package com.shiliang.icor.service.impl;

import cn.hutool.core.lang.UUID;
import com.alibaba.excel.EasyExcel;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.shiliang.icor.exception.ApiException;
import com.shiliang.icor.listener.DataEasyExcelListener;
import com.shiliang.icor.pojo.entity.*;
import com.shiliang.icor.mapper.ManuscriptMapper;
import com.shiliang.icor.pojo.enums.ApproveAttitude;
import com.shiliang.icor.pojo.enums.BusinessTypeEnum;
import com.shiliang.icor.pojo.enums.ManuscriptStatus;
import com.shiliang.icor.pojo.enums.RoleEnum;
import com.shiliang.icor.pojo.excel.ExcelConference;
import com.shiliang.icor.pojo.excel.ExcelManuscript;
import com.shiliang.icor.pojo.vo.ManuscriptSearchForm;
import com.shiliang.icor.pojo.vo.ManuscriptVO;
import com.shiliang.icor.pojo.vo.UserApproveVO;
import com.shiliang.icor.security.TokenManager;
import com.shiliang.icor.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiliang.icor.utils.*;
import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-23
 */
@Service
@Slf4j
public class ManuscriptServiceImpl extends ServiceImpl<ManuscriptMapper, ManuscriptEntity> implements ManuscriptService {


    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Autowired
    private UserManuscriptService userManuscriptService;

    @Autowired
    private ManuscriptMapper manuscriptMapper;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AttachmentService attachmentService;



    @Override
    public Page<ManuscriptVO> pageManuscriptCondition(Integer currentPage, Integer pageSize, ManuscriptSearchForm manuscriptSearchForm, HttpServletRequest request) {
        //获取当前登陆用户
        UserEntity userEntity = tokenManager.getUserInfoByToken(request);
        String userId = userEntity.getId();
        String nickName = userEntity.getNickName();
        Page<ManuscriptVO> page = new Page<ManuscriptVO>(currentPage, pageSize);
        QueryWrapper<ManuscriptEntity> queryWrapper = new QueryWrapper<ManuscriptEntity>();
        //管理员查所有，其他查已投
        if (!userEntity.getUsername().equals("admin")) {
            //查询用户的角色
            //根据用户id，查询用户拥有的角色id
            List<UserRoleEntity> userRoleEntityList = userRoleService.list(new QueryWrapper<UserRoleEntity>().eq("user_id", userId).select("role_id"));
            List<String> roleList = userRoleEntityList.stream().map(c->c.getRoleId()).collect(Collectors.toList());
            Collection<RoleEntity> roleEntities = roleService.listByIds(roleList);
            if (!Collections.isEmpty(roleEntities)) {
                for (RoleEntity roleEntity : roleEntities) {
                    //是否来自 已投列表
                    Integer isFromVotedList = manuscriptSearchForm.getIsFromVotedList();
                    //是否来自 待审列表 已审列表
                    Integer isFromApprovedList = manuscriptSearchForm.getIsFromApprovedList();
                    //如果用户拥有审稿专员角色
                    if (RoleEnum.REVIEW.getCode().equals(roleEntity.getRoleCode()) && isFromApprovedList != null && isFromApprovedList == 1) {
                        //审稿人模糊匹配
                        queryWrapper.like("u.nick_name", "%" + nickName + "%");
                    }
                    //如果用户是普通用户（投稿）
                    if (RoleEnum.CONTRIBUTOR.getCode().equals(roleEntity.getRoleCode()) && isFromVotedList != null && isFromVotedList == 1) {
                        //投稿人精准匹配
                        queryWrapper.eq("m.contributor", nickName);
                    }
                }
            }
        }
        String name = manuscriptSearchForm.getName();
        String code = manuscriptSearchForm.getCode();
        String status = manuscriptSearchForm.getStatus();
        Date startTime = manuscriptSearchForm.getStartTime();
        Date endTime = manuscriptSearchForm.getEndTime();
        Integer isApproved = manuscriptSearchForm.getIsApproved();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("m.name", name);
        }
        if (!StringUtils.isEmpty(code)) {
            queryWrapper.like("m.code", code);
        }

        if (!StringUtils.isEmpty(status)) {
            queryWrapper.eq("m.status", status);
        }
        if (startTime != null && endTime != null) {
            queryWrapper.between("m.creation_time", startTime, endTime);
        }
        if (isApproved != null && isApproved==1) {
            queryWrapper.eq("um.is_approved", 1);
        }
        if (isApproved != null && isApproved == 0) {
            queryWrapper.eq("um.is_approved", 0);
        }

        baseMapper.page(page, queryWrapper);
        return page;
    }

    @Override
    public Boolean saveManuscript(ManuscriptEntity manuscriptEntity,String attachmentId) {
        manuscriptEntity.setCode(codeGeneratorService.getCodeSerialByOptimisticLock(BusinessTypeEnum.Manuscript.name()));
        //默认是待处理状态
        manuscriptEntity.setStatus(ManuscriptStatus.Saved.getCode());
        int insert = baseMapper.insert(manuscriptEntity);
        if (insert > 0 && attachmentId != null) {
            //更新附件
            AttachmentEntity attachmentEntity = attachmentService.getById(attachmentId);
            attachmentEntity.setEntityId(manuscriptEntity.getId());
            return attachmentService.updateById(attachmentEntity);
        }

        return false;
    }

    @Override
    public Boolean updateManuscript(ManuscriptEntity manuscriptEntity) {
        ManuscriptEntity manuscript = baseMapper.selectById(manuscriptEntity.getId());
        //使用乐观锁
        manuscriptEntity.setVersion(manuscript.getVersion());
        return baseMapper.updateById(manuscriptEntity) > 0;
    }

    @Override
    @Transactional
    public Boolean submitManuscript(ManuscriptEntity manuscriptEntity,String attachmentId) {
        manuscriptEntity.setCode(codeGeneratorService.getCodeSerialByOptimisticLock(BusinessTypeEnum.Manuscript.name()));
        //提交态
        manuscriptEntity.setStatus(ManuscriptStatus.Committed.getCode());
        int insert = baseMapper.insert(manuscriptEntity);
        if (insert > 0 && attachmentId != null) {
            //更新附件
            AttachmentEntity attachmentEntity = attachmentService.getById(attachmentId);
            attachmentEntity.setEntityId(manuscriptEntity.getId());
            return attachmentService.updateById(attachmentEntity);
        }
        return false;
    }

    @Override
    @Transactional
    public List<ManuscriptEntity> submitManuscript(String[] ids) {
        try {
            List<ManuscriptEntity> manuscriptEntityList=baseMapper.findByIdIn(ids);
            for (ManuscriptEntity manuscriptEntity : manuscriptEntityList) {
                manuscriptEntity.setStatus(ManuscriptStatus.Committed.getCode());
                baseMapper.updateById(manuscriptEntity);
            }
            return manuscriptEntityList;
        } catch (Exception e) {
            throw new ApiException("提交稿件失败");
        }
    }

    @Override
    @Transactional
    public List<ManuscriptEntity> cancelSubmitManuscript(String[] ids) {
        try {
            List<ManuscriptEntity> manuscriptEntityList=baseMapper.findByIdIn(ids);
            for (ManuscriptEntity manuscriptEntity : manuscriptEntityList) {
                manuscriptEntity.setStatus(ManuscriptStatus.Saved.getCode());
                baseMapper.updateById(manuscriptEntity);
            }
            return manuscriptEntityList;
        } catch (Exception e) {
            throw new ApiException("回收稿件失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserManuscriptEntity approveManuscript(HttpServletRequest request,UserApproveVO userApproveVO) {
        try {
            Integer approveAttitude = userApproveVO.getApproveAttitude();
            //获取审稿人用户信息
            UserEntity userEntity = tokenManager.getUserInfoByToken(request);
            String userId = userEntity.getId();
            //获取审稿信息
            String manuscriptId = userApproveVO.getManuscriptId();
            UserManuscriptEntity userManuscriptEntity = new UserManuscriptEntity();
            userManuscriptEntity.setManuscriptId(manuscriptId);
            userManuscriptEntity.setUserId(userId);
            userManuscriptEntity.setIsApproved(1);
            userManuscriptEntity.setApproveTime(new Date());
            BeanUtils.copyProperties(userApproveVO, userManuscriptEntity);
            boolean result = userManuscriptService.update(userManuscriptEntity, new QueryWrapper<UserManuscriptEntity>().eq("user_id", userId).eq("manuscript_id", manuscriptId));
            //审批之后做处理
            manuscriptAfterApprove(approveAttitude, manuscriptId);
            return result ? userManuscriptEntity : null;
        } catch (Exception e) {
            throw new ApiException("审稿稿件异常："+e.getMessage());
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public void manuscriptAfterApprove(Integer approveAttitude, String manuscriptId) {
        /**
         * 审批版本二
         */
//        如果审批态度为驳回到投稿人
        if (ApproveAttitude.REJECT.getCode().equals(approveAttitude)) {
            //回写稿件状态
            rewriteManuscript(manuscriptId,ManuscriptStatus.Rejected.getCode());
            //其他审批人不需要继续审批
            otherCancelApprove(manuscriptId);
            return;
        }
        //判断所有审批人是否审批完
        if (isApprovedEnd(manuscriptId)){
            //获取稿件的所有审批记录
            List<UserManuscriptEntity> userManuscriptEntities = userManuscriptService.list(new QueryWrapper<UserManuscriptEntity>().eq("manuscript_id", manuscriptId));
            Map<Integer, Integer> attitudeCountMap = new HashMap<>();
            for (UserManuscriptEntity userManuscriptEntity : userManuscriptEntities) {
                if (attitudeCountMap.containsKey(userManuscriptEntity.getApproveAttitude())) {
                    attitudeCountMap.put(userManuscriptEntity.getApproveAttitude(), attitudeCountMap.get(userManuscriptEntity.getApproveAttitude()) + 1);
                } else {
                    attitudeCountMap.put(userManuscriptEntity.getApproveAttitude(), 1);
                }
            }
            //获取审批的总人数
            int count = userManuscriptEntities.size();
            //审稿同意的人数
            Integer approveCount = attitudeCountMap.get(ApproveAttitude.AGREE.getCode());
            //审稿不同意的人数
            Integer disApproveCount = attitudeCountMap.get(ApproveAttitude.DISAGREE.getCode());
            if (approveCount == count || approveCount>=disApproveCount) {
                //所有审批人都同意或者多数胜于少数
                rewriteManuscript(manuscriptId, ManuscriptStatus.Approved.getCode());
            } else {
                //所有审批人不同意或者多数胜于少数
                rewriteManuscript(manuscriptId, ManuscriptStatus.Disapproved.getCode());
            }
            System.out.println(attitudeCountMap);

        }





        /**
         * 审批版本一
         */
//        //如果审批态度为同意
//        if (ApproveAttitude.AGREE.getCode().equals(approveAttitude)) {
//            //判断是否是最后一个审批人
//            if (isLastApprover(manuscriptId)) {
//                rewriteManuscript(manuscriptId,ManuscriptStatus.Approved.getCode());
//            }
//        }
//        //如果审批态度为不同意
//        if (ApproveAttitude.DISAGREE.getCode().equals(approveAttitude)) {
//            //回写稿件状态
//            rewriteManuscript(manuscriptId, ManuscriptStatus.Disapproved.getCode());
//            //其他审批人不需要继续审批
//            otherCancelApprove(manuscriptId);
//        }
//        //如果审批态度为驳回到投稿人
//        if (ApproveAttitude.REJECT.getCode().equals(approveAttitude)) {
//            //回写稿件状态
//            rewriteManuscript(manuscriptId,ManuscriptStatus.Rejected.getCode());
//            //其他审批人不需要继续审批
//            otherCancelApprove(manuscriptId);
//        }
    }

    /**
     * 判断所有审批人是否审批完
     * @param manuscriptId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean isApprovedEnd(String manuscriptId) {
        return isLastApprover(manuscriptId);
    }

    /**
     * 当一个人审核不同意或者驳回，其他人取消审核
     * @param manuscriptId
     */
    @Transactional(rollbackFor = Exception.class)
    public void otherCancelApprove(String manuscriptId) {
        List<UserManuscriptEntity> userManuscriptEntities = userManuscriptService.list(new QueryWrapper<UserManuscriptEntity>().eq("manuscript_id", manuscriptId));
        List<UserManuscriptEntity> cancelApproveList = new ArrayList<>();
        for (UserManuscriptEntity userManuscriptEntity : userManuscriptEntities) {
            if (userManuscriptEntity.getIsApproved() == null || userManuscriptEntity.getIsApproved() == 0) {
                cancelApproveList.add(userManuscriptEntity);
            }
        }
        baseMapper.deleteBatchIds(cancelApproveList);
    }

    /**
     * 是否是最后一个审批人
     * @param manuscriptId
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean isLastApprover(String manuscriptId) {
        List<UserManuscriptEntity> userManuscriptEntityList = userManuscriptService.list(new QueryWrapper<UserManuscriptEntity>().eq("manuscript_id", manuscriptId));
        int count = userManuscriptService.count(new QueryWrapper<UserManuscriptEntity>().eq("manuscript_id", manuscriptId));
        int number=0;
        for (UserManuscriptEntity userManuscriptEntity : userManuscriptEntityList) {
            if (userManuscriptEntity.getIsApproved()!=null && userManuscriptEntity.getIsApproved() == 1) {
                number++;
            }
        }
        return count == number;
    }

    /**
     * 回写稿件状态
     * @param manuscriptId
     * @param status
     */
    @Transactional(rollbackFor = Exception.class)
    public void rewriteManuscript(String manuscriptId, Integer status) {
        ManuscriptEntity manuscriptEntity = baseMapper.selectById(manuscriptId);
        manuscriptEntity.setStatus(status);
        baseMapper.updateById(manuscriptEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ManuscriptEntity> cancelApproveManuscript(String[] ids) {
        try {
            List<ManuscriptEntity> manuscriptEntityList=baseMapper.findByIdIn(ids);
            for (ManuscriptEntity manuscriptEntity : manuscriptEntityList) {
                manuscriptEntity.setStatus(ManuscriptStatus.Committed.getCode());
                baseMapper.updateById(manuscriptEntity);
            }
            return manuscriptEntityList;
        } catch (Exception e) {
            throw new ApiException("取消审核稿件失败");
        }
    }

    @Override
    public List<ManuscriptEntity> rejectManuscript(String[] ids, String rejectReason) {
        try {
            List<ManuscriptEntity> manuscriptEntityList=baseMapper.findByIdIn(ids);
            for (ManuscriptEntity manuscriptEntity : manuscriptEntityList) {
                manuscriptEntity.setStatus(ManuscriptStatus.Rejected.getCode());
                baseMapper.updateById(manuscriptEntity);
            }
            return manuscriptEntityList;
        } catch (Exception e) {
            throw new ApiException("驳回稿件失败");
        }
    }

    @Override
    public void saveUserManuscriptRealtionShip(String manuscriptId, String[] userIds) {
        userManuscriptService.remove(new QueryWrapper<UserManuscriptEntity>().eq("manuscript_id", manuscriptId));

        List<UserManuscriptEntity> userManuscriptEntities = new ArrayList<>();
        for (String userId : userIds) {
            UserManuscriptEntity userManuscriptEntity = new UserManuscriptEntity();
            userManuscriptEntity.setManuscriptId(manuscriptId);
            userManuscriptEntity.setUserId(userId);
            userManuscriptEntity.setAllocateTime(new Date());
            userManuscriptEntity.setIsApproved(0);
            userManuscriptEntities.add(userManuscriptEntity);
        }
        userManuscriptService.saveBatch(userManuscriptEntities);
        //分配成功之后稿件状态为审批中
        rewriteManuscript(manuscriptId, ManuscriptStatus.Approving.getCode());
    }

    @Override
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,ManuscriptSearchForm manuscriptSearchForm) {
        log.info("*******数据导出开始*******");
        //获取当前登陆用户
        UserEntity userEntity = tokenManager.getUserInfoByToken(request);
        String userId = userEntity.getId();
        String nickName = userEntity.getNickName();
        QueryWrapper<ManuscriptEntity> queryWrapper = new QueryWrapper<ManuscriptEntity>();
        //管理员查所有，其他查已投
        if (!userEntity.getUsername().equals("admin")) {
            //查询用户的角色
            //根据用户id，查询用户拥有的角色id
            List<UserRoleEntity> userRoleEntityList = userRoleService.list(new QueryWrapper<UserRoleEntity>().eq("user_id", userId).select("role_id"));
            List<String> roleList = userRoleEntityList.stream().map(c->c.getRoleId()).collect(Collectors.toList());
            Collection<RoleEntity> roleEntities = roleService.listByIds(roleList);
            if (!Collections.isEmpty(roleEntities)) {
                for (RoleEntity roleEntity : roleEntities) {
                    //是否来自 已投列表
                    Integer isFromVotedList = manuscriptSearchForm.getIsFromVotedList();
                    //是否来自 待审列表 已审列表
                    Integer isFromApprovedList = manuscriptSearchForm.getIsFromApprovedList();
                    //如果用户拥有审稿专员角色
                    if (RoleEnum.REVIEW.getCode().equals(roleEntity.getRoleCode()) && isFromApprovedList != null && isFromApprovedList == 1) {
                        //审稿人模糊匹配
                        queryWrapper.like("u.nick_name", "%" + nickName + "%");
                    }
                    //如果用户是普通用户（投稿）
                    if (RoleEnum.CONTRIBUTOR.getCode().equals(roleEntity.getRoleCode()) && isFromVotedList != null && isFromVotedList == 1) {
                        //投稿人精准匹配
                        queryWrapper.eq("m.contributor", nickName);
                    }
                }
            }
        }
        List<ManuscriptEntity> manuscriptEntities = this.baseMapper.selectList(queryWrapper);
        List<ExcelManuscript> excelManuscripts = new ArrayList<>();
        for (ManuscriptEntity manuscriptEntity : manuscriptEntities) {
            ExcelManuscript excelManuscript = new ExcelManuscript();
            BeanUtils.copyProperties(manuscriptEntity, excelManuscript);
            excelManuscripts.add(excelManuscript);
        }
        String fileName = UUID.fastUUID().toString();
        String sheetName = "会议稿件";
        try {
            ExcelUtils.createTemplate(response, fileName, sheetName, excelManuscripts,
                    ExcelManuscript.class, ExcelManuscript.getHeadHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("*******数据导出结束*******");
    }

    @Override
    public void importManuscriptExcel(MultipartFile file) throws IOException {
        // ----- 读取excel数据 -----
        DataEasyExcelListener<ExcelManuscript> listener = new DataEasyExcelListener<>();
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, ExcelManuscript.class, listener).sheet(0).doRead();
        List<ExcelManuscript> data = listener.getData();
        System.out.println("导入的稿件信息："+data);

        List<ManuscriptEntity> manuscriptEntities = new ArrayList<>();

        for (ExcelManuscript excelManuscript : data) {
            ManuscriptEntity manuscriptEntity = new ManuscriptEntity();
            BeanUtils.copyProperties(excelManuscript, manuscriptEntity);
            manuscriptEntities.add(manuscriptEntity);
        }
        //写入数据库
        this.saveBatch(manuscriptEntities);
    }

    @Override
    public String uploadOSS(MultipartFile file) {
        String url = OSSUtil.upload(file);
        log.info("阿里云上传的文件路径：" + url);
        AttachmentEntity attachmentEntity = new AttachmentEntity();
        attachmentEntity.setUrl(url);
        attachmentEntity.setEntityType(DatabaseTableConstant.MANUSCRIPT);
        attachmentEntity.setName(file.getOriginalFilename());
        attachmentEntity.setCode(codeGeneratorService.getCodeSerialByOptimisticLock(BusinessTypeEnum.Attachment.name()));
        //写入到数据库中
        return attachmentService.save(attachmentEntity) ? attachmentEntity.getId() : null;
    }
}

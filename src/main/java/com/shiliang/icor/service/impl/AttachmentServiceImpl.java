package com.shiliang.icor.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiliang.icor.mapper.AttachmentMapper;
import com.shiliang.icor.pojo.entity.AttachmentEntity;
import com.shiliang.icor.pojo.entity.ConferenceEntity;
import com.shiliang.icor.pojo.entity.ManuscriptEntity;
import com.shiliang.icor.pojo.enums.BusinessTypeEnum;
import com.shiliang.icor.pojo.vo.AttachmentBusinessObjectSearch;
import com.shiliang.icor.pojo.vo.AttachmentSearchForm;
import com.shiliang.icor.pojo.vo.AttachmentVO;
import com.shiliang.icor.service.AttachmentService;
import com.shiliang.icor.service.CodeGeneratorService;
import com.shiliang.icor.service.ConferenceService;
import com.shiliang.icor.service.ManuscriptService;
import com.shiliang.icor.utils.DatabaseTableConstant;
import com.shiliang.icor.utils.OSSUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-25
 */
@Service
@Slf4j
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, AttachmentEntity> implements AttachmentService {

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ManuscriptService manuscriptService;

    @Override
    public String upload(String businessType, MultipartFile file) {
        String[] fileInfo = OSSUploadUtil.upload(file);
        assert fileInfo != null;
        String url =  fileInfo[0];
        String fileName =  fileInfo[1];
        log.info("阿里云上传的文件路径：" + url);
        AttachmentEntity attachmentEntity = new AttachmentEntity();
        attachmentEntity.setUrl(url);
        attachmentEntity.setFileName(fileName);
        if (businessType.equals(DatabaseTableConstant.MANUSCRIPT)) {
            attachmentEntity.setEntityType(DatabaseTableConstant.MANUSCRIPT);
        } else if (businessType.equals(DatabaseTableConstant.CONFERENCE)) {
            attachmentEntity.setEntityType(DatabaseTableConstant.CONFERENCE);
        }
        attachmentEntity.setName(file.getOriginalFilename());
        attachmentEntity.setCode(codeGeneratorService.getCodeSerialByOptimisticLock(BusinessTypeEnum.Attachment.name()));
        //写入到数据库中
        return baseMapper.insert(attachmentEntity) > 0 ? attachmentEntity.getId() : null;
    }

    @Override
    public Page<AttachmentEntity> pageAttachmentCondition(Integer currentPage, Integer pageSize, AttachmentSearchForm attachmentSearchForm) {
        Page<AttachmentEntity> page = new Page<AttachmentEntity>(currentPage, pageSize);
        QueryWrapper<AttachmentEntity> queryWrapper = new QueryWrapper<AttachmentEntity>();
        String name = attachmentSearchForm.getName();
        String code = attachmentSearchForm.getCode();
        Date startTime = attachmentSearchForm.getStartTime();
        Date endTime = attachmentSearchForm.getEndTime();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(code)) {
            queryWrapper.like("code", code);
        }
        //排序
        queryWrapper.orderByDesc("creation_time");

        if (startTime != null && endTime != null) {
            queryWrapper.between("creation_time", startTime, endTime);
        }
        baseMapper.selectPage(page, queryWrapper);
        List<AttachmentEntity> attachmentEntities = page.getRecords();
        for (AttachmentEntity attachmentEntity : attachmentEntities) {
            String entityId = attachmentEntity.getEntityId();
            if (entityId != null) {
                if (DatabaseTableConstant.CONFERENCE.equals(attachmentEntity.getEntityType())) {
                    ConferenceEntity conferenceEntity = conferenceService.getOne(new QueryWrapper<ConferenceEntity>().eq("id", entityId).select("name"));
                    if (conferenceEntity != null) {
                        attachmentEntity.setBusinessName(conferenceEntity.getName());
                    }
                } else if (DatabaseTableConstant.MANUSCRIPT.equals(attachmentEntity.getEntityType())) {
                    ManuscriptEntity manuscriptEntity = manuscriptService.getOne(new QueryWrapper<ManuscriptEntity>().eq("id", entityId).select("name"));
                    if (manuscriptEntity != null) {
                        attachmentEntity.setBusinessName(manuscriptEntity.getName());
                    }
                }
            }
        }
        page.setRecords(attachmentEntities);
        return page;
    }

    @Override
    public List<AttachmentVO> getAttachmentByBusinessObject(AttachmentBusinessObjectSearch attachmentBusinessObjectSearch) {
        return baseMapper.findByEntityTypeAndEntityId(attachmentBusinessObjectSearch.getEntityType(), attachmentBusinessObjectSearch.getEntityId());
    }

}

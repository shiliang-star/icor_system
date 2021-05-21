package com.shiliang.icor.service.impl;

import cn.hutool.core.lang.UUID;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiliang.icor.listener.DataEasyExcelListener;
import com.shiliang.icor.mapper.ConferenceMapper;
import com.shiliang.icor.pojo.entity.AttachmentEntity;
import com.shiliang.icor.pojo.entity.ConferenceEntity;
import com.shiliang.icor.pojo.enums.BusinessTypeEnum;
import com.shiliang.icor.pojo.excel.ExcelConference;
import com.shiliang.icor.pojo.vo.ConferenceSearchForm;
import com.shiliang.icor.service.AttachmentService;
import com.shiliang.icor.service.CodeGeneratorService;
import com.shiliang.icor.service.ConferenceService;
import com.shiliang.icor.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-03-22
 */
@Service
@Slf4j
public class ConferenceServiceImpl extends ServiceImpl<ConferenceMapper, ConferenceEntity> implements ConferenceService {

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Autowired
    private AttachmentService attachmentService;

    @Override
    public Boolean saveConference(ConferenceEntity conferenceEntity, String attachmentId) {
        conferenceEntity.setCode(codeGeneratorService.getCodeSerialByOptimisticLock(BusinessTypeEnum.Conference.name()));
        int insert = baseMapper.insert(conferenceEntity);
        if (insert > 0 && attachmentId != null) {
            //更新附件
            AttachmentEntity attachmentEntity = attachmentService.getById(attachmentId);
            attachmentEntity.setEntityId(conferenceEntity.getId());
            return attachmentService.updateById(attachmentEntity);
        }
        return false;
    }

    @Override
    public Boolean updateManuscript(ConferenceEntity conferenceEntity) {
        ConferenceEntity manuscript = baseMapper.selectById(conferenceEntity.getId());
        //使用乐观锁
        conferenceEntity.setVersion(manuscript.getVersion());
        return baseMapper.updateById(conferenceEntity) > 0;
    }

    @Override
    public Page<ConferenceEntity> pageConferenceCondition(Integer currentPage, Integer pageSize, ConferenceSearchForm conferenceSearchForm) {
        Page<ConferenceEntity> page = new Page<ConferenceEntity>(currentPage, pageSize);
        QueryWrapper<ConferenceEntity> queryWrapper = new QueryWrapper<ConferenceEntity>();
        String name = conferenceSearchForm.getName();
        String code = conferenceSearchForm.getCode();
        Date startTime = conferenceSearchForm.getStartTime();
        Date endTime = conferenceSearchForm.getEndTime();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(code)) {
            queryWrapper.like("code", code);
        }
        //排序
        queryWrapper.orderByDesc("creation_time");

        if (startTime != null && endTime!=null) {
            queryWrapper.between("creation_time", startTime, endTime);
        }
        baseMapper.selectPage(page, queryWrapper);
        return page;
    }

    @Override
    public void exportExcel(HttpServletResponse response)  {
        log.info("*******数据导出开始*******");
        List<ConferenceEntity> conferenceEntities = baseMapper.selectList(null);
        List<ExcelConference> excelConferences = new ArrayList<>();
        for (ConferenceEntity conferenceEntity : conferenceEntities) {
            ExcelConference excelConference = new ExcelConference();
            BeanUtils.copyProperties(conferenceEntity, excelConference);
            excelConferences.add(excelConference);
        }
        String fileName = UUID.fastUUID().toString();
        String sheetName = "国际会议信息";
        try {
            ExcelUtils.createTemplate(response, fileName, sheetName, excelConferences,
                    ExcelConference.class, ExcelConference.getHeadHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("*******数据导出结束*******");
    }

    @Override
    public void importConferenceExcel(MultipartFile file) throws IOException {
        // ----- 读取excel数据 -----
        DataEasyExcelListener<ExcelConference> listener = new DataEasyExcelListener<>();
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, ExcelConference.class, listener).sheet(0).doRead();
        List<ExcelConference> data = listener.getData();
        System.out.println("导入的学生信息："+data);

        List<ConferenceEntity> conferenceEntities = new ArrayList<>();

        for (ExcelConference excelConference : data) {
            ConferenceEntity conferenceEntity = new ConferenceEntity();
            BeanUtils.copyProperties(excelConference, conferenceEntity);
            conferenceEntities.add(conferenceEntity);
        }
        //写入数据库
        this.saveBatch(conferenceEntities);
    }


}

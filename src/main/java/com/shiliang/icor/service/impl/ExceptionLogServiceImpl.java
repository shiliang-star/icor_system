package com.shiliang.icor.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiliang.icor.mapper.ExceptionLogMapper;
import com.shiliang.icor.pojo.entity.ExceptionLogEntity;
import com.shiliang.icor.pojo.entity.OperationLogEntity;
import com.shiliang.icor.pojo.excel.ExcelConference;
import com.shiliang.icor.pojo.vo.ExceptionLogSearchForm;
import com.shiliang.icor.service.ExceptionLogService;
import com.shiliang.icor.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-04-19
 */
@Service
@Slf4j
public class ExceptionLogServiceImpl extends ServiceImpl<ExceptionLogMapper, ExceptionLogEntity> implements ExceptionLogService {

    @Override
    public Page<ExceptionLogEntity> pageExceptionLogCondition(Integer currentPage, Integer pageSize, ExceptionLogSearchForm exceptionLogSearchForm) {
        Page<ExceptionLogEntity> page = new Page<ExceptionLogEntity>(currentPage, pageSize);
        QueryWrapper<ExceptionLogEntity> queryWrapper = new QueryWrapper<ExceptionLogEntity>();
//        String name = operationLogSearchForm.getName();
//        String code = operationLogSearchForm.getCode();
//        Date startTime = operationLogSearchForm.getStartTime();
//        Date endTime = operationLogSearchForm.getEndTime();
//        if (!StringUtils.isEmpty(name)) {
//            queryWrapper.like("name", name);
//        }
//        if (!StringUtils.isEmpty(code)) {
//            queryWrapper.like("code", code);
//        }
        //排序
        queryWrapper.orderByDesc("oper_create_time");

//        if (startTime != null && endTime!=null) {
//            queryWrapper.between("creation_time", startTime, endTime);
//        }
        baseMapper.selectPage(page, queryWrapper);
        return page;
    }

    @Override
    public void exportExcel(HttpServletResponse response) {
        log.info("*******数据导出开始*******");
        List<ExceptionLogEntity> exceptionLogEntities = baseMapper.selectList(null);
        String fileName = UUID.fastUUID().toString();
        String sheetName = "操作日志信息";
        try {
            ExcelUtils.createTemplate(response, fileName, sheetName, exceptionLogEntities,
                    ExceptionLogEntity.class, ExcelConference.getHeadHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("*******数据导出结束*******");
    }
}

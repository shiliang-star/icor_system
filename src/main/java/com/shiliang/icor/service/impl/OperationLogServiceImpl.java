package com.shiliang.icor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiliang.icor.mapper.OperationLogMapper;
import com.shiliang.icor.pojo.entity.OperationLogEntity;
import com.shiliang.icor.pojo.vo.OperationLogSearchForm;
import com.shiliang.icor.service.OperationLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-04-19
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLogEntity> implements OperationLogService {

    @Override
    public Page<OperationLogEntity> pageOperationLogCondition(Integer currentPage, Integer pageSize, OperationLogSearchForm operationLogSearchForm) {
        Page<OperationLogEntity> page = new Page<OperationLogEntity>(currentPage, pageSize);
        QueryWrapper<OperationLogEntity> queryWrapper = new QueryWrapper<OperationLogEntity>();
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
}

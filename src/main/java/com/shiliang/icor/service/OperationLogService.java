package com.shiliang.icor.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shiliang.icor.pojo.entity.OperationLogEntity;
import com.shiliang.icor.pojo.vo.OperationLogSearchForm;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-04-19
 */
public interface OperationLogService extends IService<OperationLogEntity> {

    Page<OperationLogEntity> pageOperationLogCondition(Integer currentPage, Integer pageSize, OperationLogSearchForm operationLogSearchForm);

    void exportExcel(HttpServletResponse response);
}

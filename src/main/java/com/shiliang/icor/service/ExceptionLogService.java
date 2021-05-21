package com.shiliang.icor.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shiliang.icor.pojo.entity.ExceptionLogEntity;
import com.shiliang.icor.pojo.vo.ExceptionLogSearchForm;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-04-19
 */
public interface ExceptionLogService extends IService<ExceptionLogEntity> {

    Page<ExceptionLogEntity> pageExceptionLogCondition(Integer currentPage, Integer pageSize, ExceptionLogSearchForm exceptionLogSearchForm);

    void exportExcel(HttpServletResponse response);
}

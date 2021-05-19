package com.shiliang.icor.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.shiliang.icor.pojo.CommonResult;
import com.shiliang.icor.pojo.entity.ExceptionLogEntity;
import com.shiliang.icor.pojo.vo.ExceptionLogSearchForm;
import com.shiliang.icor.service.ExceptionLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ShiLiang
 * @since 2021-04-19
 */
@RestController
@RequestMapping("/system/exception")
@Api("业务异常接口中心")
public class ExceptionLogController {


    @Autowired
    private ExceptionLogService exceptionLogService;


    @ApiOperation("分页条件查询异常日志信息信息")
    @PostMapping("list/{currentPage}/{pageSize}")
    public CommonResult<Page<ExceptionLogEntity>> pageExceptionLogCondition(@ApiParam(name = "currentPage", value = "当前页码", required = true, defaultValue = "1")
                                                                          @PathVariable Integer currentPage,
                                                                            @ApiParam(name = "pageSize", value = "每页记录数", required = true, defaultValue = "10")
                                                                          @PathVariable Integer pageSize,
                                                                            @ApiParam(name = "exceptionLogSearchForm", value = "查询对象", required = false)
                                                                          @RequestBody(required = false) ExceptionLogSearchForm exceptionLogSearchForm) {
        Page<ExceptionLogEntity> page = exceptionLogService.pageExceptionLogCondition(currentPage, pageSize, exceptionLogSearchForm);
        return CommonResult.success(page);
    }


}


package com.shiliang.icor.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.shiliang.icor.pojo.CommonResult;
import com.shiliang.icor.pojo.entity.OperationLogEntity;
import com.shiliang.icor.pojo.vo.OperationLogSearchForm;
import com.shiliang.icor.service.OperationLogService;
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
@RequestMapping("/system/operation")
@Api("操作日志接口中心")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;


    @ApiOperation("分页条件查询操作日志信息信息")
    @PostMapping("list/{currentPage}/{pageSize}")
    public CommonResult<Page<OperationLogEntity>> pageOperationLogCondition(@ApiParam(name = "currentPage", value = "当前页码", required = true, defaultValue = "1")
                                                                        @PathVariable Integer currentPage,
                                                                            @ApiParam(name = "pageSize", value = "每页记录数", required = true, defaultValue = "10")
                                                                        @PathVariable Integer pageSize,
                                                                            @ApiParam(name = "operationLogSearchForm", value = "查询对象", required = false)
                                                                        @RequestBody(required = false) OperationLogSearchForm operationLogSearchForm) {
        Page<OperationLogEntity> page = operationLogService.pageOperationLogCondition(currentPage, pageSize, operationLogSearchForm);
        return CommonResult.success(page);
    }

}


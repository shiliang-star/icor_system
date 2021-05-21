package com.shiliang.icor.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.shiliang.icor.pojo.CommonResult;
import com.shiliang.icor.pojo.entity.OperationLogEntity;
import com.shiliang.icor.pojo.enums.OperTypeConst;
import com.shiliang.icor.pojo.vo.ManuscriptSearchForm;
import com.shiliang.icor.pojo.vo.OperationLogSearchForm;
import com.shiliang.icor.service.OperationLogService;
import com.shiliang.icor.utils.OperLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

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


    @ApiOperation("导出")
    @GetMapping("export")
    @OperLog(operModule = "操作日志模块", operType = OperTypeConst.EXPORT, operDesc = "导出操作日志")
    public CommonResult exportExcel(HttpServletResponse response) throws IOException {
        operationLogService.exportExcel(response);
        return CommonResult.success(null);
    }

    @ApiOperation("批量删除操作日志")
    @OperLog(operModule = "操作日志模块", operType = OperTypeConst.DELETE, operDesc = "批量删除操作日志")
    @DeleteMapping("batchDelete")
    public CommonResult batchDelete(@RequestParam("ids") String ids) {
        String[] arrayIds = ids.split(",");
        boolean result = operationLogService.removeByIds(Arrays.asList(arrayIds));
        return result?CommonResult.success(ids):CommonResult.failed();
    }

}


package com.shiliang.icor.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.shiliang.icor.pojo.CommonResult;
import com.shiliang.icor.pojo.entity.ExceptionLogEntity;
import com.shiliang.icor.pojo.enums.OperTypeConst;
import com.shiliang.icor.pojo.vo.ExceptionLogSearchForm;
import com.shiliang.icor.service.ExceptionLogService;
import com.shiliang.icor.utils.OperLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("导出异常日志")
    @GetMapping("export")
    @OperLog(operModule = "异常日志模块", operType = OperTypeConst.EXPORT, operDesc = "导出异常日志")
    public CommonResult exportExcel(HttpServletResponse response) throws IOException {
        exceptionLogService.exportExcel(response);
        return CommonResult.success(null);
    }

    @ApiOperation("批量删除异常日志")
    @OperLog(operModule = "异常日志模块", operType = OperTypeConst.DELETE, operDesc = "批量删除异常日志")
    @DeleteMapping("batchDelete")
    public CommonResult batchDelete(@RequestParam("ids") String ids) {
        String[] arrayIds = ids.split(",");
        boolean result = exceptionLogService.removeByIds(Arrays.asList(arrayIds));
        return result?CommonResult.success(ids):CommonResult.failed();
    }


}


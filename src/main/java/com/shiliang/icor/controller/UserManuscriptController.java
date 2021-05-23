package com.shiliang.icor.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiliang.icor.pojo.CommonResult;
import com.shiliang.icor.pojo.entity.UserEntity;
import com.shiliang.icor.pojo.enums.OperTypeConst;
import com.shiliang.icor.pojo.vo.ManuscriptSearchForm;
import com.shiliang.icor.pojo.vo.ManuscriptVO;
import com.shiliang.icor.pojo.vo.UserManuscriptVO;
import com.shiliang.icor.security.TokenManager;
import com.shiliang.icor.service.UserManuscriptService;
import com.shiliang.icor.utils.OperLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ShiLiang
 * @since 2021-04-25
 */
@RestController
@RequestMapping("/userManuscript")
public class UserManuscriptController {

    @Autowired
    private UserManuscriptService userManuscriptService;

    @Autowired
    private TokenManager tokenManager;


    @ApiOperation("分页条件查询稿件信息")
    @PostMapping("list/{currentPage}/{pageSize}")
    @OperLog(operModule = "用户-稿件模块", operType = OperTypeConst.GET, operDesc = "分页条件查询用户稿件信息")
    public CommonResult<Page<UserManuscriptVO>> pageUserManuscriptCondition(@ApiParam(name = "currentPage", value = "当前页码", required = true, defaultValue = "1")
                                                                    @PathVariable Integer currentPage,
                                                                        @ApiParam(name = "pageSize", value = "每页记录数", required = true, defaultValue = "10")
                                                                    @PathVariable Integer pageSize,
                                                                        @ApiParam(name = "manuscriptSearchForm", value = "查询对象", required = false)
                                                                    @RequestBody(required = false) ManuscriptSearchForm manuscriptSearchForm, HttpServletRequest request) {
        Page<UserManuscriptVO> page = userManuscriptService.pageUserManuscriptCondition(currentPage, pageSize, manuscriptSearchForm,request);
        return CommonResult.success(page);
    }


    @ApiOperation("根据稿件主键查询稿件分配情况")
    @OperLog(operModule = "用户-稿件模块", operType = OperTypeConst.GET, operDesc = "根据稿件主键查询稿件分配情况")
    @GetMapping("{manuscriptId}")
    public CommonResult<List<UserManuscriptVO>> getByManuscriptId(@PathVariable("manuscriptId") String manuscriptId) {
        List<UserManuscriptVO> userManuscriptVOList = userManuscriptService.getByManuscriptId(manuscriptId);
        return CommonResult.success(userManuscriptVOList);
    }

    @ApiOperation("根据稿件主键和审稿人查询稿件审批情况")
    @OperLog(operModule = "用户-稿件模块", operType = OperTypeConst.GET, operDesc = "根据稿件主键和审稿人查询稿件审批情况")
    @GetMapping("/getByManuscriptIdAndReviewer")
    public CommonResult<UserManuscriptVO> getByManuscriptIdAndReviewer(HttpServletRequest request,@RequestParam("manuscriptId") String manuscriptId) {
        UserEntity userEntity = tokenManager.getUserInfoByToken(request);
        UserManuscriptVO userManuscriptVO = userManuscriptService.getByManuscriptIdAndReviewer(userEntity.getId(),manuscriptId);
        return CommonResult.success(userManuscriptVO);
    }

    @ApiOperation("批量删除用户-稿件信息")
    @OperLog(operModule = "用户-稿件模块", operType = OperTypeConst.DELETE, operDesc = "批量删除用户-稿件信息")
    @DeleteMapping("batchDelete")
    public CommonResult batchDelete(@RequestParam("ids") String ids) {
        String[] arrayIds = ids.split(",");
        boolean result = userManuscriptService.removeByIds(Arrays.asList(arrayIds));
        return result?CommonResult.success(ids):CommonResult.failed();
    }

    @ApiOperation("导出用户-稿件信息")
    @GetMapping("export")
    @OperLog(operModule = "异常日志模块", operType = OperTypeConst.EXPORT, operDesc = "导出用户-稿件信息")
    public CommonResult exportExcel(HttpServletResponse response) throws IOException {
        userManuscriptService.exportExcel(response);
        return CommonResult.success(null);
    }




}


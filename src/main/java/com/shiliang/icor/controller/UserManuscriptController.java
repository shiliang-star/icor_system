package com.shiliang.icor.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiliang.icor.pojo.CommonResult;
import com.shiliang.icor.pojo.vo.ManuscriptSearchForm;
import com.shiliang.icor.pojo.vo.ManuscriptVO;
import com.shiliang.icor.pojo.vo.UserManuscriptVO;
import com.shiliang.icor.service.UserManuscriptService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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


    @ApiOperation("分页条件查询稿件信息")
    @PostMapping("list/{currentPage}/{pageSize}")
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
    @GetMapping("{manuscriptId}")
    public CommonResult<List<UserManuscriptVO>> getByManuscriptId(@PathVariable("manuscriptId") String manuscriptId) {
        List<UserManuscriptVO> userManuscriptVOList = userManuscriptService.getByManuscriptId(manuscriptId);
        return CommonResult.success(userManuscriptVOList);
    }




}


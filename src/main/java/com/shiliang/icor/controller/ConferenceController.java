package com.shiliang.icor.controller;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiliang.icor.pojo.CommonResult;
import com.shiliang.icor.pojo.entity.ConferenceEntity;
import com.shiliang.icor.pojo.excel.ExcelConference;
import com.shiliang.icor.pojo.vo.ConferenceSearchForm;
import com.shiliang.icor.service.ConferenceService;
import com.shiliang.icor.utils.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ShiLiang
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/conference")
@Api(description = "国际会议接口中心")
public class ConferenceController {


    @Autowired
    private ConferenceService conferenceService;


    @ApiOperation("分页条件查询会议信息")
    @PostMapping("list/{currentPage}/{pageSize}")
    public CommonResult<Page<ConferenceEntity>> pageConferenceCondition(@ApiParam(name = "currentPage", value = "当前页码", required = true, defaultValue = "1")
                                                                        @PathVariable Integer currentPage,
                                                                        @ApiParam(name = "pageSize", value = "每页记录数", required = true, defaultValue = "10")
                                                                        @PathVariable Integer pageSize,
                                                                        @ApiParam(name = "conferenceSearchForm", value = "查询对象", required = false)
                                                                        @RequestBody(required = false) ConferenceSearchForm conferenceSearchForm) {
        Page<ConferenceEntity> page = conferenceService.pageConferenceCondition(currentPage, pageSize, conferenceSearchForm);
        return CommonResult.success(page);
    }


    /**
     * 添加会议
     *
     * @param conferenceEntity
     * @return
     */
    @ApiOperation("添加会议")
    @PostMapping
    public CommonResult<ConferenceEntity> saveConference(@RequestBody ConferenceEntity conferenceEntity) {
        Boolean save = conferenceService.saveConference(conferenceEntity);
        return save ? CommonResult.success(conferenceEntity) : CommonResult.failed();
    }

    @ApiOperation("修改会议")
    @PutMapping
    public CommonResult updateManuscript(@RequestBody ConferenceEntity conferenceEntity) {
        Boolean result = conferenceService.updateManuscript(conferenceEntity);
        return result ? CommonResult.success(conferenceEntity) : CommonResult.failed();
    }

    @ApiOperation("删除会议")
    @DeleteMapping("{id}")
    public CommonResult deleteManuscript(@PathVariable String id) {
        boolean result = conferenceService.removeById(id);
        return result ? CommonResult.success(id) : CommonResult.failed();
    }

    @ApiOperation("根据id获取会议信息")
    @GetMapping("{id}")
    public CommonResult<ConferenceEntity> getManuscriptById(@PathVariable String id) {
        return CommonResult.success(conferenceService.getById(id));
    }

    @ApiOperation("获取所有会议")
    @GetMapping("list")
    public CommonResult<List<ConferenceEntity>> list() {
        return CommonResult.success(conferenceService.list(null));
    }

    @ApiOperation("批量删除会议")
    @DeleteMapping("batchDelete")
    public CommonResult batchDelete(@RequestParam("ids") String ids) {
        String[] arrayIds = ids.split(",");
        boolean result = conferenceService.removeByIds(Arrays.asList(arrayIds));
        return result?CommonResult.success(ids):CommonResult.failed();
    }

    @ApiOperation("导出")
    @GetMapping("export")
    public CommonResult exportExcel(HttpServletResponse response) throws IOException {
        conferenceService.exportExcel(response);
        return CommonResult.success(null);
    }

    @ApiOperation("导入")
    @RequestMapping(value = "/import", method = {RequestMethod.GET, RequestMethod.POST})
    public CommonResult importConferenceExcel(MultipartFile file) throws IOException {
        conferenceService.importConferenceExcel(file);
        return CommonResult.success(null);
    }

}


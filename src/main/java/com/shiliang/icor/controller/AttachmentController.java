package com.shiliang.icor.controller;




import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiliang.icor.pojo.CommonResult;
import com.shiliang.icor.pojo.entity.AttachmentEntity;
import com.shiliang.icor.pojo.entity.ConferenceEntity;
import com.shiliang.icor.pojo.vo.AttachmentSearchForm;
import com.shiliang.icor.pojo.vo.ConferenceSearchForm;
import com.shiliang.icor.service.AttachmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-25
 */
@RestController
@RequestMapping("/attachment-entity")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("upload")
    public CommonResult upload(MultipartFile file) {
        return attachmentService.upload(file);
    }


    @ApiOperation("分页条件查询附件信息")
    @PostMapping("list/{currentPage}/{pageSize}")
    public CommonResult<Page<AttachmentEntity>> pageAttachmentCondition(@ApiParam(name = "currentPage", value = "当前页码", required = true, defaultValue = "1")
                                                                        @PathVariable Integer currentPage,
                                                                        @ApiParam(name = "pageSize", value = "每页记录数", required = true, defaultValue = "10")
                                                                        @PathVariable Integer pageSize,
                                                                        @ApiParam(name = "attachmentSearchForm", value = "查询对象", required = false)
                                                                        @RequestBody(required = false) AttachmentSearchForm attachmentSearchForm) {
        Page<AttachmentEntity> page = attachmentService.pageAttachmentCondition(currentPage, pageSize, attachmentSearchForm);
        return CommonResult.success(page);
    }




    @ApiOperation("删除附件")
    @DeleteMapping("{id}")
    public CommonResult deleteAttachment(@PathVariable String id) {
        //TODO OSS删除
        boolean result = attachmentService.removeById(id);
        return result ? CommonResult.success(id) : CommonResult.failed();
    }
}


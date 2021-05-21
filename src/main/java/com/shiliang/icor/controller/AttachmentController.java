package com.shiliang.icor.controller;




import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiliang.icor.pojo.CommonResult;
import com.shiliang.icor.pojo.entity.AttachmentEntity;
import com.shiliang.icor.pojo.enums.OperTypeConst;
import com.shiliang.icor.pojo.vo.AttachmentVO;
import com.shiliang.icor.pojo.vo.AttachmentBusinessObjectSearch;
import com.shiliang.icor.pojo.vo.AttachmentSearchForm;
import com.shiliang.icor.service.AttachmentService;
import com.shiliang.icor.utils.OSSUploadUtil;
import com.shiliang.icor.utils.OperLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-25
 */
@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @ApiOperation("上传附件到阿里云OSS")
    @PostMapping("upload")
    @OperLog(operModule = "附件模块", operType = OperTypeConst.UPLOAD, operDesc = "上传附件到阿里云OSS")
    public CommonResult upload(@RequestParam("businessType") String businessType,MultipartFile file) {
        return CommonResult.success(attachmentService.upload(businessType,file));
    }


    @ApiOperation("分页条件查询附件信息")
    @OperLog(operModule = "附件模块", operType = OperTypeConst.GET, operDesc = "分页条件查询附件信息")
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


    @ApiOperation("根据业务类型和主键查询附件")
    @OperLog(operModule = "附件模块", operType = OperTypeConst.GET, operDesc = "根据业务类型和主键查询附件")
    @PostMapping("getAttachment")
    public CommonResult<List<AttachmentVO>> getAttachmentByBusinessObject(@RequestBody AttachmentBusinessObjectSearch attachmentBusinessObjectSearch) {
        List<AttachmentVO> attachmentVOS = attachmentService.getAttachmentByBusinessObject(attachmentBusinessObjectSearch);
        return CommonResult.success(attachmentVOS);
    }


    @ApiOperation("删除附件")
    @OperLog(operModule = "附件模块", operType = OperTypeConst.DELETE, operDesc = "删除附件（单个文件）")
    @DeleteMapping("{id}")
    public CommonResult deleteAttachment(@PathVariable String id) {
        AttachmentEntity attachmentEntity = attachmentService.getById(id);
        //OSS上删除文件
        OSSUploadUtil.deleteFile(attachmentEntity.getFileName());
        boolean result = attachmentService.removeById(id);
        return result ? CommonResult.success(id) : CommonResult.failed();
    }

    @ApiOperation("批量删除附件")
    @OperLog(operModule = "附件模块", operType = OperTypeConst.DELETE, operDesc = "批量删除附件")
    @DeleteMapping("batchDelete")
    public CommonResult batchDelete(@RequestParam("ids") String ids) {
        String[] arrayIds = ids.split(",");
        Collection<AttachmentEntity> attachmentEntities = attachmentService.listByIds(Arrays.asList(arrayIds));
        boolean result = false;
        if (attachmentEntities != null) {
            for (AttachmentEntity attachmentEntity : attachmentEntities) {
                OSSUploadUtil.deleteFile(attachmentEntity.getFileName());
                result= attachmentService.removeById(attachmentEntity.getId());
            }
        }
        return result?CommonResult.success(ids):CommonResult.failed();
    }
}


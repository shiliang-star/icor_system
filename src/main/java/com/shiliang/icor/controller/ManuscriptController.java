package com.shiliang.icor.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.shiliang.icor.exception.ApiException;
import com.shiliang.icor.pojo.CommonResult;
import com.shiliang.icor.pojo.entity.ManuscriptEntity;
import com.shiliang.icor.pojo.entity.UserEntity;
import com.shiliang.icor.pojo.entity.UserManuscriptEntity;
import com.shiliang.icor.pojo.enums.ManuscriptStatus;
import com.shiliang.icor.pojo.enums.OperTypeConst;
import com.shiliang.icor.pojo.vo.ManuscriptSearchForm;
import com.shiliang.icor.pojo.vo.ManuscriptVO;
import com.shiliang.icor.pojo.vo.UserApproveVO;
import com.shiliang.icor.security.TokenManager;
import com.shiliang.icor.service.ManuscriptService;
import com.shiliang.icor.utils.OperLog;
import com.shiliang.icor.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-23
 */
@Api(description = "稿件接口中心")
@RestController
@RequestMapping("/manuscript")
public class ManuscriptController {


    @Autowired
    private ManuscriptService manuscriptService;

    @Autowired
    private TokenManager tokenManager;

    @ApiOperation("分页条件查询稿件信息")
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.GET, operDesc = "分页条件查询稿件信息")
    @PostMapping("list/{currentPage}/{pageSize}")
    public CommonResult<Page<ManuscriptVO>> pageManuscriptCondition(@ApiParam(name = "currentPage", value = "当前页码", required = true, defaultValue = "1")
                                                                        @PathVariable Integer currentPage,
                                                                        @ApiParam(name = "pageSize", value = "每页记录数", required = true, defaultValue = "10")
                                                                        @PathVariable Integer pageSize,
                                                                        @ApiParam(name = "manuscriptSearchForm", value = "查询对象", required = false)
                                                                        @RequestBody(required = false) ManuscriptSearchForm manuscriptSearchForm,HttpServletRequest request) {
        Page<ManuscriptVO> page = manuscriptService.pageManuscriptCondition(currentPage, pageSize, manuscriptSearchForm,request);
        return CommonResult.success(page);
    }

    @ApiOperation("添加稿件")
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.ADD, operDesc = "新增稿件")
    @PostMapping
    public CommonResult saveManuscript(HttpServletRequest request, @RequestBody ManuscriptEntity manuscriptEntity, @RequestParam(value = "attachmentId", required = false) String attachmentId) {
        UserEntity userEntity = tokenManager.getUserInfoByToken(request);
        manuscriptEntity.setContributor(userEntity.getNickName());
        Boolean result = manuscriptService.saveManuscript(manuscriptEntity,attachmentId);
        return result ? CommonResult.success(manuscriptEntity) : CommonResult.failed();
    }

    @ApiOperation("修改稿件")
    @PutMapping
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.UPDATE, operDesc = "修改稿件")
    public CommonResult updateManuscript(@RequestBody ManuscriptEntity manuscriptEntity) {
        Boolean result = manuscriptService.updateManuscript(manuscriptEntity);
        return result ? CommonResult.success(manuscriptEntity) : CommonResult.failed();
    }

    @ApiOperation("删除稿件")
    @DeleteMapping("{id}")
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.DELETE, operDesc = "删除稿件")
    public CommonResult deleteManuscript(@PathVariable String id) {
        ManuscriptEntity manuscriptEntity = manuscriptService.getById(id);
        if (manuscriptEntity.getStatus().equals(ManuscriptStatus.Committed.getCode()) || manuscriptEntity.getStatus().equals(ManuscriptStatus.Approving.getCode())) {
            throw new ApiException("已提交和审批中的稿件不可删除");
        }
        boolean result = manuscriptService.removeById(id);
        return result ? CommonResult.success(id) : CommonResult.failed();
    }

    @ApiOperation("批量删除稿件")
    @DeleteMapping("batchDelete")
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.DELETE, operDesc = "批量删除稿件")
    public CommonResult batchDeleteManuscript(@RequestParam("ids") String ids) {
        String[] arrayIds = ids.split(",");
        boolean result = manuscriptService.batchRemoveByIds(arrayIds);
        return result?CommonResult.success(ids):CommonResult.failed();
    }

    @ApiOperation("根据id获取稿件信息")
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.GET, operDesc = "根据id获取稿件信息")
    @GetMapping("{id}")
    public CommonResult<ManuscriptVO> getManuscriptById(@PathVariable String id) {
        return CommonResult.success(manuscriptService.findById(id));
    }

    @ApiOperation(value = "提交稿件")
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.UPDATE, operDesc = "提交稿件信息")
    @PostMapping("/submit-manuscript")
    public CommonResult<ManuscriptEntity> submitManuscript(@RequestBody ManuscriptEntity manuscriptEntity,HttpServletRequest request,@RequestParam(value = "attachmentId", required = false) String attachmentId) {
        UserEntity userEntity = tokenManager.getUserInfoByToken(request);
        manuscriptEntity.setContributor(userEntity.getNickName());
        Boolean result =manuscriptService.submitManuscript(manuscriptEntity,attachmentId);
        return result ? CommonResult.success(manuscriptEntity) : CommonResult.failed();
    }

    @ApiOperation(value = "提交稿件")
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.UPDATE, operDesc = "提交稿件信息")
    @PutMapping("/submit-manuscript-by-id")
    public CommonResult<List<ManuscriptEntity>> submitManuscript(@RequestParam("manuscriptIds") String manuscriptIds) {
        String[] ids = manuscriptIds.split(",");
        List<ManuscriptEntity> manuscriptEntityList = manuscriptService.submitManuscript(ids);
        return CommonResult.success(manuscriptEntityList, "提交稿件成功");
    }

    @ApiOperation(value = "回收稿件")
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.UPDATE, operDesc = "回收稿件信息")
    @PutMapping("/cancel-submit-manuscript")
    public CommonResult<List<ManuscriptEntity>> cancelSubmitManuscript(@RequestParam("manuscriptIds") String manuscriptIds) {
        String[] ids = manuscriptIds.split(",");
        List<ManuscriptEntity> manuscriptEntityList = manuscriptService.cancelSubmitManuscript(ids);
        return CommonResult.success(manuscriptEntityList, "回收稿件成功");
    }

    @ApiOperation(value = "审核稿件")
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.UPDATE, operDesc = "审核稿件")
    @PostMapping("/approve-manuscript")
    public CommonResult<UserManuscriptEntity> approveManuscript(HttpServletRequest request,@RequestBody UserApproveVO userApproveVO) {
        UserManuscriptEntity userManuscriptEntity = manuscriptService.approveManuscript(request,userApproveVO);
        return CommonResult.success(userManuscriptEntity, "审核稿件成功");
    }

    @ApiOperation(value = "取消审核稿件")
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.UPDATE, operDesc = "取消审核稿件")
    @PostMapping("/cancel-approve-manuscript")
    public CommonResult<List<ManuscriptEntity>> cancelApproveManuscript(@RequestParam("id") String id) {
        String[] ids = id.split(",");
        List<ManuscriptEntity> manuscriptEntityList = manuscriptService.cancelApproveManuscript(ids);
        return CommonResult.success(manuscriptEntityList, "取消审核稿件成功");
    }


    @ApiOperation(value = "驳回稿件")
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.UPDATE, operDesc = "驳回稿件")
    @PostMapping("/reject-manuscript")
    public CommonResult<List<ManuscriptEntity>> rejectManuscript(@RequestParam("id") String id, @RequestParam("remark") String rejectReason) {
        String[] ids = id.split(",");
        List<ManuscriptEntity> manuscriptEntityList = manuscriptService.rejectManuscript(ids, rejectReason);
        return CommonResult.success(manuscriptEntityList, "驳回稿件成功");
    }

    @ApiOperation(value = "根据用户分配稿件")
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.ASSIGN, operDesc = "根据用户分配稿件")
    @PostMapping("/doAssign")
    public CommonResult doAssign(@RequestParam String manuscriptId, @RequestParam String[] userIds) {
        manuscriptService.saveUserManuscriptRelationShip(manuscriptId,userIds );
        return CommonResult.success(null, "分配成功");
    }

    @ApiOperation("导出")
    @PostMapping("export")
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.EXPORT, operDesc = "导出稿件")
    public CommonResult exportExcel(HttpServletRequest request, HttpServletResponse response, @RequestBody(required = false) ManuscriptSearchForm manuscriptSearchForm) throws IOException {
        manuscriptService.exportExcel(request, response, manuscriptSearchForm);
        return CommonResult.success(null);
    }

    @ApiOperation("导入")
    @RequestMapping(value = "/import", method = {RequestMethod.GET, RequestMethod.POST})
    @OperLog(operModule = "稿件模块", operType = OperTypeConst.IMPORT, operDesc = "导入稿件")
    public CommonResult importManuscriptExcel(MultipartFile file) throws IOException {
        manuscriptService.importManuscriptExcel(file);
        return CommonResult.success(null);
    }

}


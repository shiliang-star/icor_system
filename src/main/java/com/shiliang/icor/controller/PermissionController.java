package com.shiliang.icor.controller;



import com.shiliang.icor.pojo.entity.PermissionEntity;
import com.shiliang.icor.pojo.enums.OperTypeConst;
import com.shiliang.icor.service.PermissionService;
import com.shiliang.icor.utils.OperLog;
import com.shiliang.icor.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @OperLog(operModule = " 菜单模块", operType = OperTypeConst.GET, operDesc = "查询所有菜单")
    @GetMapping
    public Result indexAllPermission() {
        List<PermissionEntity> list =  permissionService.queryAllMenu();
        return Result.ok().data("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @OperLog(operModule = " 菜单模块", operType = OperTypeConst.DELETE, operDesc = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        permissionService.removeChildById(id);
        return Result.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @OperLog(operModule = " 菜单模块", operType = OperTypeConst.ASSIGN, operDesc = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return Result.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @OperLog(operModule = " 菜单模块", operType = OperTypeConst.GET, operDesc = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId) {
        List<PermissionEntity> list = permissionService.selectAllMenu(roleId);
        return Result.ok().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @OperLog(operModule = " 菜单模块", operType = OperTypeConst.ADD, operDesc = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody PermissionEntity permissionEntity) {
        permissionService.save(permissionEntity);
        return Result.ok();
    }

    @ApiOperation(value = "修改菜单")
    @OperLog(operModule = " 菜单模块", operType = OperTypeConst.UPDATE, operDesc = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody PermissionEntity permissionEntity) {
        permissionService.updateById(permissionEntity);
        return Result.ok();
    }

}


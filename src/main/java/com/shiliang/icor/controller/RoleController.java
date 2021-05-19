package com.shiliang.icor.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiliang.icor.pojo.entity.RoleEntity;
import com.shiliang.icor.service.RoleService;
import com.shiliang.icor.utils.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/role")
//@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            RoleEntity roleEntity) {
        Page<RoleEntity> pageParam = new Page<>(page, limit);
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(roleEntity.getRoleName())) {
            wrapper.like("role_name", roleEntity.getRoleName());
        }
        roleService.page(pageParam,wrapper);
        return Result.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    @ApiOperation(value = "获取角色")
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        RoleEntity roleEntity = roleService.getById(id);
        return Result.ok().data("item", roleEntity);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public Result save(@RequestBody RoleEntity roleEntity) {
        roleService.save(roleEntity);
        return Result.ok();
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public Result updateById(@RequestBody RoleEntity roleEntity) {
        roleService.updateById(roleEntity);
        return Result.ok();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        roleService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据id列表删除角色")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        roleService.removeByIds(idList);
        return Result.ok();
    }
}


package com.shiliang.icor.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiliang.icor.pojo.entity.UserEntity;
import com.shiliang.icor.pojo.enums.OperTypeConst;
import com.shiliang.icor.service.RoleService;
import com.shiliang.icor.service.UserService;
import com.shiliang.icor.utils.MD5;
import com.shiliang.icor.utils.OperLog;
import com.shiliang.icor.utils.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/user")
//@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取管理用户分页列表")
    @OperLog(operModule = "用户模块", operType = OperTypeConst.GET, operDesc = "获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    UserEntity userEntityQueryVo) {
        Page<UserEntity> pageParam = new Page<>(page, limit);
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(userEntityQueryVo.getUsername())) {
            wrapper.like("username", userEntityQueryVo.getUsername());
        }

        IPage<UserEntity> pageModel = userService.page(pageParam, wrapper);
        return Result.ok().data("items", pageModel.getRecords()).data("total", pageModel.getTotal());
    }

    @ApiOperation(value = "新增管理用户")
    @OperLog(operModule = "用户模块", operType = OperTypeConst.ADD, operDesc = "新增管理用户")
    @PostMapping("save")
    public Result save(@RequestBody UserEntity userEntity) {
        userEntity.setPassword(MD5.encrypt(userEntity.getPassword()));
        userService.save(userEntity);
        return Result.ok();
    }

    @ApiOperation(value = "修改管理用户")
    @OperLog(operModule = "用户模块", operType = OperTypeConst.UPDATE, operDesc = "修改管理用户")
    @PutMapping("update")
    public Result updateById(@RequestBody UserEntity userEntity) {
        userService.updateById(userEntity);
        return Result.ok();
    }

    @ApiOperation(value = "获取管理用户")
    @OperLog(operModule = "获取管理用户", operType = OperTypeConst.GET, operDesc = "获取管理用户")
    @GetMapping("get/{id}")
    public Result get(@PathVariable() String id) {
        return Result.ok().data("item", userService.getById(id));
    }

    @ApiOperation(value = "删除管理用户")
    @OperLog(operModule = "用户模块", operType = OperTypeConst.DELETE, operDesc = "删除管理用户")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        userService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据id列表删除管理用户")
    @OperLog(operModule = "用户模块", operType = OperTypeConst.DELETE, operDesc = "根据id列表删除管理用户")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return Result.ok();
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @OperLog(operModule = "用户模块", operType = OperTypeConst.GET, operDesc = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return Result.ok().data(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @OperLog(operModule = "用户模块", operType = OperTypeConst.ASSIGN, operDesc = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam String userId,@RequestParam String[] roleId) {
        roleService.saveUserRoleRealtionShip(userId,roleId);
        return Result.ok();
    }

    @ApiOperation(value = "获取角色为审稿员的用户")
    @OperLog(operModule = "用户模块", operType = OperTypeConst.GET, operDesc = "获取角色为审稿员的用户")
    @GetMapping("/getReviewUserList/{manuscriptId}")
    public Result getReviewUserList(@PathVariable("manuscriptId")String manuscriptId) {
        Map<String, Object> roleMap = roleService.getReviewUserList(manuscriptId);
        return Result.ok().data(roleMap);
    }
}


package com.shiliang.icor.controller;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.extension.api.R;
import com.shiliang.icor.pojo.enums.OperTypeConst;
import com.shiliang.icor.service.IndexService;
import com.shiliang.icor.utils.OperLog;
import com.shiliang.icor.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
//@CrossOrigin
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    @OperLog(operModule = "主页模块", operType = OperTypeConst.GET, operDesc = "根据token获取用户信息")
    public Result info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return Result.ok().data(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("menu")
    @OperLog(operModule = "主页模块", operType = OperTypeConst.GET, operDesc = "获取菜单")
    public Result getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return Result.ok().data("permissionList", permissionList);
    }

    @PostMapping("logout")
    @OperLog(operModule = "主页模块", operType = OperTypeConst.GET, operDesc = "登出")
    public Result logout(){
        return Result.ok();
    }

}

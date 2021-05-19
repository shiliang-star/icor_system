package com.shiliang.icor.service.impl;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiliang.icor.mapper.RoleMapper;
import com.shiliang.icor.mapper.UserMapper;
import com.shiliang.icor.pojo.entity.RoleEntity;
import com.shiliang.icor.pojo.entity.UserManuscriptEntity;
import com.shiliang.icor.pojo.entity.UserRoleEntity;
import com.shiliang.icor.pojo.vo.UserVO;
import com.shiliang.icor.service.RoleService;
import com.shiliang.icor.service.UserManuscriptService;
import com.shiliang.icor.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserManuscriptService userManuscriptService;

    @Autowired
    private UserMapper userMapper;


    //根据用户获取角色数据
    @Override
    public Map<String, Object> findRoleByUserId(String userId) {
        //查询所有的角色
        List<RoleEntity> allRolesList =baseMapper.selectList(null);

        //根据用户id，查询用户拥有的角色id
        List<UserRoleEntity> existUserRoleEntityList = userRoleService.list(new QueryWrapper<UserRoleEntity>().eq("user_id", userId).select("role_id"));

        List<String> existRoleList = existUserRoleEntityList.stream().map(c->c.getRoleId()).collect(Collectors.toList());

        //对角色进行分类
        List<RoleEntity> assignRoleEntities = new ArrayList<RoleEntity>();
        for (RoleEntity roleEntity : allRolesList) {
            //已分配
            if(existRoleList.contains(roleEntity.getId())) {
                assignRoleEntities.add(roleEntity);
            }
        }

        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoles", assignRoleEntities);
        roleMap.put("allRolesList", allRolesList);
        return roleMap;
    }

    //根据用户分配角色
    @Override
    public void saveUserRoleRealtionShip(String userId, String[] roleIds) {
        userRoleService.remove(new QueryWrapper<UserRoleEntity>().eq("user_id", userId));

        List<UserRoleEntity> userRoleEntityList = new ArrayList<>();
        for(String roleId : roleIds) {
            if(StringUtils.isEmpty(roleId)) continue;
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userId);
            userRoleEntity.setRoleId(roleId);

            userRoleEntityList.add(userRoleEntity);
        }
        userRoleService.saveBatch(userRoleEntityList);
    }

    @Override
    public List<RoleEntity> selectRoleByUserId(String id) {
        //根据用户id拥有的角色id
        List<UserRoleEntity> userRoleEntityList = userRoleService.list(new QueryWrapper<UserRoleEntity>().eq("user_id", id).select("role_id"));
        List<String> roleIdList = userRoleEntityList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
        List<RoleEntity> roleEntityList = new ArrayList<>();
        if(roleIdList.size() > 0) {
            roleEntityList = baseMapper.selectBatchIds(roleIdList);
        }
        return roleEntityList;
    }

    @Override
    public Map<String, Object> getReviewUserList(String manuscriptId) {
        Assert.notNull(manuscriptId, "稿件主键不能为空");
        //查询所有拥有审稿员角色的用户
        List<UserVO> userEntityList = userMapper.searchAllUserWithReview();
        //查询已分配该稿件的用户
        List<UserVO> userManuscriptEntities = userMapper.searchUserAssignManuscript(manuscriptId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("assignUsers", userManuscriptEntities);
        resultMap.put("allUsersList", userEntityList);
        return resultMap;
    }
}

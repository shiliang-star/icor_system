package com.shiliang.icor.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shiliang.icor.pojo.entity.RoleEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface RoleService extends IService<RoleEntity> {

    //根据用户获取角色数据
    Map<String, Object> findRoleByUserId(String userId);

    //根据用户分配角色
    void saveUserRoleRealtionShip(String userId, String[] roleId);

    List<RoleEntity> selectRoleByUserId(String id);

    Map<String, Object> getReviewUserList(String manuscriptId);

}

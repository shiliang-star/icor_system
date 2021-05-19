package com.shiliang.icor.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shiliang.icor.pojo.entity.UserEntity;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface UserService extends IService<UserEntity> {

    // 从数据库中取出用户信息
    UserEntity selectByUsername(String username);
}

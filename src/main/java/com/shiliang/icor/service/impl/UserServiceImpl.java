package com.shiliang.icor.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiliang.icor.mapper.UserMapper;
import com.shiliang.icor.pojo.entity.UserEntity;
import com.shiliang.icor.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Override
    public UserEntity selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
    }
}

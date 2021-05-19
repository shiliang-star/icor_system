package com.shiliang.icor.service.impl;


import com.shiliang.icor.pojo.entity.SecurityUser;
import com.shiliang.icor.pojo.entity.UserEntity;
import com.shiliang.icor.service.PermissionService;
import com.shiliang.icor.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        UserEntity userEntity = userService.selectByUsername(username);

        // 判断用户是否存在
        if (null == userEntity){
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        UserEntity curUserEntity = new UserEntity();
        BeanUtils.copyProperties(userEntity, curUserEntity);

        List<String> authorities = permissionService.selectPermissionValueByUserId(userEntity.getId());
        SecurityUser securityUser = new SecurityUser(curUserEntity);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }

}

package com.shiliang.icor.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiliang.icor.pojo.entity.PermissionEntity;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface PermissionMapper extends BaseMapper<PermissionEntity> {


    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<PermissionEntity> selectPermissionByUserId(String userId);
}

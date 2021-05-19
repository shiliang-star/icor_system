package com.shiliang.icor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiliang.icor.pojo.entity.UserEntity;
import com.shiliang.icor.pojo.vo.UserVO;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    List<UserVO> searchAllUserWithReview();

    List<UserVO> searchUserAssignManuscript(String manuscriptId);
}

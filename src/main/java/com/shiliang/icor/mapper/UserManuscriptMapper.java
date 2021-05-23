package com.shiliang.icor.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiliang.icor.pojo.entity.UserManuscriptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiliang.icor.pojo.vo.ManuscriptVO;
import com.shiliang.icor.pojo.vo.UserManuscriptVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ShiLiang
 * @since 2021-04-25
 */
public interface UserManuscriptMapper extends BaseMapper<UserManuscriptEntity> {


    /**
     * 自定义分页查询
     * @param page
     * @param queryWrapper
     * @return
     */
    Page<UserManuscriptVO> page(Page<UserManuscriptVO> page, @Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    List<UserManuscriptVO> searchByManuscriptId(String manuscriptId);

    UserManuscriptVO searchByManuscriptIdAndReviewer(String userId,String manuscriptId);
}

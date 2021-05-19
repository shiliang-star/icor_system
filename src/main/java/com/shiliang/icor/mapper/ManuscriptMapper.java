package com.shiliang.icor.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiliang.icor.pojo.entity.ManuscriptEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiliang.icor.pojo.vo.ManuscriptVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-23
 */
public interface ManuscriptMapper extends BaseMapper<ManuscriptEntity> {


    List<ManuscriptEntity> findByIdIn(@Param("ids") String[] ids);

    /**
     * 自定义分页查询
     * @param page
     * @param queryWrapper
     * @return
     */
    Page<ManuscriptVO> page(Page<ManuscriptVO> page, @Param(Constants.WRAPPER) QueryWrapper queryWrapper);

}

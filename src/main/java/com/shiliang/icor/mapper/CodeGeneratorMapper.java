package com.shiliang.icor.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiliang.icor.pojo.entity.CodeGeneratorEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-28
 */
public interface CodeGeneratorMapper extends BaseMapper<CodeGeneratorEntity> {

    @Select("select serialCode from t_code_generator where creation_time<=#{date} and type =#{type} order by creation_time desc limit 1")
    String findLatestSerialCode(@Param("type") String type,@Param("date") Date date);
}

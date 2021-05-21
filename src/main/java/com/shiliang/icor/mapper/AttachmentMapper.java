package com.shiliang.icor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.shiliang.icor.pojo.entity.AttachmentEntity;
import com.shiliang.icor.pojo.vo.AttachmentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-25
 */
@Mapper
public interface AttachmentMapper extends BaseMapper<AttachmentEntity> {

    List<AttachmentVO> findByEntityTypeAndEntityId(String entityType, String entityId);

}

package com.shiliang.icor.pojo.entity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.shiliang.icor.pojo.enums.SortEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @Author: sl
 */
@Data
public  abstract class BaseSorter implements Serializable {
    /**
     * 查询对象
     * @param queryWrapper
     */
    public abstract void queryWrapper(QueryWrapper queryWrapper);

    public void sort(QueryWrapper queryWrapper, Serializable column, SortEnum sortEnum){
        if (sortEnum != null && sortEnum == SortEnum.DESC) {
            queryWrapper.orderByDesc(column);
        }else if(sortEnum != null && sortEnum == SortEnum.ASC) {
            queryWrapper.orderByAsc(column);
        }
    }
}

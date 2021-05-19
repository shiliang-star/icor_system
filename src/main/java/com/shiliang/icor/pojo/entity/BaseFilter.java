package com.shiliang.icor.pojo.entity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.io.Serializable;

/**
 * @description:
 * @Author: sl
 */
public abstract  class BaseFilter implements Serializable {
    /**
     * 查询对象
     * @param queryWrapper
     */
    public abstract void queryWrapper(QueryWrapper queryWrapper);

    public void bt(QueryWrapper queryWrapper,String column,Object begin,Object end){
        // amount 筛选
        if (begin != null && end != null) {
            queryWrapper.between(column, begin, end);
        } else if (begin != null) {
            queryWrapper.ge(column, begin);
        } else if (end!= null) {
            queryWrapper.le(column, end);
        }
    }
}

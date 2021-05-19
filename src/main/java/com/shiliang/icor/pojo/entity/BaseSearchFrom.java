package com.shiliang.icor.pojo.entity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.io.Serializable;

/**
 * @description:
 * @Author: sl
 */
public abstract class BaseSearchFrom implements Serializable {
    /**
     * 生成查询对象
     * @return
     */
    public abstract QueryWrapper queryWrapper();

    protected QueryWrapper queryWrapper(QueryWrapper queryWrapper, BaseFilter filter, BaseSorter sorter){
        if (filter != null) {
            filter.queryWrapper(queryWrapper);
        }
        if (sorter != null) {
            sorter.queryWrapper(queryWrapper);
        }
        return queryWrapper;
    }
}

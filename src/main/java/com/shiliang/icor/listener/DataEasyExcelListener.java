package com.shiliang.icor.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName DataEasyExcelListener.java
 * @Description excel数据监视器
 * @createTime 2021年05月15日 12:57:00
 */
@Slf4j
public class DataEasyExcelListener<T> extends AnalysisEventListener<T>{
    private List<T> list = new ArrayList<>();

    @Override
    public void invoke(T data, AnalysisContext context) {
        log.info("解析到一条数据:{}", data);
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！");
    }

    public List<T> getData() {
        return list;
    }
}

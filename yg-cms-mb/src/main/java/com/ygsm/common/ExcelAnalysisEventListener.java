package com.ygsm.common;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ExcelAnalysisEventListener<T> extends AnalysisEventListener<T> {

    @Getter
    List<T> list = new ArrayList<>();

    @Override
    public void invoke(T data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        log.error("Excel处理异常：" + exception.getMessage());
        throw exception;
    }

}

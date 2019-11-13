package com.example.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.pojo.DemoData;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Log4j
public class DemoDataListener extends AnalysisEventListener<DemoData> {

    @Getter
    private List<DemoData> list = new ArrayList<DemoData>();
    
    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }
    
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析到一条头数据:{}"+ headMap);
    }

}

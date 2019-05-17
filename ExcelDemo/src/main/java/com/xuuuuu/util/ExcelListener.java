package com.xuuuuu.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener {

    private List<Object> datas = new ArrayList<Object>();

    public void invoke(Object o, AnalysisContext analysisContext) {
        datas.add(o);
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

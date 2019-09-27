package com.sptpc.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

//easyexcel的数据处理中心
public class ExcelListener extends AnalysisEventListener {

    private List<Object> datas = new ArrayList<Object>();

    public void invoke(Object o, AnalysisContext analysisContext) {
        System.out.println("当前行："+analysisContext.getCurrentRowNum());
        System.out.println(o);
        datas.add(o);
    }

    public List<Object> getDatas(){
        return datas;
    }

    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

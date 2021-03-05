package com.hogwarts.eduservice.excle;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/15
 */
public class ExcelListener extends AnalysisEventListener<DemoDataRead> {
    //一行一行读取excel内容
    @Override
    public void invoke(DemoDataRead demoDataRead, AnalysisContext analysisContext) {
        System.out.println(""+demoDataRead);
    }

    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头"+headMap);
    }

    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

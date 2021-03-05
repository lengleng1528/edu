package com.hogwarts.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hogwarts.eduservice.entity.EduSubject;
import com.hogwarts.eduservice.entity.excel.SubjectData;
import com.hogwarts.eduservice.service.EduSubjectService;
import com.hogwarts.servicebase.exceptionhandler.MyException;

import java.util.Map;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/15
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
    //不能实现数据库操作
    public EduSubjectService subjectService;

    public SubjectExcelListener() {}

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData==null){
            throw new MyException(20001,"文件数据为空");
        }
        //添加一级分类
        EduSubject eduSubject = this.exisitOneSbuject(subjectService, subjectData.getFirst());
        if(eduSubject==null){
            eduSubject=new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(subjectData.getFirst());
            subjectService.save(eduSubject);
        }

        String pid = eduSubject.getId();
            //添加二级分类
            EduSubject secondSubject = this.existSecondSubject(subjectService, subjectData.getSecond(), pid);
            if(secondSubject==null){
                secondSubject=new EduSubject();
                secondSubject.setTitle(subjectData.getSecond());
                secondSubject.setParentId(pid);
            subjectService.save(secondSubject);
        }


    }

    //判断一级分类不能重复添加
    public EduSubject exisitOneSbuject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",0);
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }

    //判断二级分类不能重复添加
    public EduSubject existSecondSubject(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject second = eduSubjectService.getOne(wrapper);
        return second;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

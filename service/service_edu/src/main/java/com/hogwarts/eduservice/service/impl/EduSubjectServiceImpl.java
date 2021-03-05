package com.hogwarts.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hogwarts.eduservice.entity.EduSubject;
import com.hogwarts.eduservice.entity.excel.SubjectData;
import com.hogwarts.eduservice.entity.subject.OneSubject;
import com.hogwarts.eduservice.entity.subject.SecondSubject;
import com.hogwarts.eduservice.listener.SubjectExcelListener;
import com.hogwarts.eduservice.mapper.EduSubjectMapper;
import com.hogwarts.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-01-15
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //课程分类列表，树形
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1.查询所有一级分类
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id","0");
        List<EduSubject> list = baseMapper.selectList(wrapper);

        //2.查询所有二级分类
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id","0");
        List<EduSubject> list2 = baseMapper.selectList(wrapper2);

        //3.封装一级分类
        List<OneSubject> finalList = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            EduSubject subject = list.get(i);
            OneSubject oneSubject = new OneSubject();
            //oneSubject.setId(subject.getId());
            //oneSubject.setTitle(subject.getTitle());
            BeanUtils.copyProperties(subject,oneSubject);
            finalList.add(oneSubject);
            //在一级分类的基础上循环遍历二级分类
            //4.封装二级分类
            List<SecondSubject> secondFinalList = new ArrayList<>();
            for (int m = 0; m < list2.size(); m++) {
                EduSubject second = list2.get(m);
                if(second.getParentId().equals(oneSubject.getId())){
                    SecondSubject secondSubject = new SecondSubject();
                    BeanUtils.copyProperties(second,secondSubject);
                    secondFinalList.add(secondSubject);
                }
            }
            oneSubject.setChildren(secondFinalList);

        }

        return finalList;

    }
}

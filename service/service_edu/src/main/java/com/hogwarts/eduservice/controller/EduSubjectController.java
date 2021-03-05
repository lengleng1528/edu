package com.hogwarts.eduservice.controller;


import com.hogwarts.commonutils.Res;
import com.hogwarts.eduservice.entity.subject.OneSubject;
import com.hogwarts.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-15
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    //获取上传的文件，把文件内容读取出来
    @PostMapping("/addSubject")
    public Res addSubject(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return Res.ok();
    }

    //课程分类的列表显示
    //树形结构显示
    @GetMapping("/getAllSubject")
    public Res getSbuject(){
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return Res.ok().data("list",list);
    }
}


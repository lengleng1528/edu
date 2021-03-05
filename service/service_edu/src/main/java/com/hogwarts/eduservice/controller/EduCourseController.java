package com.hogwarts.eduservice.controller;


import com.hogwarts.commonutils.Res;
import com.hogwarts.eduservice.entity.EduCourse;
import com.hogwarts.eduservice.entity.vo.CourseInfoVo;
import com.hogwarts.eduservice.entity.vo.PublishVo;
import com.hogwarts.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-18
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;
    //课程列表 基本实现
    //TODO  完善条件查询带分页

    @GetMapping
    public Res getCourseList() {
        List<EduCourse> list = eduCourseService.list(null);
        return Res.ok().data("list",list);
    }

    //添加课程基本信息
    @PostMapping("addCourseInfo")
    public Res addCourse(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return Res.ok().data("courseId",id);
    }

    //查询课程基本信息
    @GetMapping("/getCourseInfo/{courseId}")
    public Res getInfo(@PathVariable String courseId){
        CourseInfoVo courseInfo = eduCourseService.getCourseInfo(courseId);
        return Res.ok().data("courseInfo",courseInfo);
    }

    //修改课程基本信息
    @PostMapping("/updateCourseInfo")
    public Res update(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return Res.ok();
    }

    //根据课程的id查询课程的确认信息
    @GetMapping("/getPublish/{courseId}")
    public Res get(@PathVariable String courseId){
        PublishVo publish = eduCourseService.getPublishVo(courseId);
        return Res.ok().data("publish",publish);
    }

    //课程最终发布--修改课程的status
    @PostMapping("publishCourse/{courseId}")
    public Res publish(@PathVariable String courseId){
        System.out.println("课程最终发布");
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus("Normal");
        eduCourseService.updateById(course);
        return Res.ok();
    }

    @DeleteMapping("/deleteCourse/{id}")
    public Res delete(@PathVariable String id){
        eduCourseService.removeCourse(id);
        return Res.ok();
    }
}


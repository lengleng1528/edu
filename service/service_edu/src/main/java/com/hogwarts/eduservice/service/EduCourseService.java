package com.hogwarts.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hogwarts.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hogwarts.eduservice.entity.frontvo.CourseFrontVo;
import com.hogwarts.eduservice.entity.frontvo.CourseWebVo;
import com.hogwarts.eduservice.entity.vo.CourseInfoVo;
import com.hogwarts.eduservice.entity.vo.PublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-01-18
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    PublishVo getPublishVo(String courseId);

    void removeCourse(String id);

    CourseWebVo getBaseCourseInfo(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);
}

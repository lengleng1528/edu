package com.hogwarts.eduservice.mapper;

import com.hogwarts.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hogwarts.eduservice.entity.frontvo.CourseWebVo;
import com.hogwarts.eduservice.entity.vo.PublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-01-18
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public PublishVo getPublishVo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}

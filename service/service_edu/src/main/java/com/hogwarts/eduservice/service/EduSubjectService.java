package com.hogwarts.eduservice.service;

import com.hogwarts.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hogwarts.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-01-15
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类

    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();
}

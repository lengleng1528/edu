package com.hogwarts.eduservice.entity.vo;

import lombok.Data;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/22
 */
@Data
public class PublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}

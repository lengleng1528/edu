package com.hogwarts.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/18
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<SecondSubject> children = new ArrayList<>();
}

package com.hogwarts.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/21
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    //表示小节
    List<VideoVo> list = new ArrayList<>();
}

package com.hogwarts.eduservice.controller;


import com.hogwarts.commonutils.Res;
import com.hogwarts.eduservice.entity.EduChapter;
import com.hogwarts.eduservice.entity.chapter.ChapterVo;
import com.hogwarts.eduservice.service.EduChapterService;
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
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("/getAllChapter/{courseId}")
    public Res getAllChapter(@PathVariable String courseId){
        List<ChapterVo> chapters = eduChapterService.getChapterAndVideoByCourseId(courseId);
        System.out.println("查询所有章节方法调用了");
        return Res.ok().data("chapters",chapters);
    }

    //添加章节
    @PostMapping("/addChapter")
    public Res add(@RequestBody EduChapter chapter){
        eduChapterService.save(chapter);
        return Res.ok();
    }

    //根据章节id查询
    @GetMapping("/getChapter/{chapterId}")
    public Res get(@PathVariable String chapterId){
        EduChapter chapter = eduChapterService.getById(chapterId);
        return Res.ok().data("chapter",chapter);
    }

    //修改章节信息
    @PostMapping("/updateChapter")
    public Res update(@RequestBody EduChapter chapter){
        eduChapterService.updateById(chapter);
        return Res.ok();
    }

    //删除章节
    @DeleteMapping("delete/{chapterId}")
    public Res delete(@PathVariable String chapterId){
        System.out.println("删除章节");
        boolean flag = eduChapterService.deleteChapter(chapterId);
        System.out.println(flag);
        if(flag){
            return Res.ok();
        }else {
            return Res.error();
        }

    }
}


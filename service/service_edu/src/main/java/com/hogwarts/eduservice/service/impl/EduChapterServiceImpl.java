package com.hogwarts.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hogwarts.eduservice.entity.EduChapter;
import com.hogwarts.eduservice.entity.EduVideo;
import com.hogwarts.eduservice.entity.chapter.ChapterVo;
import com.hogwarts.eduservice.entity.chapter.VideoVo;
import com.hogwarts.eduservice.mapper.EduChapterMapper;
import com.hogwarts.eduservice.service.EduChapterService;
import com.hogwarts.eduservice.service.EduVideoService;
import com.hogwarts.servicebase.exceptionhandler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-01-18
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<ChapterVo> getChapterAndVideoByCourseId(String courseId) {

        //1.根据课程id查询所有chapter
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);

        //2.根据课程id查询所有的video
        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(wrapper1);

        //3.封装所有章节
        List<ChapterVo> chapters = new ArrayList<>();
        for(int i = 0;i<eduChapters.size();i++){
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapters.get(i),chapterVo);
            chapters.add(chapterVo);
            //4.封装所有的video
            List<VideoVo> videos = new ArrayList<>();
            for(int m = 0;m<eduVideos.size();m++){
                EduVideo eduVideo = eduVideos.get(m);
                if(eduVideo.getChapterId().equals(chapterVo.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videos.add(videoVo);
                }
            }
            chapterVo.setList(videos);
        }

        return chapters;
    }

    @Override
    public boolean deleteChapter(String chapterId){
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(wrapper);
        if(count>0){
            throw new MyException(20001,"不能删除");
        }else {
            int i = baseMapper.deleteById(chapterId);
            System.out.println(chapterId);
            return i>0;
        }

    }

    @Override
    public void removeChapterByCourseId(String id) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }

}

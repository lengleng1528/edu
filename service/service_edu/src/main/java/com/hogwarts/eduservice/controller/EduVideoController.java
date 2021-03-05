package com.hogwarts.eduservice.controller;


import com.hogwarts.commonutils.Res;
import com.hogwarts.eduservice.client.VodClient;
import com.hogwarts.eduservice.entity.EduVideo;
import com.hogwarts.eduservice.service.EduVideoService;
import com.hogwarts.servicebase.exceptionhandler.MyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-18
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    
    @Autowired
    private EduVideoService eduVideoService;

    //注入vodClient
    @Autowired
    private VodClient vodClient;
    
    //添加小节
    @PostMapping("/addVideo")
    public Res add(@RequestBody EduVideo video){
        eduVideoService.save(video);
        return Res.ok();
    }

    //删除小节
    //TODO :删除小节的时候需要把后面的视频也进行删除
    @DeleteMapping("/delete/{videoId}")
    public Res delete(@PathVariable String videoId){
        //1.根据小节id查询视频id
        EduVideo video = eduVideoService.getById(videoId);
        String videoSourceId = video.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)){
            //根据视频id远程调用实现视频删除
            Res result = vodClient.delete(videoSourceId);
            if(result.getCode() == 20001) {
                throw new MyException(20001,"删除视频失败，熔断器...");
            }
        }
        System.out.println("删除方法调用了");
        //删除小节
        eduVideoService.removeById(videoId);
        return Res.ok();
    }

    //修改小节 TODO

}


package com.hogwarts.eduservice.client;

import com.hogwarts.commonutils.Res;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/24
 */
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {
    //定义调用的方法路径
    //根据视频id删除阿里云视频
    //@PathVariable注解一定要指定参数名称，否则出错，名称必须和路径变量一样
    @DeleteMapping("/eduvod/video/removeVideo/{videoSourceId}")
    public Res delete(@PathVariable("videoSourceId") String id);

    //定义调用删除多个视频的方法
    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("/eduvod/video/delete-batch")
    public Res deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}

package com.hogwarts.eduservice.client;

import com.hogwarts.commonutils.Res;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/25
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    //出错之后会执行
    @Override
    public Res delete(String id) {
        return Res.error().message("删除视频出错了");
    }

    @Override
    public Res deleteBatch(List<String> videoIdList) {
        return Res.error().message("删除多个视频出错了");
    }
}

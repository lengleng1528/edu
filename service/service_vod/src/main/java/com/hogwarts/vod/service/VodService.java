package com.hogwarts.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/23
 */
public interface VodService {
    String uploadVideo(MultipartFile file);

    void removeMoreAlyVideo(List<String> videoIdList);
}

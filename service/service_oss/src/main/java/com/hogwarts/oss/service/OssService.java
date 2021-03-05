package com.hogwarts.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/14
 */

public interface OssService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}

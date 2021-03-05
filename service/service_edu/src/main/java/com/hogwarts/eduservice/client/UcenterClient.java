package com.hogwarts.eduservice.client;

import com.hogwarts.commonutils.commentvo.UcenterMemberComment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/2/6
 */
@FeignClient("service-ucenter")
@Component
public interface UcenterClient {

    //根据用户id获取用户信息，用于生成评论
    @PostMapping("/educenter/member/getUserInfo/{id}")
    public UcenterMemberComment getInfo(@PathVariable(name = "id") String id);
}

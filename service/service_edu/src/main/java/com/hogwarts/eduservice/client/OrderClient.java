package com.hogwarts.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/30
 */
@FeignClient(name ="service-order",fallback = OrderFeignClient.class)
@Component
public interface OrderClient {
    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    boolean isBuyCourse(@PathVariable(name = "courseId") String courseId, @PathVariable(name = "memberId") String memberId);
}

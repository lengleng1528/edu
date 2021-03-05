package com.hogwarts.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/25
 */
@Component
public class OrderFeignClient implements OrderClient {

    //出错之后会执行
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        System.out.println("查询购买信息出错");
        return false;
    }

}

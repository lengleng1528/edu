package com.hogwarts.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hogwarts.commonutils.JwtUtils;
import com.hogwarts.commonutils.Res;
import com.hogwarts.eduorder.entity.TOrder;
import com.hogwarts.eduorder.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-29
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class TOrderController {

    @Autowired
    private TOrderService orderService;

    //1 生成订单的方法
    @PostMapping("createOrder/{courseId}")
    public Res saveOrder(@PathVariable String courseId, HttpServletRequest request) {
        //创建订单，返回订单号
        String orderNo =
                orderService.createOrders(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return Res.ok().data("orderId",orderNo);
    }

    //根据课程id和用户id查询订单表中的订单状态
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId,@PathVariable String memberId){
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        int count = orderService.count(wrapper);
        if(count>0){
            return true;
        }else {
            return false;
        }

    }



    //2 根据订单id查询订单
    @GetMapping("/getOrderInfo/{orderId}")
    public Res getOrderInfo(@PathVariable String orderId){
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        TOrder order = orderService.getOne(wrapper);
        return Res.ok().data("item",order);
    }
}


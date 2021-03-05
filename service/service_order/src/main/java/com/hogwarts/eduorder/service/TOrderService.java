package com.hogwarts.eduorder.service;

import com.hogwarts.eduorder.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-01-29
 */
public interface TOrderService extends IService<TOrder> {

    String createOrders(String courseId, String memberId);
}

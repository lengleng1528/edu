package com.hogwarts.eduorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hogwarts.commonutils.ordervo.CourseWebVoOrder;
import com.hogwarts.commonutils.ordervo.UcenterMemberOrder;
import com.hogwarts.eduorder.client.EduClient;
import com.hogwarts.eduorder.client.UcenterClient;
import com.hogwarts.eduorder.entity.TOrder;
import com.hogwarts.eduorder.mapper.TOrderMapper;
import com.hogwarts.eduorder.service.TOrderService;
import com.hogwarts.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-01-29
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrders(String courseId, String memberId) {
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);
        TOrder order = new TOrder();

        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);

        //返回订单号
        return order.getOrderNo();

    }
}

package com.hogwarts.educenter.controller;


import com.hogwarts.commonutils.JwtUtils;
import com.hogwarts.commonutils.Res;
import com.hogwarts.commonutils.commentvo.UcenterMemberComment;
import com.hogwarts.commonutils.ordervo.UcenterMemberOrder;
import com.hogwarts.educenter.entity.UcenterMember;
import com.hogwarts.educenter.entity.vo.RegisterVo;
import com.hogwarts.educenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-27
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    public Res loginUser(@RequestBody UcenterMember member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return Res.ok().data("token",token);
    }

    //注册
    @PostMapping("register")
    public Res registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return Res.ok();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public Res getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return Res.ok().data("userInfo",member);
    }

    //根据用户id获取用户信息--用于生成订单
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        //把member对象里面值复制给UcenterMemberOrder对象
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    //统计某一天注册的人数
    @GetMapping("countRegister/{day}")
    public Res registerCount(@PathVariable String day){
       Integer count = memberService.countRegister(day);
       return Res.ok().data("countRegister",count);
    }

    //根据用户id获取用户信息，用于生成评论
    @PostMapping("/getUserInfo/{id}")
    public UcenterMemberComment getInfo(@PathVariable String id){
        UcenterMemberComment member1 = new UcenterMemberComment();
        UcenterMember member = memberService.getById(id);
        BeanUtils.copyProperties(member,member1);
        member1.setMemberId(member.getId());
        return member1;

    }
}


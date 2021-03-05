package com.hogwarts.eduservice.controller;

import com.hogwarts.commonutils.Res;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/13
 */
//@CrossOrigin//解决跨域
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {
    @PostMapping("login")
    public Res login(){
        return Res.ok().data("token","admin");
    }

    @GetMapping("info")
    public Res info(){
        return Res.ok().data("roles","[admin]").data("name","admin").data("avator","https://picsum.photos/200/300");
    }
}

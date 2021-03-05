package com.hogwarts.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hogwarts.commonutils.JwtUtils;
import com.hogwarts.commonutils.Res;
import com.hogwarts.commonutils.commentvo.UcenterMemberComment;
import com.hogwarts.eduservice.client.UcenterClient;
import com.hogwarts.eduservice.entity.EduComment;
import com.hogwarts.eduservice.service.EduCommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-04
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class EduCommentController{
    @Autowired
    private EduCommentService commentService;

    @Autowired
    private UcenterClient client;

    //分页查询课程评论的方法
    @GetMapping("{current}/{limit}")
    public Res querry(String courseId,@PathVariable Long current,@PathVariable Long limit){
        Page<EduComment> page = new Page<>(current,limit);
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        commentService.page(page, wrapper);
        List<EduComment> comments = page.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("items", comments);
        map.put("current", page.getCurrent());
        map.put("pages", page.getPages());
        map.put("size", page.getSize());
        map.put("total", page.getTotal());
        map.put("hasNext", page.hasNext());
        map.put("hasPrevious", page.hasPrevious());

        return Res.ok().data(map);
    }

    @PostMapping("addComments")
    public Res add(@RequestBody EduComment comment,HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)){
            return Res.error().code(20001).message("用户未登录");
        }
        UcenterMemberComment member = client.getInfo(memberId);
        BeanUtils.copyProperties(member,comment);
        //comment.setAvatar(member.getAvatar());

        commentService.save(comment);
        return Res.ok();
    }
}


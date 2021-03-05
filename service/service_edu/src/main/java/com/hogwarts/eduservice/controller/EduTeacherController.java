package com.hogwarts.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hogwarts.commonutils.Res;
import com.hogwarts.eduservice.entity.EduTeacher;
import com.hogwarts.eduservice.entity.vo.TeacherQuery;
import com.hogwarts.eduservice.service.EduTeacherService;
import com.hogwarts.servicebase.exceptionhandler.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-11
 */
@CrossOrigin
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;
    //1.查询讲师表中的所有数据--rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public Res selectAll(){
        List<EduTeacher> teachers = teacherService.list(null);
        return Res.ok().data("items",teachers);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("delete/{id}")
    public Res delete(@ApiParam(name = "id", value = "讲师ID", required = true)@PathVariable String id){
        System.out.println("逻辑删除讲师方法执行");
        boolean flag = teacherService.removeById(id);
        if(flag) {
            return Res.ok();
        }
        return Res.error();
    }

    @GetMapping("pageTeacher/{current}/{limit}")
    public Res pageistTeacher(@PathVariable long current,@PathVariable long limit){
        Page<EduTeacher> page = new Page<>(current,limit);
        try{
            //int i = 10/0;
        }catch (Exception e){
            throw new MyException(200001,"出现自定义异常");
        }
        //调用方法实现分页
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        teacherService.page(page,null);

        long total = page.getTotal();//总记录数
        List<EduTeacher> records = page.getRecords();
//        Map<String,Object> map = new HashMap<>();
//        map.put("total",total);
//        map.put("rows",records);
//        return Res.ok().data(map);
        return Res.ok().data("total",total).data("rows",records);
    }

    //4 条件查询带分页的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public  Res pageTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery){
        System.out.println("条件查询方法执行");
        //创建page对象
        Page<EduTeacher> page = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        // mybatis学过 动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }
        //排序功能
        wrapper.orderByDesc("gmt_create");

        teacherService.page(page,wrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return Res.ok().data("total",total).data("rows",records);

    }

    //添加讲师接口的方法
    @PostMapping("addTeacher")
    public Res addTeacher(@RequestBody EduTeacher eduTeacher) {
        System.out.println("添加讲师方法执行");
        boolean save = teacherService.save(eduTeacher);
        if(save) {
            return Res.ok();
        } else {
            return Res.error();
        }
    }

    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public Res getTeacher(@PathVariable String id) {
        System.out.println("查询讲师方法执行");
        EduTeacher eduTeacher = teacherService.getById(id);
        return Res.ok().data("teacher",eduTeacher);
    }

    //讲师修改功能
    @PostMapping("updateTeacher")
    public Res updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag) {
            return Res.ok();
        } else {
            return Res.error();
        }
    }
}


package com.hogwarts.servicebase.exceptionhandler;

import com.hogwarts.commonutils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/12
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //指定出现什么异常时执行方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回数据
    public Res error(Exception e) {
        e.printStackTrace();
        return Res.error().message("执行了全局异常处理");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//返回数据
    public Res error(ArithmeticException e) {
        e.printStackTrace();
        return Res.error().message("执行了ArithmeticException异常处理");
    }

    //自定义异常
    @ExceptionHandler(MyException.class)
    @ResponseBody//返回数据
    public Res error(MyException e) {
        log.error(e.getMessage());//将错误输出到日志文件
        e.printStackTrace();
        return Res.error().message(e.getMsg()).code(e.getCode());
    }
}

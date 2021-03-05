package com.hogwarts.eduservice.excle;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/15
 */
@Data
public class DemoData {
    //设置excle表头名称
    @ExcelProperty("学生编号")
    private Integer sno;
    @ExcelProperty("学生姓名")
    private String name;
}

package com.hogwarts.eduservice.excle;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/15
 */
@Data
public class DemoDataRead {
    //设置excle表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String name;

}

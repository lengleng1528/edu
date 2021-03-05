package com.hogwarts.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/15
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String first;
    @ExcelProperty(index = 1)
    private String second;
}

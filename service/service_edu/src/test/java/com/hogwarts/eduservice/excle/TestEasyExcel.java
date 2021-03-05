package com.hogwarts.eduservice.excle;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Leng
 * @Description
 * @date 2021/1/15
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        TestEasyExcel t = new TestEasyExcel();
        //t.write();
        t.read();

    }
    //实现excel写操作
    private void write(){
        //1.设置写入文件夹地址和excel文件名称
        String fileName = "F:\\write.xlsx";
        //2.调用easyExcel里面的方法实现写操作
        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());
    }
    //实现excel读操作
    private void read(){
        //1.设置读入文件夹地址和excel文件名称
        String fileName = "F:\\write.xlsx";
        //2.调用easyExcel里面的方法实现写操作
        EasyExcel.read(fileName,DemoDataRead.class,new ExcelListener()).sheet().doRead();
    }
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for(int i = 0;i<10;i++){
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setName("lucy"+i);
            list.add(demoData);
        }
        return list;
    }
}

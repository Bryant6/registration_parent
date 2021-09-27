package com.wang.easyexcel;

import com.alibaba.excel.EasyExcel;

/**
 * @author wangyu
 * @date 2021/9/27 14:58
 */
public class TestRead {
    public static void main(String[] args) {
        String fileName = "E:\\workspace\\IdeaProject\\registration_parent\\service\\service_cmn\\src\\test\\java\\com\\wang\\easyexcel\\01.xlsx";
        EasyExcel.read(fileName,UserData.class,new ExcelListener()).sheet().doRead();
    }
}

package com.wang.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyu
 * @date 2021/9/27 14:45
 */
public class TestWrite {
    public static void main(String[] args) {
        List<UserData> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            UserData data = new UserData();
            data.setUid(i);
            data.setUsername("luck" + i);
            list.add(data);
        }
        //设置Excel文件路径和名称
        String fileName = "E:\\workspace\\IdeaProject\\registration_parent\\service\\service_cmn\\src\\test\\java\\com\\wang\\easyexcel\\01.xlsx";

        EasyExcel.write(fileName,UserData.class).sheet("用户信息")
                    .doWrite(list);
    }
}

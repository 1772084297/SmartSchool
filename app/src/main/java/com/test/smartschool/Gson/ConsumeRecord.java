package com.test.smartschool.Gson;

// TODO: 2018/9/6  数据格式未定义，先假设为单条String数据
public class ConsumeRecord {
    
    private String data;
    
    public ConsumeRecord(String data){
        this.data = data;
    }

    public String getData(){
        return this.data;
    }
}

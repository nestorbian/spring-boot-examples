package com.nestor.springactuatordemo;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义监控端点
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/4/25
 */
@Endpoint(id="myEndpoint")
@Component
public class MyEndpoint {
    private String STATUS = "up";
    private String DETAIL = "一切正常";

//    @ReadOperation
//    public String test1(){
//        return "wolf";
//    }

//    @ReadOperation
//    public Map<String,String> test2(){
//        Map<String,String> map = new HashMap();
//        map.put("status","up");
//        return map;
//    }

    @ReadOperation
    public Map<String, Object> test3(){
        Map<String, Object> jsonObject= new HashMap<>();
        jsonObject.put("status",STATUS);
        jsonObject.put("detail",DETAIL);
        return jsonObject;
    }

    @ReadOperation
    public Map<String, Object> test3_1(@Selector String name){
        Map<String, Object> jsonObject= new HashMap<>();
        if ("status".equals(name)){
            jsonObject.put("status",STATUS);
        }else if ("detail".equals(name)){
            jsonObject.put("detail",DETAIL);
        }
        return jsonObject;
    }

    @WriteOperation//动态修改指标
    public void test4(@Selector String name,@Nullable String value){
        System.err.println(value);
        if (!StringUtils.isEmpty(value)){
            if ("status".equals(name)){
                STATUS = value;
            }else if ("detail".equals(name)){
                DETAIL = value;
            }
        }
    }
}

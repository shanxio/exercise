package com.nf.spring.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

public class JsonUtils {
    public static void
    write(OutputStream out, Object value) {
        ObjectMapper mapper= new ObjectMapper();
        try {
            mapper.writeValue(out, value);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("json serialize failed",e);
        }
    }

    /**
     * 将Bean对象转换为json语句
     *
     * @param object 对象
     * @return String
     */
    public static String getJsonString(Object object) {
        // 转换工具
        ObjectMapper mapper = new ObjectMapper();
        try {
            // 将对象转换成json语句并输出
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 把json数组转为Bean对象
     *
     * @param json json语句
     * @return 返回一个泛型对象
     */
    public static <T> T readValue(String json, Class<? extends T> clazz) {
        // 转换器
        ObjectMapper mapper = new ObjectMapper();
        T bean = null;
        try {
            // 将json语句转化为Bean对象
            bean = (T) mapper.readValue(json, clazz);
        } catch (IOException e) {
            System.out.println("json转对象失败！");
        }
        return bean;
    }

}
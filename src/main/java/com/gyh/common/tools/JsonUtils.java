package com.gyh.common.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gyh
 * @Date 2020/6/14 21:01
 */
public class JsonUtils extends ObjectMapper {
    private final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static Object toJson(String str){
        return JSONObject.parse(str);
    }

    public static String toStrByJson(Object obj){
        return JSONObject.toJSON(obj).toString();
    }

    /**
     *
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String jsonStr,Class<T> clazz){
        JSONObject json = JSONObject.parseObject(jsonStr);
        return JSON.toJavaObject(json,clazz);
    }
}

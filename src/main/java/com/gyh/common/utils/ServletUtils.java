package com.gyh.common.utils;

import com.gyh.common.tools.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gyh
 * @Date 2021/3/20 0:30
 */
public class ServletUtils {

    private static final Logger log = LoggerFactory.getLogger(ServletUtils.class);

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        try
        {
            return getRequestAttributes().getRequest();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name)
    {
        return getRequest().getParameter(name);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name)
    {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }


    public static ServletRequestAttributes getRequestAttributes() {
        try
        {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            return (ServletRequestAttributes) attributes;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static String getParameter(){
        HttpServletRequest request = getRequest();
        Map<String, String> result = new HashMap<>();

        Map<String, String[]> map = request.getParameterMap();

        for (Map.Entry<String, String[]> entry: map.entrySet()) {
            result.put(entry.getKey(), Arrays.stream(entry.getValue()).collect(Collectors.joining(",")));
        }

        return JsonUtils.toStrByJson(result);
    }

    public static String getBody(){
        HttpServletRequest request = getRequest();
        try {
            BufferedReader reader = request.getReader();
            return reader.toString();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return "";
    }

}

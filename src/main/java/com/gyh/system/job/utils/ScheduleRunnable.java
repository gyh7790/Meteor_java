package com.gyh.system.job.utils;

import com.gyh.common.exception.RRException;
import com.gyh.common.tools.StringUtils;
import com.gyh.common.utils.PropertiesUtil;
import com.gyh.common.utils.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author guoyh
 * @Date 2019/5/28 20:15
 */
public class ScheduleRunnable implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleRunnable.class);

    private Object target;
    private Method method;
    private String params;

    public ScheduleRunnable(String beanName,String methodName,String params){
        this.target = SpringContext.getBean(beanName);
        this.params = params;
        try {
            if(StringUtils.isNotBlank(params)){
                this.method = target.getClass().getDeclaredMethod(methodName, String.class);
            }else{
                this.method = target.getClass().getDeclaredMethod(methodName);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotBlank(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(),e);
        }
    }

}

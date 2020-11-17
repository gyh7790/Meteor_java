package com.gyh.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PropertiesLoaderSupport;

/**
 * 系统中的配置文件
 * @author 作者 gyh
 * @version 创建时间：2018年7月7日 下午5:20:33
 */
public final class PropertiesUtil extends PropertiesLoaderSupport {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static final Environment ENV = SpringContext.getBean(Environment.class);

    public static String getString(String key){
        return ENV.getProperty(key);
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return Integer.parseInt(getString(key));
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String key, int defaultValue) {
        String value = getString(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

}

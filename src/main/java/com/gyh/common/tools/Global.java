package com.gyh.common.tools;

import com.google.common.collect.Maps;
import com.gyh.common.utils.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author 作者 gyh
 * @version 创建时间：2018年7月2日 下午3:33:42
 */
public class Global {

    /**
     * Token的过期时间(分钟)
     */
    public static final long TOKEN_EXPIRE = 12 * 3600;

    /**
     * 获取的项目工程路径
     */
    public static final String PATH_PROJECT = System.getProperty("user.dir");

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 代码 模板 路径
     */
    public static String GEN_TEMPLATE = "src/main/resources/templates/system/gen";

    /**
     * 获取配置文件中的数据
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (org.apache.commons.lang3.StringUtils.isEmpty(value)) {
            value = PropertiesUtil.getString(key);
            map.put(key, org.apache.commons.lang3.StringUtils.isEmpty(value) ? value : StringUtils.EMPTY);
        }
        return value;
    }

    /**
     * 判断是否是debug模式
     */
    public static Boolean isDebugMode() {
        String dm = getConfig("debug.mode");
        return "true".equals(dm) || "1".equals(dm);
    }


    /**
     * 生成UUID
     * @return
     */
    public static String genUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }

}

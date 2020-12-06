package com.gyh.system.sys.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author gyh
 * @Date 2020/12/6 19:11
 */
public class IpUtils {

    /**
     * 获取服务器IP
     * @return
     */
    public static String getHostIp()
    {
        try
        {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e)
        {
        }
        return "127.0.0.1";
    }

    /**
     * 获取 服务器名称
     * @return
     */
    public static String getHostName()
    {
        try
        {
            return InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException e)
        {
        }
        return "未知";
    }
}

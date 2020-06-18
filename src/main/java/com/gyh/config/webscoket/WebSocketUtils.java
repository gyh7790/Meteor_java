package com.gyh.config.webscoket;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;

/**
 * @author
 * @Date 2019/5/12 17:07
 */
@Component
public class WebSocketUtils {

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;



    /**
     * 使用 字符格式发送信息
     * @param message
     * @throws IOException
     */
    public void sendStringMessage(String message, String key)throws IOException {
        myWebSocketHandler.sendTextMessage(new TextMessage(message),key);
    }

    /**
     * 使用 Object格式发送信息
     * @param obj
     * @throws IOException
     */
    public void sendObjectMessage(Object obj, String key)throws IOException {
        myWebSocketHandler.sendTextMessage(new TextMessage(JSONObject.toJSON(obj).toString()),key);
    }

    /**
     * 使用 文本格式发送信息
     * @param message
     * @throws IOException
     */
    public void sendTextMessage(TextMessage message, String key)throws IOException {
        myWebSocketHandler.sendTextMessage(message,key);
    }

    /**
     * 使用 二进制格式发送信息
     * @param message
     * @throws IOException
     */
    public void sendBinaryMessage(TextMessage message,String key)throws IOException {
        myWebSocketHandler.sendBinaryMessage(message,key);
    }

}
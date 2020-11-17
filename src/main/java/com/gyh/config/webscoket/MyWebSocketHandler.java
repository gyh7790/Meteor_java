package com.gyh.config.webscoket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author
 * @Date 2019/5/11 17:51
 */
public class MyWebSocketHandler extends AbstractWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);

    public static Map<String, WebSocketSession> WEB_WEB_SOCKET_SESSION_MAP;

    static {
        WEB_WEB_SOCKET_SESSION_MAP = new ConcurrentHashMap<>();
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("MyWebSocketHandler =======> afterConnectionEstablished..................");
        String token = (String) session.getAttributes().get("token");
        if (WEB_WEB_SOCKET_SESSION_MAP.get(token) == null || WEB_WEB_SOCKET_SESSION_MAP.containsKey(token)) {
            //判断是否存在该token
            if (WEB_WEB_SOCKET_SESSION_MAP.containsKey(token)) {
                logger.info(token+"用户Socket会话已经替换:被替换sessionId=" + WEB_WEB_SOCKET_SESSION_MAP.get(token).getId()
                        +"\t"+"替换sessionId="+session.getId());
            }
            WEB_WEB_SOCKET_SESSION_MAP.put(token,session);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        logger.info("MyWebSocketHandler =======> handleTextMessage..................");
    }


    /**
     * 使用 文本格式发送信息
     * @param message
     * @throws IOException
     */
    public void sendTextMessage(TextMessage message,String token)throws IOException {
        WebSocketSession session = WEB_WEB_SOCKET_SESSION_MAP.get(token);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }

    /**
     * 使用 二进制格式发送信息
     * @param message
     * @throws IOException
     */
    public void sendBinaryMessage(TextMessage message,String token)throws IOException {
        WebSocketSession session = WEB_WEB_SOCKET_SESSION_MAP.get(token);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }

}

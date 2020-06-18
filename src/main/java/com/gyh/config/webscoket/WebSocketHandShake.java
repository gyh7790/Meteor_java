package com.gyh.config.webscoket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author
 * @Date 2019/5/12 13:44
 */
public class WebSocketHandShake  implements HandshakeInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandShake.class);


    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        logger.debug("WebSocketHandShake=========>beforeHandshake......................");
        String token = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest().getParameter("token");
        if(token!=null){
            map.put("token", token);
        }else{
            return false;
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        logger.debug("WebSocketHandShake=========>beforeHandshake......................");
    }
}

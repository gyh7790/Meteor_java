package com.gyh.config.webscoket;

/**
 * @author gyh
 * @Date 2020/6/14 0:23
 */

import com.gyh.common.tools.Global;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * websocket配置
 * @author guoyh
 * @Date 2019/5/11 17:45
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer(){
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        container.setMaxSessionIdleTimeout(Global.TOKEN_EXPIRE);
        return container;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler(),"/webSocket").setAllowedOrigins("*").addInterceptors(new WebSocketHandShake());
        registry.addHandler(myWebSocketHandler(),"/sockejs").setAllowedOrigins("*").addInterceptors(new WebSocketHandShake()).withSockJS();
    }

    @Bean
    public MyWebSocketHandler myWebSocketHandler(){
        return new MyWebSocketHandler();
    }

}

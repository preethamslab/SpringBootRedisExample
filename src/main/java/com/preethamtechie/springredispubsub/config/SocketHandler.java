package com.preethamtechie.springredispubsub.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class SocketHandler extends TextWebSocketHandler
{
    private static final Logger logger = LoggerFactory.getLogger(SocketHandler.class);

    public static Map<String, WebSocketSession> tokenSessionMap = new HashMap<String,WebSocketSession>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("after connection established, session id is "+ session.getId());
        session.sendMessage(new TextMessage("send_qrtoken"));
        super.afterConnectionEstablished(session);

    }
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage msg)
    {
        logger.info("session id in handle text msg "+ session.getId() + "  ==> msg ==> "+ msg.getPayload());
        tokenSessionMap.put(msg.getPayload().trim(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}

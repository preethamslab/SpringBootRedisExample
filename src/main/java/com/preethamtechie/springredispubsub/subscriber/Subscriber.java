package com.preethamtechie.springredispubsub.subscriber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.preethamtechie.springredispubsub.config.SocketHandler;
import com.preethamtechie.springredispubsub.dto.QrtokenSession;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


public class Subscriber implements MessageListener
{
    Logger logger = LoggerFactory.getLogger(Subscriber.class);

    @Autowired
    private SocketHandler socketHandler;

    @SneakyThrows
    @Override
    public void onMessage(Message message, byte[] bytes)
    {
        String[] arr = message.toString().split(",");
        logger.info("message received "+ arr[0] + " "+arr[1]+ " "+arr[2]);
        if(socketHandler.tokenSessionMap.containsKey(arr[1].trim())) {
            WebSocketSession session = socketHandler.tokenSessionMap.get(arr[1].trim());
            session.sendMessage(new TextMessage(arr[2].trim()));
        }else
        {
            logger.info("QR Token associated session doesn't belong to this instance "+ arr[1]);
        }

    }

}

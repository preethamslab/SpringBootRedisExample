package com.preethamtechie.springredispubsub.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.preethamtechie.springredispubsub.config.SocketHandler;
import com.preethamtechie.springredispubsub.dto.QrtokenSession;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;


@RestController
@Slf4j
public class Publisher
{
    Logger logger = LoggerFactory.getLogger(Publisher.class);
    @Autowired
    @Qualifier(value = "redisTemp")
    private RedisTemplate template;
    @Autowired
    private ChannelTopic topic;

    @Autowired
    private SocketHandler socketHandler;
    @PostMapping("/pub")
    public String publish(@RequestBody QrtokenSession tokenSession) throws JsonProcessingException {

        String[] arr = new String[5];
        arr[0] = ""+tokenSession.getId();
        arr[1] = tokenSession.getQrToken();
        arr[2] = tokenSession.getAuthToken();

        if(socketHandler.tokenSessionMap.containsKey(tokenSession.getQrToken()))
        {
            logger.info("published for "+ arr[0] + " qrtoken "+ arr[1]+ " auth token "+ arr[2]);
            template.convertAndSend(topic.getTopic(), arr);
            return "event published  "+ arr.toString();
        }
        else
        {
            return "No associated token/session found, please try again later";
        }
    }
}

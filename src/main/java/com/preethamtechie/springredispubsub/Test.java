package com.preethamtechie.springredispubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.preethamtechie.springredispubsub.dto.QrtokenSession;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Test
{
    public static void main(String[] args) throws JsonProcessingException {
/*        QrtokenSession qrtokenSession = new QrtokenSession(101,"121","1213");
        //JSONObject obj = new JSONObject(tokenSession);

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr =  mapper.writeValueAsString(qrtokenSession);
        System.out.println(jsonStr);
        *//*        JSONObject obj = new JSONObject(qrtokenSession);
        System.out.println(obj.toString());*//*

*//*        Gson gson = new Gson();
        QrtokenSession session = gson.fromJson(obj.toString(),QrtokenSession.class);
        System.out.println(session.getId());*/
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("12",44);
        System.out.println(map.get("12"));
        map.put("12",423);
        System.out.println(map.get("12"));
        map.remove("12");
        if(map.containsKey("12")) {
            System.out.println(map.get("12"));
        }

    }
}

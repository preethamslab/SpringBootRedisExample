package com.preethamtechie.springredispubsub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.json.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QrtokenSession
{
    private int id;
    private String qrToken;
    private String authToken;

    public String toJson()
    {
      return "";
    }


}

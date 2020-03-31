package com.example.StockPrediction.serviceImpl;

import com.example.StockPrediction.service.RecommendationService;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;


@Service
public class RecommendationServiceImpl implements RecommendationService {

    public String getRecommendation(long date) {
        JSONObject responseData = new JSONObject();
        responseData.put("message", "successfully get your request");
        return responseData.toJSONString();
    }
}

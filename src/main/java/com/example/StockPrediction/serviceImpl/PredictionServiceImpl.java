package com.example.StockPrediction.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.example.StockPrediction.service.PredictionService;
import com.example.StockPrediction.stockDaoImpl.PredictionDaoImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {

    public String predict(String stockName, long date) {
        JSONObject responseData = new JSONObject();
        responseData.put("message", "successfully get your request");
        return responseData.toJSONString();
    }
}

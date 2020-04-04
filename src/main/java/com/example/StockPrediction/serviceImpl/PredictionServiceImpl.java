package com.example.StockPrediction.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.example.StockPrediction.service.PredictionService;
import com.example.StockPrediction.Dao.PredictionDao;
import com.example.StockPrediction.DaoImpl.PredictionDaoImpl;
import org.springframework.stereotype.Service;

@Service
public class PredictionServiceImpl implements PredictionService {

    public String predict(String stockName, String date) throws Exception {

        PredictionDao pd = new PredictionDaoImpl();

        float price = pd.getPrice(stockName, date);

        JSONObject responseData = new JSONObject();
        responseData.put("price: ", String.valueOf(price));
        return responseData.toJSONString();
    }
}

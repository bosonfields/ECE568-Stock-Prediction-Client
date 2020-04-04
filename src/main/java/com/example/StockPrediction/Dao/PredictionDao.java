package com.example.StockPrediction.Dao;
import com.example.StockPrediction.model.PredictData;
import java.util.List;

public interface PredictionDao {
    List<PredictData> findAllPrices() throws Exception;
    float getPrice(String stockName, String date) throws Exception;
    String timeStamp2Date(String seconds);

}

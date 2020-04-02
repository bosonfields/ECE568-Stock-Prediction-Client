package com.example.StockPrediction.stockDao;
import com.example.StockPrediction.pkg.PredictData;
import java.util.List;

public interface PredictionDao {
    List<PredictData> findAllPrices() throws Exception;

}

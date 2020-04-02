package com.example.StockPrediction;

import com.example.StockPrediction.pkg.PredictData;
import com.example.StockPrediction.stockDao.PredictionDao;
import com.example.StockPrediction.stockDaoImpl.PredictionDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class StockPredictionApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockPredictionApplication.class, args);
//
//		PredictionDao pd = new PredictionDaoImpl();
//
//		List<PredictData> ls = pd.findAllPrices();
//
//		System.out.println(ls.get(0).getPrice());
	}

}

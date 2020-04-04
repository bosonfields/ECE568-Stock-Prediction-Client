package com.example.StockPrediction.service;

public interface PredictionService {
    String predict(String stockName, String date) throws Exception;
}

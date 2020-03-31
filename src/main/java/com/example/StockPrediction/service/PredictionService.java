package com.example.StockPrediction.service;

public interface PredictionService {
    String predict(String stockName, long date);
}

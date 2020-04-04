package com.example.StockPrediction.model;

public class PredictData {
    private String symbol;
    private String currTime;
    private float price;

    public PredictData(String symbol, String currTime, float price){
        this.symbol = symbol;
        this.currTime = currTime;
        this.price = price;
    }
    public String getSymbol(){
        return this.symbol;
    }
    public String getTime(){
        return this.currTime;
    }
    public float getPrice(){
        return this.price;
    }
}

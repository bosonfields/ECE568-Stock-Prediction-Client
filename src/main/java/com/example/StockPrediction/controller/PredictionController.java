package com.example.StockPrediction.controller;

import java.util.*;


import com.example.StockPrediction.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/prediction")
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @RequestMapping(value = "/getPrediction", method = RequestMethod.GET)
    public String predict(@RequestParam Map<String, Object> params) {
        long date = Long.parseLong(params.get("date").toString());
        return predictionService.predict(params.get("stockName").toString(), date);
    }
}

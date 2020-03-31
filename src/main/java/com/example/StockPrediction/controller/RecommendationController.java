package com.example.StockPrediction.controller;


import com.example.StockPrediction.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping(value = "/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @RequestMapping(value = "/getRecommendation", method = RequestMethod.GET)
    public String recommend(@RequestParam Map<String, Object> params) {
        long date = Long.parseLong(params.get("date").toString());
        return recommendationService.getRecommendation(date);
    }

}

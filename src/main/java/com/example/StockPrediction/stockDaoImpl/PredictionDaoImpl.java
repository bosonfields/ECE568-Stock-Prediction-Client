package com.example.StockPrediction.stockDaoImpl;


import com.example.StockPrediction.pkg.PredictData;
import com.example.StockPrediction.stockDao.BaseDao;
import com.example.StockPrediction.stockDao.PredictionDao;

import java.sql.*;
import java.util.*;

public class PredictionDaoImpl implements PredictionDao {

    public List<PredictData> findAllPrices() throws Exception{

        List<PredictData> ls = new ArrayList<>();

        BaseDao bd = new BaseDaoImpl();
        Connection conn = bd.getConnection();
        String sql = "select * from Prediction";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            PredictData curr = new PredictData(
                    rs.getString("symbol"),
                    rs.getString("currTime"),
                    rs.getFloat("price")
            );
            ls.add(curr);
        }
        bd.closeAll(conn);
        return ls;
    }
}

/*
insert into Prediction
(symbol, currTime, price)
values('BABA', '2020-02-27 16:00:00', '205.0900');



insert into Stock
(symbol, startTime)
values('BABA', '1999-04-04');
 */
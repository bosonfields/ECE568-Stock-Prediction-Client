package com.example.StockPrediction.DaoImpl;


import com.example.StockPrediction.model.PredictData;
import com.example.StockPrediction.Dao.BaseDao;
import com.example.StockPrediction.Dao.PredictionDao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

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
    public float getPrice(String stockName, String date) throws Exception{

        List<PredictData> ls = new ArrayList<>();

        BaseDao bd = new BaseDaoImpl();
        Connection conn = bd.getConnection();

        String strDate = "'" + timeStamp2Date(date) + "'";

        String sql = "select * from Prediction where symbol = '" + stockName + "' and currTime = " + strDate;

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);


        float price = 0;
        if(rs.next()){
            price = rs.getFloat("price");
        }

        bd.closeAll(conn);
        return price;
    }

    public String timeStamp2Date(String seconds) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }

        String format = "yyyy-MM-dd";

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
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
package com.example.StockPrediction.DaoImpl;

import com.example.StockPrediction.Dao.BaseDao;
import java.sql.*;

public class BaseDaoImpl implements BaseDao {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://35.243.147.176:3306/stockPrice";
    private static String user = "root";
    private static String password = "EE568fluent";

    public Connection getConnection(){
        try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;

        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void closeAll(Connection conn) throws SQLException {
        conn.close();
    }
}

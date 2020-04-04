package com.example.StockPrediction.Dao;
import java.sql.Connection;
import java.sql.SQLException;

public interface BaseDao {
    Connection getConnection() throws SQLException;

    void closeAll(Connection conn) throws SQLException;
}

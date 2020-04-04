import time, logging
import pymysql
from alpha_vantage.timeseries import TimeSeries

class DataHunter:
   stock_list = ("GOOGL", "TSLA", "INSM", "LAKE", "AMZN", "FB", "BABA", "AAPL", "GM", "LLY")
   key = 'FPJESXUBYN5ZQIDX'
   ts = TimeSeries(key)

   def __init__(self):
       pass

   def get_historical_data(self):
       for stock in self.stock_list:
           raw_data, meta = self.ts.get_daily(symbol=stock, outputsize='full')
           print("successfully get %s" % stock)
           logging.info("successfully get %s" % stock)
           # write to disk
           f = open('%s.txt' % stock, 'w')
           f.write(str(raw_data))
           f.close()
           time.sleep(12)

   def get_real_time_data(self):
       for stock in self.stock_list:
           raw_data, meta = self.ts.get_intraday(symbol=stock, interval='1min', outputsize='full')
           print("successfully get real time %s" % stock)
           logging.info("successfully get real time %s" % stock)
           # write to disk
           f = open('%s_real_time.txt' % stock, 'w')
           f.write(str(raw_data))
           f.close()
           time.sleep(12)

   def parse_and_insert_into_DB(self):
        pass

   def insertTable(self, tableName, symbol, symbolLogs, mycursor, mydb):
       for currTime in symbolLogs:

           priceData = symbolLogs[currTime]
           meta = [symbol, currTime, priceData['1. open'], priceData['2. high'], priceData['3. low'],
                   priceData['4. close'], priceData['5. volume']]

           vals = ''
           for str in meta:
               vals += '\'' + str + '\'' + ', '

           vals = '( ' + vals[:-2] + ' )'

           if not self.lineExist(symbol, currTime, tableName, mycursor):
               self.insertLine(tableName, vals, mycursor, mydb)
           else:
               break

   def insertLine(self, tableName, vals, mycursor, mydb):
       insertQuery = "INSERT INTO " + tableName + "(symbol, currTime, openPrice, highPrice, lowPrice, closePrice, volume) VALUES "
       insertQuery += vals

       print(insertQuery)
       try:
           mycursor.execute(insertQuery)
           mydb.commit()
       except:
           mydb.rollback()

   def insertStock(self, symbol, mycursor, mydb):
       query = "insert into Stock(symbol, startTime) VALUES"
       query += '( \'' + symbol + '\', ' + '\'1999-01-01\' )'
       print(query)
       try:
           mycursor.execute(query)
           mydb.commit()
       except:
           mydb.rollback()

   def buildConnect(self):
       mydb = pymysql.connect(
           host="0.0.0.0",
           user="root",
           passwd="root",
           database="stockPrice",
       )
       mycursor = mydb.cursor()

       return mycursor, mydb

   def lineExist(self, symbol, currTime, tableName, mycursor):
       searchQuery = "select Q.symbol from " + tableName + " Q WHERE Q.symbol = '%s' AND Q.currTime = '%s' " % (
       symbol, currTime)
       print(searchQuery)

       mycursor.execute(searchQuery)
       data = mycursor.fetchall()
       if len(data) >= 1:
           return True
       return False

   def stockExist(self, symbol, tableName, mycursor):
       searchQuery = "SELECT Q.symbol from " + tableName + " Q WHERE Q.symbol = '%s' " % (symbol)
       print(searchQuery)

       mycursor.execute(searchQuery)

       data = mycursor.fetchall()
       if len(data) >= 1:
           return True
       return False

   def endDB(self, mydb):
       mydb.close()

   def insertData(self, tableName, mycursor, mydb):
       symbols = self.stock_list
       for symbol in symbols:
           suffix = ''
           if tableName is 'Real_time_data':
               suffix += '_real_time'
           suffix += '.txt'
           f = open(symbol + suffix, 'r')
           a = f.read()
           symbolLogs = eval(a)
           if not self.stockExist(symbol, 'Stock', mycursor):
               self.insertStock(symbol, mycursor, mydb)

           self.insertTable(tableName, symbol, symbolLogs, mycursor, mydb)
           f.close()


if __name__=="__main__":
    while(True):
        logging.basicConfig(filename='log_file.log', level=logging.DEBUG)
        print("start to retrieve data at ", time.localtime(time.time()))
        data_hunter = DataHunter()
        data_hunter.get_historical_data()
        data_hunter.get_real_time_data()
        cursor, db = data_hunter.buildConnect()
        tableName = "Historical"
        data_hunter.insertData(tableName, cursor, db)
        tableName = "Real_time_data"
        data_hunter.insertData(tableName, cursor, db)
        data_hunter.endDB(db)
        time.sleep(300)

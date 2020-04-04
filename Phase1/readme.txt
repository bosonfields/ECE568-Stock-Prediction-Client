package requirements:
pip install time
pip install logging
pip install pymysql
pip install alpha_vantage

to run this application:
1. Open command line, cd to the directory where source codes is
2. Run "nohup python -u DataHunter.py > out.log 2>&1 &". 

to install mysql:
1. Open command line
2. For the Ubuntu, run "sudo apt-get install mysql-server". Otherwise, google "how to install mysql on YOUR OPERATING SYSTEM". 

This Application will run continously, unless someone kill it with command. It will run continuously as a background process and periodically retrieve stock information, parse received raw data, store the extracted data into a relational database. 

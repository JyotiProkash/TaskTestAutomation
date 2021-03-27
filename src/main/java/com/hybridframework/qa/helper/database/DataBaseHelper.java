package com.hybridframework.qa.helper.database;

import com.hybridframework.qa.helper.frame.FrameHelper;
import com.hybridframework.qa.helper.logger.LoggerHelper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Jyoti on 04/05/2020.
 */
public class DataBaseHelper
  {
      private Logger log= LoggerHelper.getLogger(DataBaseHelper.class);
      private static String url="jdbc:mysql://localhost/person";
      private static String userName="root";
      private static String password="password";
      private static String driverName="com.mysql.jdbc.Driver";
      private static Connection connection;
      private static DataBaseHelper instance=null;

      public DataBaseHelper() throws IOException
        {
            connection=getSingleInstanceConnection();
        }
      public static DataBaseHelper getInstance() throws IOException
        {
            if(instance==null)
              {
                  instance=new DataBaseHelper();
              }
            return instance;
        }

      private Connection getSingleInstanceConnection()
        {
            try
              {
                  Class.forName(driverName);
                  try
                    {
                        connection= DriverManager.getConnection(url,userName,password);
                        if(connection!=null)
                            log.info("Connected to database.");
                    }
                  catch (SQLException e)
                    {
                        log.error("Failed to create database connection"+e);
                    }
              }
            catch (ClassNotFoundException e)
              {
                  log.info("Driver not found"+e);
              }
            return connection;
        }

      public Connection getConnection()
        {
            return connection;
        }

      public ResultSet getResultSet(String dbQuery) throws IOException
        {
            instance=DataBaseHelper.getInstance();
            connection=instance.getConnection();
            log.info("Executing query: "+dbQuery);
            try
              {
                  Statement stmt=connection.createStatement();
                  ResultSet result=stmt.executeQuery(dbQuery);
                  return result;
              }
            catch (SQLException e)
              {
                  e.printStackTrace();
              }
            return null;
        }
  }

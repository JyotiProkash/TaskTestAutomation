package com.hybridframework.qa.utilities;

import com.hybridframework.qa.helper.resource.ResourceHelper;

import java.io.*;
import java.util.Properties;

/**
 * Created by Jyoti on 12/04/2020
 */

public class PropertyManager
  {
      public static Properties prop;

      /**
       * This method will help to find any of property file.
       * @param nameOfPropertyFile
       * @param nameOfProperty
       * @return
       * @throws IOException
       */
      public static String getProperty(String nameOfPropertyFile, String nameOfProperty) throws IOException
        {
            try
              {
                  prop=new Properties();
                  FileInputStream fs=new FileInputStream(ResourceHelper.getResourcePath("src/main/resources/ConfigFiles/")+nameOfPropertyFile);
                  prop.load(fs);
                  fs.close();
              }
            catch (FileNotFoundException e)
              {
                e.printStackTrace();
              }
            catch (IOException e)
              {
                e.printStackTrace();
              }
            return  prop.getProperty(nameOfProperty);
        }

      /**
       * This method will help to to find all properties of property file.
       * @param nameOfPropertyFile
       * @return
       * @throws IOException
       */
      public static Properties getProperty(String nameOfPropertyFile) throws IOException
        {
            try
             {
                prop=new Properties();
                FileInputStream fs=new FileInputStream(ResourceHelper.getResourcePath("src/main/resources/ConfigFiles/")+nameOfPropertyFile);
                prop.load(fs);
                fs.close();
             }
            catch (FileNotFoundException e)
             {
                e.printStackTrace();
             }
            catch (IOException e)
             {
                e.printStackTrace();
             }
            return  prop;
      }
  }

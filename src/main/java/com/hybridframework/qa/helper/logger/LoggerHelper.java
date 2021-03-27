package com.hybridframework.qa.helper.logger;

import com.hybridframework.qa.utilities.PropertyManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;

public class LoggerHelper
  {
      /**
       * Created by Jyoti 14/04/2020
       * @Method getLogger
       */

      private static boolean root=false;

      /**
       * This method will help to generate log.
       * @param classname
       * @return
       * @throws IOException
       */
      public static Logger getLogger(Class classname) throws IOException
         {
             if(root)
               {
                   return Logger.getLogger(classname);
               }
             PropertyConfigurator.configure(PropertyManager.getProperty("log4j.properties"));
             root=true;
             return Logger.getLogger(classname);
         }

      /*public static void main(String[] args) throws IOException {
          Logger log=LoggerHelper.getLogger(LoggerHelper.class);
          log.info("I am an Engineer");
      }*/
  }

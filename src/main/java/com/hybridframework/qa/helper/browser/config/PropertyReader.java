package com.hybridframework.qa.helper.browser.config;

import com.hybridframework.qa.helper.browser.BrowserType;
import com.hybridframework.qa.utilities.PropertyManager;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader implements ConfigReader
  {
      private Properties pro;
      public PropertyReader()
        {
            try
              {
                pro=PropertyManager.getProperty("browserconfig.properties");
              }
            catch (Exception e)
              {
                e.printStackTrace();
              }
        }

      @Override
      public int getImplicitWait() {
          return Integer.parseInt(pro.getProperty("implicitwait"));
      }

      @Override
      public int getExplicitWait() {
          return Integer.parseInt(pro.getProperty("explicitwait"));
      }

      @Override
      public int getPageLoadTime() {
          return Integer.parseInt(pro.getProperty("pageloadtime"));
      }

      @Override
      public BrowserType getBrowserType() {
          return BrowserType.valueOf(pro.getProperty("browserType"));
      }
  }

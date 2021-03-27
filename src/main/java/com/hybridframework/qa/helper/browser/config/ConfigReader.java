package com.hybridframework.qa.helper.browser.config;

import com.hybridframework.qa.helper.browser.BrowserType;

public interface ConfigReader
  {
      public int getImplicitWait();
      public int getExplicitWait();
      public int getPageLoadTime();
      public BrowserType getBrowserType();
  }

package com.hybridframework.qa.helper.browser;

import com.hybridframework.qa.utilities.PropertyManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.ElementScrollBehavior;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

/**
 * Created by Jyoti on 20/04/2020
 */
public class IEBrowser
  {
      public InternetExplorerOptions getIExplorerCapabilities()
         {
             DesiredCapabilities cap=new DesiredCapabilities();
             cap.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
             cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);
             cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
             cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
             cap.setJavascriptEnabled(true);

             InternetExplorerOptions internetExplorerOptions=new InternetExplorerOptions(cap);
             return internetExplorerOptions;
         }

      public WebDriver getIExplorerDriver(InternetExplorerOptions cap) throws IOException {
             //System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\IEDriverServer.exe");
             WebDriverManager.iedriver().arch32().version(PropertyManager.getProperty("basicinfo.properties", "browser.ie.version")).setup();
             return new InternetExplorerDriver(cap);

         }
  }

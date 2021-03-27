package com.hybridframework.qa.helper.browser;

import com.hybridframework.qa.utilities.PropertyManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

/**
 * Created by Jyoti on 20/04/2020
 */
public class ChromeBrowser
  {
      public ChromeOptions getChromeOptions()
        {
            ChromeOptions option=new ChromeOptions();
            option.addArguments("--test-type");
            option.addArguments("--disable-popup-blocking");
            option.addArguments("--start-maximized");
            option.addArguments("--auto-open-devtools-for-tabs");
            DesiredCapabilities chrome=new DesiredCapabilities();
            chrome.setJavascriptEnabled(true);
            option.setCapability(ChromeOptions.CAPABILITY,option);
            if(System.getProperty("os.name").contains("Linux"))
              {
                  option.addArguments("--headless","window-size=1024,768","--no-sandbox");
              }
            return option;
        }
      public WebDriver getChromeDriver(ChromeOptions cap) throws IOException {
            if(PropertyManager.getProperty("basicinfo.properties", "os.name").equalsIgnoreCase("Windows"))
             {
                 //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"src\\main\\resources\\drivers\\chromedriver.exe");
                 WebDriverManager.chromedriver().version(PropertyManager.getProperty("basicinfo.properties", "browser.chrome.version"));
                 return new ChromeDriver(cap);
             }
            else if(PropertyManager.getProperty("basicinfo.properties", "os.name").equalsIgnoreCase("Linux"))
             {
                 //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"src\\main\\resources\\drivers\\chromedriver.exe");
                 WebDriverManager.chromedriver().version(PropertyManager.getProperty("basicinfo.properties", "browser.chrome.version"));
                 return new ChromeDriver(cap);
             }
            else if(PropertyManager.getProperty("basicinfo.properties", "os.name").equalsIgnoreCase("Mac"))
             {
                 //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"src\\main\\resources\\drivers\\chromedriver.exe");
                 WebDriverManager.chromedriver().version(PropertyManager.getProperty("basicinfo.properties", "browser.chrome.version"));
                 return new ChromeDriver(cap);
             }
            return null;
        }
  }

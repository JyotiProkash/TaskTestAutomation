package com.hybridframework.qa.helper.browser;

import com.hybridframework.qa.utilities.PropertyManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

/**
 * Created by Jyoti on 20/04/2020
 */
public class FireFoxBrowser
  {
      public FirefoxOptions getFireFoxOptions()
         {
             DesiredCapabilities firefox=new DesiredCapabilities();
             FirefoxProfile profile=new FirefoxProfile();
             profile.setAcceptUntrustedCertificates(true);
             profile.setAssumeUntrustedCertificateIssuer(true);
             firefox.setCapability(FirefoxDriver.PROFILE,profile);
             firefox.setCapability("marionette",true);

             FirefoxOptions firefoxOptions=new FirefoxOptions(firefox);
             if(System.getProperty("os.name").contains("Linux"))
             {
                 firefoxOptions.addArguments("--headless","window-size=1024,768","--no-sandbox");
             }
             return firefoxOptions;
         }
      public WebDriver getFireFoxDriver(FirefoxOptions cap) throws IOException
         {
             if(PropertyManager.getProperty("basicinfo.properties", "os.name").equalsIgnoreCase("Windows"))
             {
                 //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"src\\main\\resources\\drivers\\chromedriver.exe");
                 WebDriverManager.firefoxdriver().setup();
                 return new FirefoxDriver(cap);
             }
             else if(PropertyManager.getProperty("basicinfo.properties", "os.name").equalsIgnoreCase("Linux"))
             {
                 //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"src\\main\\resources\\drivers\\chromedriver.exe");
                 WebDriverManager.firefoxdriver().setup();
                 return new FirefoxDriver(cap);
             }
             else if(PropertyManager.getProperty("basicinfo.properties", "os.name").equalsIgnoreCase("Mac"))
             {
                 //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"src\\main\\resources\\drivers\\chromedriver.exe");
                 WebDriverManager.firefoxdriver().setup();
                 return new FirefoxDriver(cap);
             }
             return null;
         }
  }

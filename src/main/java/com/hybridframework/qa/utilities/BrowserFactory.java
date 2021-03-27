package com.hybridframework.qa.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;

public class BrowserFactory
  {
      /**
       * Created by Jyoti on 12/04/2020
       * Either set System Property or WebDriver Manager
       */
      private static WebDriver aBrowser;

      //Selecting a browser
      public static WebDriver getABrowser() throws IOException
      {
         if(PropertyManager.getProperty("basicinfo.properties", "os.name").equalsIgnoreCase("Windows") || PropertyManager.getProperty("basicinfo.properties", "os.name").equalsIgnoreCase("Linux") || PropertyManager.getProperty("basicinfo.properties", "os.name").equalsIgnoreCase("Mac"))
          if (PropertyManager.getProperty("basicinfo.properties", "browser").equalsIgnoreCase("firefox"))
          {
              //System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"src\\main\\resources\\drivers\\geckodriver.exe");
              WebDriverManager.firefoxdriver().setup();
              aBrowser=new FirefoxDriver();
          }
          else if (PropertyManager.getProperty("basicinfo.properties", "browser").equalsIgnoreCase("chrome"))
          {
              //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"src\\main\\resources\\drivers\\chromedriver.exe");
              //WebDriverManager.chromedriver().version(PropertyManager.getProperty("basicinfo.properties", "browser.chrome.version"));
              WebDriverManager.chromedriver().arch64().setup();
              aBrowser=new ChromeDriver();
          }
          else if (PropertyManager.getProperty("basicinfo.properties", "browser").equalsIgnoreCase("ie"))
          {
              //System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\IEDriverServer.exe");
              WebDriverManager.iedriver().arch32().version(PropertyManager.getProperty("basicinfo.properties", "browser.ie.version")).setup();
              aBrowser=new InternetExplorerDriver();
          }
          else if (PropertyManager.getProperty("basicinfo.properties", "browser").equalsIgnoreCase("edge"))
          {
              //System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\msedgedriver.exe");
              WebDriverManager.edgedriver().version(PropertyManager.getProperty("basicinfo.properties", "browser.edge.version")).setup();
              aBrowser=new EdgeDriver();
          }
          else if (PropertyManager.getProperty("basicinfo.properties", "browser").equalsIgnoreCase("safari"))
          {
              //System.setProperty("webdriver.safari.driver", System.getProperty("user.dir")+"src\\main\\resources\\drivers\\SafariDriver.exe");
              aBrowser=new SafariDriver();
          }
          else if (PropertyManager.getProperty("basicinfo.properties", "browser").equalsIgnoreCase("opera"))
          {
              //System.setProperty("webdriver.opera.driver", System.getProperty("user.dir")+"src\\main\\resources\\drivers\\operadriver.exe");
              WebDriverManager.operadriver().setup();
              aBrowser=new OperaDriver();

          }
          else if (PropertyManager.getProperty("basicinfo.properties", "browser").equalsIgnoreCase("browserstack"))
          {
                   /*try {
                       // todo , get this capability config from property
                       return new RemoteWebDriver(new URL(BrowserStackCapabilities.browserstackURL),DesiredcapabilityFactory.getCapability("browserstack") );
                   } catch (MalformedURLException e) {
                       e.printStackTrace();
                   }*/

          }
          else if (PropertyManager.getProperty("basicinfo.properties", "browser").equalsIgnoreCase("saucelabs"))
          {
                   /*try {
                       return new RemoteWebDriver(new URL(SauceLabCapabilities.sauceLabURL),DesiredcapabilityFactory.getCapability("saucelabs") );
                   } catch (MalformedURLException e) {
                       e.printStackTrace();
                   }*/
          }
          else if (PropertyManager.getProperty("basicinfo.properties", "browser").equalsIgnoreCase("appium-ios"))
          {
                   /*try {
                       aBrowser = new IOSDriver(new URL(AppiumCapabilities.appiumURL),DesiredcapabilityFactory.getCapability("appium-ios") );
                   } catch (MalformedURLException e) {
                       e.printStackTrace();
                   }*/
          }
          else if (PropertyManager.getProperty("basicinfo.properties", "browser").equalsIgnoreCase("appium-android"))
          {
                   /*try {
                       aBrowser = new AndroidDriver(new URL(AppiumCapabilities.appiumURL),DesiredcapabilityFactory.getCapability("appium-android") );
                   } catch (MalformedURLException e) {
                       e.printStackTrace();
                   }*/
          }
          else
          {
              //return new RemoteWebDriver(DesiredcapabilityFactory.getCapability(internalRemoteDriverConfig));
          }
          return aBrowser;
      }

      //Maximize a browser
      public static WebDriver maximizeABrowser(WebDriver rBrowser)
      {
          try
          {
              aBrowser=rBrowser;
              aBrowser.manage().window().maximize();
          }
          catch (Exception e)
          {
              e.printStackTrace();
          }
          return aBrowser;
      }

      //Open url with a browser
      public static WebDriver openURL(WebDriver rBrowser, String url)
      {
          try
          {
              aBrowser=rBrowser;
              aBrowser.get(url);
          }
          catch (Exception e)
          {
              e.printStackTrace();
          }
          return aBrowser;
      }

      //Closing a browser
      public static void closeABrowser(WebDriver rBrowser)
       {
          aBrowser=rBrowser;
          aBrowser.quit();
          aBrowser=null;
       }

      private BrowserFactory(){}
  }

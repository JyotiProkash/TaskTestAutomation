package com.hybridframework.qa.testbase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.hybridframework.qa.helper.browser.BrowserType;
import com.hybridframework.qa.helper.browser.ChromeBrowser;
import com.hybridframework.qa.helper.browser.FireFoxBrowser;
import com.hybridframework.qa.helper.browser.IEBrowser;
import com.hybridframework.qa.helper.browser.config.ObjectReader;
import com.hybridframework.qa.helper.browser.config.PropertyReader;
import com.hybridframework.qa.helper.logger.LoggerHelper;
import com.hybridframework.qa.helper.wait.WaitHelper;
import com.hybridframework.qa.utilities.BrowserFactory;
import com.hybridframework.qa.utilities.PropertyManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jyoti 23/03/2021
 */
public class TestBase
  {
      public WebDriver driver;
      public static ExtentReports extent;
      public static ExtentTest test;
      private Logger log= LoggerHelper.getLogger(TestBase.class);

      public TestBase() throws IOException {
      }


      @BeforeSuite
      public void beforeSuite() throws IOException
        {
            //extent=ExtentManager.getInstance();
        }

      @BeforeTest
      public void beforeTest() throws Exception
        {
//           driver= BrowserFactory.getABrowser();
//           BrowserFactory.maximizeABrowser(driver);
//           BrowserFactory.openURL(driver, PropertyManager.getProperty("basicinfo.properties", "url"));

//            ObjectReader.reader=new PropertyReader();
//            setUpDriver(ObjectReader.reader.getBrowserType());
        }

      //Launching a browser with url
      @BeforeClass
      public void initiation() throws InterruptedException, IOException
        {
            //test=extent.createTest(getClass().getName());
            driver= BrowserFactory.getABrowser();
            BrowserFactory.maximizeABrowser(driver);
            BrowserFactory.openURL(driver, PropertyManager.getProperty("basicinfo.properties", "url"));
        }

       @BeforeMethod
       public void beforeMethod(Method method)
        {
            //test.log(Status.INFO,method.getName()+" test started.");
        }

      @AfterMethod
      public void afterMethod(ITestResult result) throws IOException
        {
            //BrowserFactory.closeABrowser(driver);
        }

      @AfterClass
      public void tearDown() throws InterruptedException
        {
            BrowserFactory.closeABrowser(driver);
        }

      /**
       * @method for getBrowserObject
       * @param browserType
       * @return
       */
      public WebDriver getBrowserObject(BrowserType browserType) throws Exception {
             try
               {
                   switch (browserType)
                     {
                         case Chrome:
                             //get object of chrome browser class
                             ChromeBrowser chrome=ChromeBrowser.class.newInstance();
                             ChromeOptions option=chrome.getChromeOptions();
                             return chrome.getChromeDriver(option);
                         case FireFox:
                             FireFoxBrowser firefox=FireFoxBrowser.class.newInstance();
                             FirefoxOptions options=firefox.getFireFoxOptions();
                             return firefox.getFireFoxDriver(options);
                         case IE:
                             IEBrowser ie=IEBrowser.class.newInstance();
                             InternetExplorerOptions cap=ie.getIExplorerCapabilities();
                             return ie.getIExplorerDriver(cap);
                         default:
                             throw new Exception("Driver not found:"+browserType.name());
                     }

               }
             catch (Exception e)
               {
                   log.info(e.getMessage());
                   throw e;
               }
         }

      public void setUpDriver(BrowserType browserType) throws Exception
         {
            driver=getBrowserObject(browserType);
            log.info("Initialize Webdriver:"+driver.hashCode());
            WaitHelper wait=new WaitHelper(driver);
            wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
            wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(),TimeUnit.SECONDS);
            driver.manage().window().maximize();
         }
  }

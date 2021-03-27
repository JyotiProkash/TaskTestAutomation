package com.hybridframework.qa.helper.screenshot;

import com.hybridframework.qa.helper.alert.AlertHelper;
import com.hybridframework.qa.helper.logger.LoggerHelper;
import com.hybridframework.qa.helper.resource.ResourceHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenShotHelper
  {
      private static WebDriver driver;
      private static Logger log;

      static {
          try {
              log = LoggerHelper.getLogger(ScreenShotHelper.class);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      public ScreenShotHelper(WebDriver driver)
      {
          this.driver=driver;
      }
      public static String captureScreen(WebDriver driver, String screenShotName)
         {
             if(driver==null)
               {
                   log.info("Driver is null");
                   return null;
               }
             if(screenShotName.equals(""))
               {
                   screenShotName="blank";
               }

             Calendar calendar=Calendar.getInstance();
             SimpleDateFormat dFormat =new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
             String fileName="AutomationReport_"+dFormat.format(calendar.getTimeInMillis())+".html";
             String directory=ResourceHelper.getResourcePath("src/test/output/FailedCaseScreenshot/");
             //new File(directory).mkdirs();
             String path=directory+fileName;
             File destFile=null;

             try
              {
                  File srcFile = (File) ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                  destFile =new File(path);
                  Files.copy(srcFile.toPath(),destFile.toPath());
                  log.info("Screenshot stored at: "+path);
                  //Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"' height='100' width='100'/> </a>");
              }
             catch (Exception e)
              {
                  e.printStackTrace();
              }
             return destFile.getAbsolutePath().toString();

         }

       public String captureScreenAsBase64(WebDriver driver, String screenShotName)
         {
           return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
         }

  }

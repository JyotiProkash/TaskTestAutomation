package com.hybridframework.qa.helper.alert;

import com.hybridframework.qa.helper.frame.FrameHelper;
import com.hybridframework.qa.helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by Jyoti 19/04/2020
 */
public class AlertHelper
  {
      private WebDriver driver;
      private Logger log= LoggerHelper.getLogger(AlertHelper.class);
      public AlertHelper(WebDriver driver) throws IOException
         {
             this.driver=driver;
             log.info("AlertHelper object is created...");
         }
      public Alert getAlert()
         {
             getAlert().accept();
             log.info("Alert is:"+driver.switchTo().alert().getText());
             return driver.switchTo().alert();
         }
      public void acceptAlert()
         {
             getAlert().accept();
             log.info("Accept alert is done...");
         }
      public void dismissAlert()
         {
            getAlert().dismiss();
            log.info("Dismiss alert is done...");
         }
      public String getAlertText()
         {
             String text=getAlert().getText();
             log.info("Alert text is:"+text);
             return text;
         }
      public boolean isAlertPresent()
         {
             try
               {
                   driver.switchTo().alert();
                   log.info("Alert is present...");
                   return true;
               }
             catch(NoAlertPresentException e)
               {
                   log.info(e.getCause());
                   return false;
               }
         }
      public void acceptAlertIfPresent()
         {
             if(isAlertPresent())
               {
                   acceptAlert();
               }
             else
               {
                   log.info("Alert is not present...");
               }
         }
      public void dismissAlertIfPresent()
         {
             if(isAlertPresent())
               {
                   dismissAlert();
               }
             else
               {
                   log.info("Alert is not present...");
               }
         }
      public void acceptPrompt(String text)
         {
             if(isAlertPresent())
               {
                   Alert alert=getAlert();
                   alert.sendKeys(text);
                   alert.accept();
                   log.info("Alert text is:"+text);
               }
         }
  }

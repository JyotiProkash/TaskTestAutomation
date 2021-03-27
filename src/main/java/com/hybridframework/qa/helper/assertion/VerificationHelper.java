package com.hybridframework.qa.helper.assertion;

import com.hybridframework.qa.helper.frame.FrameHelper;
import com.hybridframework.qa.helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

/**
 * Created by Jyoti 18/04/2020
 */
public class VerificationHelper
  {
      private WebDriver driver;
      private Logger log= LoggerHelper.getLogger(VerificationHelper.class);

      public VerificationHelper(WebDriver driver) throws IOException
         {
             this.driver=driver;
         }

      /**
       * This method checks whether element is display.
       * @param element
       * @return
       */
      public boolean isDisplayed(WebElement element)
         {
             try
               {
                   element.isDisplayed();
                   log.info("Element is present..."+element.getText());
                   return true;
               }
             catch (Exception e)
               {
                   log.error("Element is not present...",e.getCause());
                   return false;
               }
         }

      /**
       * This method checks whether element is not displayed.
       * @param element
       * @return
       */
      public boolean isNotDisplayed(WebElement element)
         {
              try
              {
                 element.isDisplayed();
                 log.info("Element is present..."+element.getText());
                 return false;
              }
             catch (Exception e)
               {
                   log.error("Element is not present...",e.getCause());
                   return true;
               }
         }

      /**
       * This method finds out value from an element.
       * @param element
       * @return
       */
      public String getValueFromElement(WebElement element)
         {
             if(element==null)
               {
                   log.info("WebElement is null...");
                   return null;
               }
             boolean status=isDisplayed(element);
             if (status)
               {
                   log.info("Element text is.."+element.getText());
                   return element.getText();
               }
             else
               {
                   return null;
               }
         }

      /**
       * This method finds out title of a page.
       * @return
       */
      public String getTitle()
         {
             log.info("Page title is: "+driver.getTitle());
             return driver.getTitle();
         }

  }

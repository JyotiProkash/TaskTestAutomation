package com.hybridframework.qa.helper.frame;

import com.hybridframework.qa.helper.logger.LoggerHelper;
import com.hybridframework.qa.helper.wait.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

/**
 * Created by Jyoti 15/04/2020
 */

public class FrameHelper
   {
       private WebDriver driver;
       private Logger log= LoggerHelper.getLogger(FrameHelper.class);

       public FrameHelper(WebDriver driver) throws IOException
          {
              this.driver=driver;
          }

       /**
        * This method will help switchToFrame based on index
        * @param index
        */
       public void switchToFrame(int index)
          {
              driver.switchTo().frame(index);
              log.info("Switched to:"+index+" frame.");
          }

       /**
        * This method will help switchToFrame based on frameName
        * @param frameName
        */
       public void switchToFrame(String frameName)
          {
              driver.switchTo().frame(frameName);
              log.info("Switched to:"+frameName+" frame.");
          }

       /**
        * This method will help switchToFrame based on frame WebElement
        * @param element
        */
       public void switchToFrame(WebElement element)
          {
              driver.switchTo().frame(element);
              log.info("Switched to frame:"+element.toString());
          }
   }

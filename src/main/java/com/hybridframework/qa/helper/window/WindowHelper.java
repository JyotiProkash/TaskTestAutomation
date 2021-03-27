package com.hybridframework.qa.helper.window;

import com.hybridframework.qa.helper.frame.FrameHelper;
import com.hybridframework.qa.helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Jyoti 16/04/2020
 */
public class WindowHelper
   {
      private WebDriver driver;
      private Logger log= LoggerHelper.getLogger(WindowHelper.class);

      public WindowHelper(WebDriver driver) throws IOException
        {
           this.driver=driver;
        }

       /**
        * This method will switchToParentWindow
        */
      public void switchToParentWindow()
        {
           log.info("Switching to parent window...");
           //String parentWindowHandler=driver.getWindowHandle();
           //driver.switchTo().window(parentWindowHandler);
           driver.switchTo().defaultContent();
        }

       /**
        * This method will switchToChildWindow based on index.
        * @param index
        */
      public void switchToChildWindow(int index)
        {
         Set<String> windows=driver.getWindowHandles();
         Iterator<String> iterator=windows.iterator();
         int i=1;
         log.info("Switched to: "+index+" window.");
         while(iterator.hasNext())
          {
            if(i == index)
            driver.switchTo().window(iterator.next());
            else
            i++;
          }
        }

       /**
        * This method will close all child window.
        * Switch to parent window.
        */
      public void closeAllTabsAndSwitchToParentWindow()
        {
            Set<String> windows=driver.getWindowHandles();
            Iterator<String> iterator=windows.iterator();
            String parentWindow=driver.getWindowHandle();
            while(iterator.hasNext())
              {
                if(!(iterator.next()).equalsIgnoreCase(parentWindow))
                    driver.close();
              }
            log.info("Switched to parent window.");
            driver.switchTo().window(parentWindow);
        }

       /**
        * This method will do browser forward navigation.
        */
      public void navigateForward()
        {
            log.info("Navigating Forward.");
            driver.navigate().forward();
        }

       /**
        * This method will do browser back navigation.
        */
      public void navigateBack()
        {
            log.info("Navigating Back.");
            driver.navigate().back();
        }
   }

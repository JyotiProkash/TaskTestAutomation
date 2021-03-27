package com.hybridframework.qa.helper.javascript;

import com.hybridframework.qa.helper.frame.FrameHelper;
import com.hybridframework.qa.helper.logger.LoggerHelper;
import com.hybridframework.qa.helper.wait.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class JavaScriptHelper
  {
      private WebDriver driver;
      private Logger log= LoggerHelper.getLogger(JavaScriptHelper.class);
      WaitHelper waitHelper;

      /**
       * This method will click on element.
       * @param element
       */
      public void clickElement(WebElement element)
         {
             executeScript("arguments[0].click();",element);
         }

      public JavaScriptHelper(WebDriver driver) throws IOException
         {
            this.driver=driver;
            log.info("JavaScriptHelper has been initialised.");
             waitHelper=new WaitHelper(driver);
         }
      public Object executeScript(String script)
         {
            JavascriptExecutor exe=(JavascriptExecutor)driver;
            return exe.executeScript(script);
         }
      public Object executeScript(String script, Object...args)
         {
           JavascriptExecutor exe=(JavascriptExecutor)driver;
           return exe.executeScript(script,args);
         }
      public void scrollToElement(WebElement element)
         {
            log.info("Scroll to WebElement...");
            JavascriptExecutor js=(JavascriptExecutor)driver;
            js.executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
         }
      public void scrollToElementAndClick(WebElement element)
         {
           log.info("Scroll to WebElement and Click.");
           scrollToElement(element);
           element.click();
           log.info("Element is clicked: "+element.toString());
         }
      public void scrollIntoView(WebElement element)
         {
            log.info("Scroll till WebElement.");
            executeScript("arguments[0].scrollIntoView()",element);
         }
      public void scrollIntoViewAndClick(WebElement element)
        {
            scrollIntoView(element);
            element.click();
            log.info("Element is clicked: "+element.toString());
        }
      public void scrollDownVertically()
        {
            log.info("Scrolling Down Vertically...");
            executeScript("window.scrollTo(0,document.body.scrollHeight)");
        }
      public void scrollUpVertically()
        {
           log.info("Scrolling Up Vertically...");
           executeScript("window.scrollTo(0,-document.body.scrollHeight)");
        }

      /**
       * This method will scroll till given pixel.
       * @param pixel
       */
        public void scrollDownByPixel(int pixel)
          {
             executeScript("window.scrollBy(0,"+pixel+")");
          }
        public void scrollUpByPixel(int pixel)
          {
             executeScript("window.scrollBy(0,-"+pixel+")");
          }
      public void scrollDownToPixel(int pixel)
      {
          executeScript("window.scrollTo(0,"+pixel+")");
      }
      public void scrollDownVerticallyByPixel(int currentLocationPixel, int expectedLocationPixel) throws InterruptedException
       {
          int initialPixel=currentLocationPixel;
          for(int i=initialPixel+200;i<=expectedLocationPixel;i+=200)
            {
                if(i>expectedLocationPixel)
                    break;
                executeScript("window.scrollTo(0,"+i+")");
                waitHelper.waitBrowser(500);

            }
       }

      public void scrollUpVerticallyByPixel(int currentLocationPixel, int loopCount) throws InterruptedException
       {
          int initialPixel=currentLocationPixel;
          int pixel=initialPixel;
          for(int i=1;i<=loopCount;i++)
           {
               if(i>loopCount)
                   break;
               executeScript("window.scrollBy(0,-"+pixel+")");
               pixel+=200;
               waitHelper.waitBrowser(500);

           }
       }
      public void scrollUpToPixel(int pixel)
      {
          executeScript("window.scrollTo(0,-"+pixel+")");
      }

      /**
       * This method will zoom screen by 100%.
       */
      public void zoomInBy100Percentage()
        {
           executeScript("document.body.style.zoom='100%'");
        }

      /**
       * This method will zoom screen by 60%.
       */
      public void zoomInBy60Percentage()
        {
           executeScript("document.body.style.zoom='60%'");
        }
  }

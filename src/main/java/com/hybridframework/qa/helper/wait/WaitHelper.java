package com.hybridframework.qa.helper.wait;

import com.hybridframework.qa.helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jyoti 15/04/2020
 */
public class WaitHelper
  {
      private WebDriver driver;
      private Logger log= LoggerHelper.getLogger(WaitHelper.class);
      public  WaitHelper(WebDriver driver) throws IOException
        {
            this.driver=driver;
            //log.debug("WaitHelper: "+this.driver.hashCode());
            log.info("WaitHelper object is created.");
        }

      /**
       * This is ImplicitWait method
       * @param timeout
       * @param unit
       */
      public void setImplicitWait(long timeout, TimeUnit unit)
        {
            log.info("Implicit wait has been set to:"+timeout);
            driver.manage().timeouts().implicitlyWait(timeout,unit==null ? TimeUnit.SECONDS : unit);
        }
      /**
       * This is Wait for the Browser method
       */
      public void waitBrowser(long milliSec) throws InterruptedException {
          log.info("Wait a browser to:"+milliSec);
          Thread.sleep(milliSec);
      }

      /**
       * This will help us to get WebDriverWait object
       * @param timeOutInSeconds
       * @param pollingEveryInMilliSec
       * @return
       */
      private  WebDriverWait getWait(long timeOutInSeconds, long pollingEveryInMilliSec)
        {
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds),Duration.ofMillis(pollingEveryInMilliSec));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(ElementNotVisibleException.class);
            wait.ignoring(StaleElementReferenceException.class);
            wait.ignoring(NoSuchFrameException.class);
            return wait;
        }

      /**
       * This method will make sure element is visible.
       * @param element
       * @param timeOutInSeconds
       * @param pollingEveryInMilliSec
       */
      public void waitForElementVisibleWithPollingTime(WebElement element, long timeOutInSeconds, long pollingEveryInMilliSec)
        {
            log.info("Waiting for :"+element.toString()+"for :"+timeOutInSeconds+"seconds");
            WebDriverWait wait=getWait(timeOutInSeconds,pollingEveryInMilliSec);
            wait.until(ExpectedConditions.visibilityOf(element));
            log.info("Element is visible now.");
        }

      /**
       * This method will make sure ElementClickable.
       * @param element
       * @param timeOutInSeconds
       */
      public void waitForElementClickable(WebElement element, long timeOutInSeconds)
        {
           log.info("Waiting for :"+element.toString()+"for :"+timeOutInSeconds+"seconds");
           WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
           wait.until(ExpectedConditions.elementToBeClickable(element));
           log.info("Element is clickable now.");
        }

      /**
       * This method will make sure ElementNotPresent.
       * @param element
       * @param timeOutInSeconds
       * @return
       */
      public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds)
        {
           log.info("Waiting for :"+element.toString()+"for :"+timeOutInSeconds+"seconds");
           WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
           boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
           log.info("Element is invisible now.");
           return status;
        }

      /**
       * This method will make sure FrameToBeAvailableAndSwitchToIt
       * @param element
       * @param timeOutInSeconds
       */
      public void waitForFrameToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds)
        {
           log.info("Waiting for :"+element.toString()+"for :"+timeOutInSeconds+"seconds");
           WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
           wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
           log.info("Frame is invisible and switched.");
        }

      /**
       * This method will give FluentWait object.
       * @param timeOutInSeconds
       * @param pollingEveryInMilliSec
       * @return
       */
      private Wait<WebDriver> getFluentWait(long timeOutInSeconds, long pollingEveryInMilliSec)
        {
            Wait<WebDriver> fWait=new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(timeOutInSeconds))
                    .pollingEvery(Duration.ofMillis(pollingEveryInMilliSec)).ignoring(NoSuchElementException.class);
            return fWait;

        }

      /**
       * This method is waitForElement for FluentWait.
       * @param element
       * @param timeOutInSeconds
       * @param pollingEveryInMilliSec
       * @return
       */
      public WebElement waitForElement(WebElement element, long timeOutInSeconds, long pollingEveryInMilliSec)
        {
            log.info("Waiting for :"+element.toString()+"for :"+timeOutInSeconds+" seconds");
            Wait<WebDriver> fWait=getFluentWait(timeOutInSeconds,pollingEveryInMilliSec);
            fWait.until(ExpectedConditions.visibilityOf(element));
            log.info("Element is visible.");
            return element;
        }

      /**
       * This method is for pageLoadTime.
       * @param timeout
       * @param unit
       */
      public void pageLoadTime(long timeout, TimeUnit unit)
        {
            log.info("Waiting for page to load for :"+unit+" seconds");
            driver.manage().timeouts().pageLoadTimeout(timeout,unit);
            log.info("Page is loaded.");
        }
  }


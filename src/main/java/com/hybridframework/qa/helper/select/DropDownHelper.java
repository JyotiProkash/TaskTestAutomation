package com.hybridframework.qa.helper.select;

import com.hybridframework.qa.helper.alert.AlertHelper;
import com.hybridframework.qa.helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jyoti 19/04/2020
 */
public class DropDownHelper
  {
      private WebDriver driver;
      private Logger log= LoggerHelper.getLogger(DropDownHelper.class);

      public DropDownHelper(WebDriver driver) throws IOException
        {
            this.driver=driver;
            log.info("DropdownHelper object is created...");
        }
      public void selectUsingValue(WebElement element, String value)
        {
            Select select=new Select(element);
            log.info("selectUsingValue and value is: "+value);
            select.selectByValue(value);
        }
      public void selectUsingIndex(WebElement element, int index)
        {
           Select select=new Select(element);
           log.info("selectUsingIndex and index is: "+index);
           select.selectByIndex(index);
        }
      public void selectUsingVisibleText(WebElement element, String visibleText)
        {
           Select select=new Select(element);
           log.info("selectUsingVisibleText and visibleText is: "+visibleText);
           select.selectByVisibleText(visibleText);
        }
      public void deSelectUsingValue(WebElement element, String value)
        {
           Select select=new Select(element);
           log.info("deSelectUsingValue and value is: "+value);
           select.deselectByValue(value);
        }
      public void deSelectUsingIndex(WebElement element, int index)
        {
           Select select=new Select(element);
           log.info("deSelectUsingIndex and index is: "+index);
           select.deselectByIndex(index);
        }
      public void deSelectUsingVisibleText(WebElement element, String visibleText)
        {
           Select select=new Select(element);
           log.info("deSelectUsingVisibleText and visibleText is: "+visibleText);
           select.deselectByVisibleText(visibleText);
        }
      public List<String> getAllDropDownData(WebElement element)
        {
            Select select=new Select(element);
            List<WebElement> elementList=select.getOptions();
            List<String> valueList=new LinkedList<String>();
            for(WebElement ele:elementList)
               {
                   log.info(ele.getText());
                   valueList.add(ele.getText());
               }
            return valueList;
        }

  }

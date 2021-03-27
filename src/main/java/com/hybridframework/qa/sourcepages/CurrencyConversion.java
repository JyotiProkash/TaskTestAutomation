package com.hybridframework.qa.sourcepages;

import com.hybridframework.qa.helper.javascript.JavaScriptHelper;
import com.hybridframework.qa.helper.logger.LoggerHelper;
import com.hybridframework.qa.helper.select.DropDownHelper;
import com.hybridframework.qa.helper.wait.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jyoti 23/03/2021
 */
public class CurrencyConversion
  {
      private WebDriver driver;
      private final Logger log= LoggerHelper.getLogger(CurrencyConversion.class);
      WaitHelper waitHelper;
      JavaScriptHelper javaScriptHelper;
      DropDownHelper dropDownHelper;

      public CurrencyConversion(WebDriver driver) throws IOException
        {
            this.driver=driver;
            PageFactory.initElements(driver,this);
            waitHelper=new WaitHelper(driver);
            javaScriptHelper=new JavaScriptHelper(driver);
            dropDownHelper=new DropDownHelper(driver);
        }

      @FindBy(xpath ="//label[contains(text(),'Sell')]//following::input[1]")
      WebElement sellTextField;

      @FindBy(xpath ="//label[contains(text(),'Buy')]//following::input[1]")
      WebElement buyTextField;

      @FindBy(css ="span.flag-icon-small.flag-icon-us")
      WebElement usFlagIcon;

      @FindBy(xpath ="//button[contains(text(),'Filter')]")
      WebElement filterButton;

      @FindBy(xpath ="//button[contains(text(),'Clear filter')]")
      WebElement clearFilterButton;

      @FindBy(xpath ="//tr[@class='ng-scope']//self::tr")
      List<WebElement> recordAllRow;

      @FindBy(css ="span.dropup")
      WebElement countrySelectFooterOption;

      @FindBy(css ="button#countries-dropdown")
      WebElement countryDropDown;

      @FindBy(css ="ul[class='dropdown-menu'][aria-labelledby='countries-dropdown'] li a")
      List<WebElement> countryList;

      @FindBy(xpath ="//*[@id=\"currency-exchange-app\"]/div/div/div[2]/div[1]/form/div[1]/div/div[1]/span/span[2]/span")
      WebElement sellCurrencyOption;

      @FindBy(xpath ="//tbody[@class='ng-scope']//tr[1]//td[4]")
      WebElement companyProvidedAmount;

      @FindBy(xpath ="//tbody[@class='ng-scope']//tr[1]//td[5]")
      WebElement swedBankAmount;


      // Method filtered by Buy Amount
      public List<WebElement> enterValueBuyAmountAndSellAmountBoxEmpty(String buyAmount) throws InterruptedException
        {
            javaScriptHelper.scrollDownToPixel(1000);
            waitHelper.waitForElement(usFlagIcon,20,5);
            sellTextField.clear();
            buyTextField.clear();
            buyTextField.sendKeys(buyAmount);
            filterButton.click();
            waitHelper.waitBrowser(8000);
            javaScriptHelper.scrollDownVerticallyByPixel(1000,3000);
            return recordAllRow;
        }

      // Method filtered by Sell Amount
      public List<WebElement> enterValueSellAmountAndBuyAmountBoxEmpty(String sellAmount) throws InterruptedException
        {
            javaScriptHelper.scrollDownToPixel(1000);
            waitHelper.waitForElement(usFlagIcon,20,5);
            sellTextField.clear();
            buyTextField.clear();
            sellTextField.sendKeys(sellAmount);
            filterButton.click();
            waitHelper.waitBrowser(8000);
            javaScriptHelper.scrollDownVerticallyByPixel(1000,3000);
            return recordAllRow;
        }

      //Method select country from country dropdown
      public WebElement selectCountryFromFooter(String countryName) throws InterruptedException
        {
            javaScriptHelper.scrollDownVertically();
            waitHelper.waitForElement(countrySelectFooterOption,20,5);
            countrySelectFooterOption.click();
            waitHelper.waitForElement(countryDropDown,20,5);
            countryDropDown.click();
            waitHelper.waitBrowser(1000);
            Iterator<WebElement> country=countryList.iterator();
            while (country.hasNext())
              {
                  WebElement ele=country.next();
                  if(ele.getText().equalsIgnoreCase(countryName))
                    {
                        ele.click();
                        break;
                    }
              }
            waitHelper.pageLoadTime(20,TimeUnit.SECONDS);
            javaScriptHelper.scrollDownVertically();
            waitHelper.waitBrowser(5000);
            javaScriptHelper.scrollUpVertically();
            javaScriptHelper.scrollDownToPixel(1000);
            waitHelper.waitBrowser(6000);
            return sellCurrencyOption;
        }

      public ArrayList<Double> lossAmountCalculation(String countryName,String sellAmount) throws InterruptedException
        {

          javaScriptHelper.scrollDownVertically();
          waitHelper.waitForElement(countrySelectFooterOption,20,5);
          countrySelectFooterOption.click();
          waitHelper.waitForElement(countryDropDown,20,5);
          countryDropDown.click();
          waitHelper.waitBrowser(1000);
          Iterator<WebElement> country=countryList.iterator();
          while (country.hasNext())
          {
              WebElement ele=country.next();
              if(ele.getText().equalsIgnoreCase(countryName))
              {
                  ele.click();
                  break;
              }
          }
          waitHelper.pageLoadTime(20,TimeUnit.SECONDS);
          javaScriptHelper.scrollDownVertically();
          waitHelper.waitBrowser(5000);
          javaScriptHelper.scrollUpVertically();
          javaScriptHelper.scrollDownToPixel(1000);
          waitHelper.waitBrowser(6000);

          Double convertedCompanyProvidedAmount=Double.parseDouble(String.valueOf(companyProvidedAmount.getText()));
          String[] arrOfStr1 = swedBankAmount.getText().split("[()]");
          Double convertedSwedBankAmount=Double.parseDouble(arrOfStr1[0]);
          Double expectedLossAmount=Double.parseDouble(arrOfStr1[1]);
          Double lossAmount= 0.00;
          ArrayList amount = new ArrayList<Double>(Arrays.asList());

            if(convertedSwedBankAmount-convertedCompanyProvidedAmount<0)
              {
                  lossAmount=convertedSwedBankAmount-convertedCompanyProvidedAmount;
                  DecimalFormat df2 = new DecimalFormat("###.##");
                  Double calculatedLossAmount=Double.valueOf(df2.format(lossAmount));
                  //Actual loss amount after calculation is added into 1st index of ArrayList amount.add(calculatedLossAmount);
                  amount.add(calculatedLossAmount);
                  //Expected loss amount that shows in web as red text is added into 2nd index of ArrayList amount.add(expectedLossAmount);
                  amount.add(expectedLossAmount);
              }
            return amount;
        }
  }

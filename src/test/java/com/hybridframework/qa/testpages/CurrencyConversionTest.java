package com.hybridframework.qa.testpages;

import com.hybridframework.qa.helper.assertion.AssertionHelper;
import com.hybridframework.qa.sourcepages.CurrencyConversion;
import com.hybridframework.qa.testbase.TestBase;
import com.hybridframework.qa.utilities.PropertyManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jyoti 23/03/2021
 */

public class CurrencyConversionTest extends TestBase
  {

    public CurrencyConversion cc;

    public CurrencyConversionTest() throws IOException{
      }

    @Test(priority = 1)
    public void verifyFunctionalityForBuyAmountAndSellAmountBoxEmpty() throws InterruptedException, IOException {
          cc= PageFactory.initElements(driver, CurrencyConversion.class);
          List<WebElement> totalRecord=cc.enterValueBuyAmountAndSellAmountBoxEmpty(PropertyManager.getProperty("testdata.properties", "buy.amount.value.tc1"));
          AssertionHelper.verifyRecordCount(totalRecord.size(),31);
      }

    @Test(priority = 2)
    public void verifyFunctionalityForSellAmountAndBuyAmountBoxEmpty() throws InterruptedException, IOException {
          cc= PageFactory.initElements(driver, CurrencyConversion.class);
          List<WebElement> totalRecord=cc.enterValueSellAmountAndBuyAmountBoxEmpty(PropertyManager.getProperty("testdata.properties", "sell.amount.value.tc2"));
          AssertionHelper.verifyRecordCount(totalRecord.size(),31);
      }

    @Test(priority = 3)
    public void verifyCurrencyOptionChangedAndCurrencyRatesUpdatedBasedOnCountrySelected() throws InterruptedException, IOException {
        cc= PageFactory.initElements(driver, CurrencyConversion.class);
        WebElement getCurrencyOption=cc.selectCountryFromFooter(PropertyManager.getProperty("testdata.properties", "footer.select.country.tc3"));
        AssertionHelper.verifySelectedCurrencyOption(getCurrencyOption.getText(),PropertyManager.getProperty("testdata.properties", "country.change.selected.currency.option.tc3"));
      }
    @Test(priority = 4)
    public void verifyLossAmountWhenBankProviderAmountForSellingIsLowerThanTheCompanyProvidedAmount() throws InterruptedException, IOException {
          cc= PageFactory.initElements(driver, CurrencyConversion.class);
          //Actual loss amount can be fetched from 1st index of ArrayList LossAmount.get(0) and expected loss amount can be fetched from 2nd index of ArrayList LossAmount.get(1)
          ArrayList<Double> LossAmount=cc.lossAmountCalculation(PropertyManager.getProperty("testdata.properties", "footer.select.country.tc4"),PropertyManager.getProperty("testdata.properties", "sell.amount.value.tc4"));
          //Compare calculated or actual loss amount and expected loss amount
          AssertionHelper.verifyActualAndExpectedAmount(LossAmount.get(0),LossAmount.get(1));
      }

  }

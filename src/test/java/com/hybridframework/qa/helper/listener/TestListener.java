package com.hybridframework.qa.helper.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.hybridframework.qa.helper.logger.LoggerHelper;
import com.hybridframework.qa.testbase.TestBase;
import com.hybridframework.qa.utilities.ExtentManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Jyoti 25/04/2020
 */
public class TestListener implements ITestListener {

    private static ExtentReports extent;
    //private static ExtentTest test;
    private Logger log = LoggerHelper.getLogger(TestListener.class);
    private static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
    public TestListener() throws IOException {

    }

    /**
     * Invoked each time before a test will be invoked. The <code>ITestResult</code> is only partially
     * filled with the references to class, method, start millis and status.
     *
     * @param result the partially filled <code>ITestResult</code>
     * @see ITestResult#STARTED
     */
    public void onTestStart(ITestResult result)
      {
        //ExtentTest test=extent.createTest(result.getTestClass().getName()+" :: "+result.getMethod().getMethodName());
        ExtentTest test=extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        log.info(result.getMethod().getMethodName()+" started.");
      }

    /**
     * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt; tag
     * and calling all their Configuration methods.
     *
     * @param context
     */
    public void onStart(ITestContext context)
      {
          try
           {
              extent=ExtentManager.getInstance();
              //extent.createTest(context.getName());
              extent.createTest(context.getCurrentXmlTest().getName());
              log.info(context.getCurrentXmlTest().getName()+" started.");
           }
          catch (IOException e)
           {
              e.printStackTrace();
           }
      }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    public void onTestSuccess(ITestResult result)
      {
         String logText="<b>"+result.getMethod().getMethodName()+" successful</b>";
         Markup m= MarkupHelper.createLabel(logText, ExtentColor.GREEN);
         extentTest.get().log(Status.PASS,m);
         log.info(result.getMethod().getMethodName()+" passed.");

          String path = captureScreen(((TestBase)result.getInstance()).driver, result.getMethod().getMethodName());

          try
          {
              //path = captureScreen(((TestBase)result.getInstance()).driver, result.getMethod().getMethodName());
              //extentTest.get().fail("<b><font color=red>" + "screenshot of failure" + "</font></b>", MediaEntityBuilder.createScreenCaptureFromPath(Paths.get(path).toAbsolutePath().toString()).build());
              extentTest.get().pass("<b><font color=green>" + "screenshot of page" + "</font></b>",
                      MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
          }
          catch (IOException e)
          {
              extentTest.get().fail("<b><font color=green>"+"Screenshot Capture failed"+"</font></b>");
          }
      }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    public void onTestFailure(ITestResult result)
      {

          String logText = "<b>" + result.getMethod().getMethodName() + " failed.</b>";
          Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
          extentTest.get().log(Status.FAIL, m);
          log.info(result.getMethod().getMethodName()+" failed.");

          String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details><summary><b><font color=red>" +
                "Exception, click to see details:" + "</font></b></summary>" +
                exceptionMessage.replaceAll(",", "<br>") + "</details>\n");
        //WebDriver driver = ((TestBase)result.getInstance()).driver;
          String path = captureScreen(((TestBase)result.getInstance()).driver, result.getMethod().getMethodName());

          try
          {
             //path = captureScreen(((TestBase)result.getInstance()).driver, result.getMethod().getMethodName());
             //extentTest.get().fail("<b><font color=red>" + "screenshot of failure" + "</font></b>", MediaEntityBuilder.createScreenCaptureFromPath(Paths.get(path).toAbsolutePath().toString()).build());
            extentTest.get().fail("<b><font color=red>" + "screenshot of failure" + "</font></b>",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
          }
        catch (IOException e)
          {
            extentTest.get().fail("<b><font color=red>"+"Test failed, cannot attach screenshot"+"</font></b>");
          }
      }

    /**
     * Invoked each time a method fails but has been annotated with successPercentage and this failure
     * still keeps it within the success percentage requested.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
     */

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    public void onTestSkipped(ITestResult result)
      {
          String logText="<b>"+result.getMethod().getMethodName()+" skipped.</b>";
          Markup m= MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
          extentTest.get().log(Status.SKIP,m);
          log.info(result.getMethod().getMethodName()+" skipped.");
      }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    /**
     * Invoked each time a test fails due to a timeout.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     */
    public void onTestFailedWithTimeout(ITestResult result)
      {
          String logText="<b>Failed "+result.getMethod().getMethodName()+" for timeout.</b>";
          Markup m= MarkupHelper.createLabel(logText, ExtentColor.RED);
          extentTest.get().fail(m);
      }

    /**
     * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have run
     * and all their Configuration methods have been called.
     *
     * @param context
     */
    public void onFinish(ITestContext context)
       {
           if(extentTest!=null)
               extent.flush();
           log.info(context.getName()+" finished.");
       }

    public String captureScreen(WebDriver driver, String screenShotName)
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

        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);


      }
}
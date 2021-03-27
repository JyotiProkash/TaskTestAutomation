package com.hybridframework.qa.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.hybridframework.qa.helper.resource.ResourceHelper;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Jyoti 22/04/2020
 */
public class ExtentManager
  {
      private static ExtentReports extent;
      public static ExtentReports getInstance() throws IOException
        {
            if(extent==null)
              {
                  return createInstance();
              }
            else
              {
                  return extent;
              }
        }
      private static ExtentReports createInstance() throws IOException {
            Date d=new Date();
            //String fileName="AutomationReport_"+d.toString().replace(":","_").replace(" ","_")+".html";
            String fileName="ExtentReport.html";
            String directory=ResourceHelper.getResourcePath("src/test/output/TestReport/");
            //new File(directory).mkdirs();
            String path=directory+fileName;
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
            /*htmlReporter.config().setDocumentTitle("Selenium Automation");
            htmlReporter.config().setTheme(Theme.DARK);
            htmlReporter.config().setEncoding("utf-8");
            htmlReporter.config().setReportName("Automation Execution Report");*/
            //htmlReporter.loadXMLConfig(ResourceHelper.getResourcePath("src/main/resources/ConfigFiles/extent-config.xml"));
            htmlReporter.loadXMLConfig(ResourceHelper.getResourcePath("extent-config.xml"));
            extent=new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Application Name", "ExtentReport");
            extent.setSystemInfo("Platform", System.getProperty("os.name"));
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser Name",PropertyManager.getProperty("basicinfo.properties","browser"));
            return extent;

        }
  }


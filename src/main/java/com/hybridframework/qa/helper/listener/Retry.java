package com.hybridframework.qa.helper.listener;

import com.hybridframework.qa.helper.frame.FrameHelper;
import com.hybridframework.qa.helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.io.IOException;

public class Retry implements IRetryAnalyzer 
{
	private int retryCount=0;
	private final int MAX_RETRY_COUNT=3;
	private Logger log= LoggerHelper.getLogger(Retry.class);

	public Retry() throws IOException {
	}

	public boolean retry(ITestResult arg0)
	 {
		if(retryCount<MAX_RETRY_COUNT)
		{
			try 
			{
				log.info("Retrying test "+arg0.getName()+"with status "+getResultStatusName(arg0.getStatus())+" for the"+(retryCount+1)+" times.");
				retryCount++;
				return true;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		}
		return false;
	 }

	public String getResultStatusName(int status)
	 {
	 	String resultName=null;
	 	if(status==1)
	 		resultName="SUCCESS";
	 	if(status==2)
	 		resultName="FAILURE";
	 	if(status==3)
	 		resultName="SKIP";
	 	return resultName;
	 }

}

package com.hybridframework.qa.helper.assertion;

import com.hybridframework.qa.helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.io.IOException;

/**
 * Created by Jyoti 18/04/2020
 */
public class AssertionHelper
   {
       private static Logger log;

       static {
           try {
               log = LoggerHelper.getLogger(AssertionHelper.class);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

       public static void verifySelectedCurrencyOption(String s1, String s2)
         {
             log.info("Verifying Currency Option: "+s1+" with "+s2);
             Assert.assertEquals(s1,s2);
         }

       public static void verifyActualAndExpectedAmount(Double s1, Double s2)
         {
           log.info("Verifying amount: "+s1+" with "+s2);
           Assert.assertEquals(s1,s2);
         }

       public static void verifyRecordCount(int s1, int s2)
         {
           log.info("Verifying record count: "+s1+" with "+s2);
           Assert.assertEquals(s1,s2);
         }
       public static void makeTrue()
         {
             log.info("Making script PASS");
             Assert.assertTrue(true);
         }
       public static void makeTrue(String message)
         {
             log.info("Making script PASS"+message);
             Assert.assertTrue(true, message);
         }
       public static void makeFalse()
         {
             log.info("Making script FAIL");
             Assert.assertTrue(false);
         }
       public static void makeFalse(String message)
         {
             log.info("Making script FAIL"+message);
             Assert.assertTrue(false,message);
         }
       public static void verifyTrue(boolean status)
         {
             log.info("Making script PASS"+status);
             Assert.assertTrue(status);
         }
       public static void verifyFalse(boolean status)
         {
             log.info("Making script FAIL"+status);
             Assert.assertFalse(status);
         }
       public static void verifyNull(String s1)
         {
             log.info("Verifying object is null...");
             Assert.assertNull(s1);
         }
       public static void verifyNotNull(String s1)
         {
             log.info("Verifying object is not null...");
             Assert.assertNotNull(s1);
         }
       public static void fail()
         {
             Assert.assertTrue(false);
         }
       public static void pass()
         {
             Assert.assertTrue(true);
         }
       public static void updateTestStatus(boolean status)
         {
             if(status)
             {
                 pass();
             }
             else
             {
                 fail();
             }
         }
   }

# Task Automation on Currency Conversion Calculation

# Overview<br>
**Automated Test scripts:** <br>
Verify functionality for buy amount when sell amount box is empty.<br>
Verify functionality for sell amount when buy amount box is empty.<br>
Verify currency option changed and currency rates updated based on country selected.<br>
Verify loss amount when bank provider's exchange amount for selling is lower than the company's provided amount.<br>

**Test Data:** <br>
Following Path: src/main/resources/ConfigFiles<br>
Test Data File Name: testdata.properties<br>
Base URL and Browser Info Config File Name: basicinfo.properties<br>

**Environment/Platform/Tools/Language/Library:**<br>
Java<br>
JDK 1.8<br>
Selenium WebDriver - Hybrid Framework<br>
TestNG<br>
TestNG Extent report<br>
Linux(Ubuntu) 20.04<br>
IntelliJIdea Community Edition 2019.3

**Script Writing Information:**<br>
Page Source Class: CurrencyConversion<br>
Location: (src/main/java/com/hybridframework/qa/sourcepages)<br>
Test Class: CurrencyConversionTest<br>
Location: (src/test/java/com/hybridframework/qa/testpages)<br>
TestNG Regression Suite xml File Path: src/main/resources/testSuite<br>
TestNG Regression Suite xml Name: regressionSuite.xml<br>

**Test Run:**<br>
clean mvn test

**HTML Test Report:**<br>
After test execution, to get test report, visit as follows.<br>
Location: src/test/output/TestReport<br>
Test Report File Name: ExtentReport.html<br>

**Test Execution Recorded Video:**<br>
Location: src/main/resources/TestExecutionRecordedVideo<br>
Video File Name: TestExecutionRecordings


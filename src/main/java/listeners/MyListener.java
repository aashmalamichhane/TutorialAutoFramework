package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReporter;
import utils.Utilities;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.FileHandler;

public class MyListener implements ITestListener {

    ExtentReports extentReport;
    ExtentTest extentTest;
    @Override
    public void onStart(ITestContext context)
    {
        try {
            extentReport = ExtentReporter.generateExtentReport();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        extentTest = extentReport.createTest(testName);
        extentTest.log(Status.INFO, testName + "started executing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        extentTest.log(Status.PASS , testName + "got successfully executed");
        System.out.println(testName + " test is passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = null;

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }
        catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        if (driver != null) {

            String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
            extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
        }
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, result.getName() + " got failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, testName+ " got skipped");
        System.out.println(testName + " test is skipped");
        System.out.println(result.getThrowable());


    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
    }


}

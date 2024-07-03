package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("pROJECT TEST STARTED");

    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        System.out.println(testName + " started to execute");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        System.out.println(testName + " test is passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        System.out.println(testName + " test is failed");
        System.out.println(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        System.out.println(testName + " test is skipped");
        System.out.println(result.getThrowable());


    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Project TEST Completed");

    }


}

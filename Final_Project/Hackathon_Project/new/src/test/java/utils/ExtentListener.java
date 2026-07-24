package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListener implements ITestListener {

    private static ExtentReports extent;

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        ExtentSparkReporter spark =
                new ExtentSparkReporter(
                        "test-output/ExtentReport-AllTests.html");

        spark.config().setDocumentTitle(
                "District Automation Report");

        spark.config().setReportName(
                "All Test Cases");

        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(
                        result.getMethod().getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().log(Status.PASS, "Test passed");

        String path =
                Screenshot.take(
                        result.getMethod().getMethodName()
                                + "_PASS");

        if (path != null) {

            try {

                test.get().addScreenCaptureFromPath(path);

            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().log(
                Status.FAIL,
                "Test failed: " + result.getThrowable());

        String path =
                Screenshot.take(
                        result.getMethod().getMethodName()
                                + "_FAIL");

        if (path != null) {

            try {

                test.get().addScreenCaptureFromPath(path);

            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().log(
                Status.SKIP,
                "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}
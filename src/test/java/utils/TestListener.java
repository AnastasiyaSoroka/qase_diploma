package utils;

import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info(String.format("==================== STARTING TEST %s ====================", iTestResult.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        takeScreenshot(iTestResult);
        log.info(String.format("==================== FINISHED TEST %s Duration: %ss ====================", iTestResult.getName(),
                getExecutionTime(iTestResult)));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.error(String.format("===== FAILED TEST %s Duration: %s =====", iTestResult.getName(),
                getExecutionTime(iTestResult)));
        log.error(iTestResult.getThrowable().getMessage());
        takeScreenshot(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(String.format("==================== SKIPPING TEST %s ====================", iTestResult.getName()));
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }

    @Attachment(value = "Last screen state", type = "image/png")
    private byte[] takeScreenshot(ITestResult iTestResult) {
        ITestContext context = iTestResult.getTestContext();
        try {
            if (null != context.getAttribute("driver")) {
                return ((TakesScreenshot) context.getAttribute("driver")).getScreenshotAs(OutputType.BYTES);
            } else return null;
        } catch (NoSuchSessionException ex) {
            return null;
        } catch (IllegalStateException ex) {
            return null;
        }
    }

}

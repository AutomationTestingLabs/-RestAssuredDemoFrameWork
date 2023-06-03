package api.utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports reports;
    public ExtentTest test;
    String repName;

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = reports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = reports.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.FAIL,"Test Failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        String TimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report" + TimeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
        sparkReporter.config().setReportName("Rest Assured Automation Report");
        sparkReporter.config().setDocumentTitle("AutomationExecutionReport");
        sparkReporter.config().setTheme(Theme.DARK);

        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Application","Pet Store User API");
        reports.setSystemInfo("OS",System.getProperty("os.name"));
        reports.setSystemInfo("User Name",System.getProperty("user.name"));
        reports.setSystemInfo("Environment","QA");
        reports.setSystemInfo("User","Navee Hans");
    }

    @Override
    public void onFinish(ITestContext context) {
        reports.flush();

    }
}

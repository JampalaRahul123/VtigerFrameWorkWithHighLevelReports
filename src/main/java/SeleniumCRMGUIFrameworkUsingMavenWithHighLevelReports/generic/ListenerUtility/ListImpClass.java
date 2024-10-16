package SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.ListenerUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.listeners.highlevellogs.BaseTest.*;
import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.webDriverUtility.UtilityClassObject;

public class ListImpClass implements ITestListener , ISuiteListener {
	
	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;
	
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		// Code To Get The TimeStamp WithOut Spaces and Colon
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results"); // To Set Document Title
		spark.config().setReportName("CRM Report"); // To set The Report Name
		spark.config().setTheme(Theme.DARK); // To Set The Report Theme
		// Add Environment Information And Create Test
		report = new ExtentReports();
		report.attachReporter(spark); //we have to pass spark configuration in attach reporter
		// To Set System Info
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}

	public void onFinish(ISuite suite) {
		System.out.println("Report BackUp");
		report.flush(); // To get the backup of report generated
		}

	public void onTestStart(ITestResult result) {
		System.out.println("===== ======>" + result.getMethod().getMethodName() + ">===START======");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO,result.getMethod().getMethodName()+"==> STARTED <====");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("===== ======>" + result.getMethod().getMethodName() + ">===END======");
		test.log(Status.PASS ,result.getMethod().getMethodName()+"==> COMPLETED <====");
	}

	public void onTestFailure(ITestResult result){
		String testName = result.getMethod().getMethodName();
		//Takes Screenshot
		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
		String srcFile=ts.getScreenshotAs(OutputType.BASE64);
		// Code To Get The TimeStamp WithOut Spaces and Colon
		String time = new Date().toString().replace(" ","_").replace(":","_");
		test.addScreenCaptureFromBase64String(srcFile, testName+"_"+time);
		test.log(Status.FAIL,result.getMethod().getMethodName()+"==> FAILED <====");
		}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP,result.getMethod().getMethodName()+"==> SKIPPED <====");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}
	
	public void onStart(ITestContext context) {
		
	}
	
	public void onFinish(ITestContext context) {
		
	}
}


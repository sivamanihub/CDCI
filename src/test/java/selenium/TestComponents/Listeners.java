package selenium.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import sivamani.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent= ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
	    test=extent.createTest(result.getMethod().getMethodName());
	    extentTest.set(test);
		
	  }
	 public void onTestSuccess(ITestResult result) {
		   
		 extentTest.get().log(Status.PASS, "Test Passed");
		  }
	
	@Override
	public void onTestFailure(ITestResult result) {
	    // not implemented
		extentTest.get().fail(result.getThrowable());
		try {
			d=(WebDriver) result.getTestClass().getRealClass().getField("d").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		
		String file=null;
		test.fail(result.getThrowable());
		try {
			 file= getScreenShot(result.getMethod().getMethodName(), d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(file,result.getMethod().getMethodName());
	  }
	 public void onFinish(ITestContext context) {
		    // not implemented
		 extent.flush();
		  }
}

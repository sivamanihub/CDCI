package selenium.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retryForFail implements IRetryAnalyzer {

	public int count=0,maxCount=1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(count>=maxCount)
		{
			return true;
		}
		return false;
	}

	
}

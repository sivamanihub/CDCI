package selenium.tests;


import java.io.IOException;
import selenium.TestComponents.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import selenium.TestComponents.BaseTest;
import sivamani.PagObjectModel.ProductCatalogue;
import sivamani.PagObjectModel.cartPages;

public class C168_ErrorValidation extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=retryForFail.class)
	public void LonginError() throws IOException {
		lp.LoginApplication("sivamani354@gmail.com", "Sivamani3@");
		String msg=lp.getErrorMessage() ;
		Assert.assertEquals("Incorrect email or password.",msg);
		tearDown();
		
	}	    
	@org.testng.annotations.Test
	public void submit() throws IOException, InterruptedException {
		String productName="ZARA COAT 3";
		ProductCatalogue pc=lp.LoginApplication("sivamani354@gmail.com", "sivamani");
		Productcat(productName);
		cartPages c=cartPage(productName);
	    Assert.assertTrue(c.selectCountry("india"));
	  
	}
}

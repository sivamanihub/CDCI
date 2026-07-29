package selenium.tests;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium.TestComponents.BaseTest;
import sivamani.PagObjectModel.ProductCatalogue;
import sivamani.PagObjectModel.cartPages;
import sivamani.PagObjectModel.myorder;

public class C167_PageObjectModel2 extends BaseTest {

	String productName="ZARA COAT 3";
	@org.testng.annotations.Test(dataProvider = "getData",groups="purchase")
	public <CheckoutPage> void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		ProductCatalogue pc=lp.LoginApplication(input.get("email"), input.get("Password"));
		List<WebElement> products=pc.getproductList();
		pc.addProductToCart(input.get("product"));
		
		Productcat(input.get("productName"));
		cartPages c=cartPage(input.get("productName"));
	    Assert.assertTrue(c.selectCountry("india")); 
	    tearDown();
	}	    
	
	//To verify zara coat3 is displayed or not
	@Test(dependsOnMethods = "submitOrder")
	public void Order()
	{
		ProductCatalogue pc=lp.LoginApplication("sivamani354@gmail.com", "Sivamani354@");
		myorder orderPage=pc.goToOrdersPage();
		Assert.assertTrue(orderPage.validateOrderproducts(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		/*HashMap<String, String> map=new HashMap();
		map.put("email", "sivamani354@gmail.com");
		map.put("Password", "Sivamani354@");                              this is 2nd method
		map.put("productName", "ZARA COAT 3");
		
		HashMap<String, String> map1=new HashMap();
		map1.put("email", "Sivamani3@gmail.com");
		map1.put("Password", "Mani354@");
		map1.put("productName", "ADIDAS ORIGINAL");*/
		List<HashMap<String, String >> data= getJsonDatatoMap(System.getProperty("user.dir")+"\\src\\test\\java\\Purches\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
}

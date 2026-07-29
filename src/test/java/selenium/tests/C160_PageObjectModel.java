package selenium.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import sivamani.PagObjectModel.LandingPage;
import sivamani.PagObjectModel.ProductCatalogue;
import sivamani.PagObjectModel.cartPages;

public class C160_PageObjectModel {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver d=new ChromeDriver();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String productName="ZARA COAT 3";
		String countryName="india";
		
		LandingPage lp=new LandingPage(d);
		lp.Goto(d);
		lp.LoginApplication("sivamani354@gmail.com", "Sivamani354@");
		
		ProductCatalogue pc=new ProductCatalogue(d);
		List<WebElement> product=pc.getproductList();
		pc.addProductToCart(productName);
		
		cartPages c=new cartPages(d);
	    Assert.assertTrue(c.validateCartproducts(productName));
	    c.clickCheckOut();
	    Assert.assertTrue(c.selectCountry(countryName));
	    
	}	    
}

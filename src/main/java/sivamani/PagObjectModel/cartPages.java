package sivamani.PagObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sivamani.AbstracComponents.AbstracComponents;

public class cartPages extends AbstracComponents {
	WebDriver d;
    String s="THANKYOU FOR THE ORDER.";
	public cartPages(WebDriver d) {
		super(d);
		this.d=d;
		PageFactory.initElements(d, this);
	}
	//hi how are you
	@FindBy(css=".cartSection h3")
	static
	List<WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement clickcheckout;
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement selectCountry;
	
	By findby= By.cssSelector(".ta-results");
	
	public static Boolean validateCartproducts(String productName)
	{
		Boolean match	=cartproducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	public void clickCheckOut()
	{
		clickcheckout.click();
		
	}
	
	public boolean selectCountry(String countryName) throws InterruptedException
	{
		 Actions a= new Actions(d);
		    a.sendKeys(selectCountry,countryName ).build().perform();
		    waitForElementToAppear(findby);
		    
		   
		    d.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();    
		    d.findElement(By.xpath("//a[text()='Place Order ']")).click();
		    String cm=d.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		    boolean bl=cm.equalsIgnoreCase("THANKYOU FOR THE ORDER.");
		    
		   return bl;
	}
	public boolean equalsIgnoreCase(String s) {
		String cm=d.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
	    boolean bl=cm.equalsIgnoreCase(s);// TODO Auto-generated method stub
		return bl;
	}
	
	
	
}

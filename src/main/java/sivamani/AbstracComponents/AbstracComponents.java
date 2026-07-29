package sivamani.AbstracComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sivamani.PagObjectModel.cartPages;

public class AbstracComponents {
	WebDriver d;
	public AbstracComponents(WebDriver d) {
		
		this.d=d;
		PageFactory.initElements(d, this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement myorder;
	
	
	

	public void waitForElementToAppear(By findby) throws InterruptedException
	{
		WebDriverWait w=new WebDriverWait(d, Duration.ofSeconds(100));
		w.until(ExpectedConditions.visibilityOfElementLocated(findby));
		//Thread.sleep(5000);
	}
	public void waitForWebElementToAppear(WebElement findby)
	{
		WebDriverWait w=new WebDriverWait(d, Duration.ofSeconds(100));
		w.until(ExpectedConditions.visibilityOf(findby));
	}
	
	public void waitForElementToDisapper(WebElement ele)
	{
		WebDriverWait w=new WebDriverWait(d, Duration.ofSeconds(100));
		w.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void waitForElementToDisappear(WebElement ele)
	{
	    WebDriverWait w = new WebDriverWait(d, Duration.ofSeconds(100));
	    w.until(ExpectedConditions.invisibilityOf(ele));
	}
	public cartPages goToCartPage()
	{
		cartHeader.click();
		cartPages c=new cartPages(d);
		return c;
	}
	
	public  sivamani.PagObjectModel.myorder goToOrdersPage()
	{
		myorder.click();
		
	   sivamani.PagObjectModel.myorder orderPage=new sivamani.PagObjectModel.myorder(d);
		
	   
		return orderPage;
	}

}

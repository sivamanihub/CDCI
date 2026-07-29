package sivamani.PagObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sivamani.AbstracComponents.AbstracComponents;

public class ProductCatalogue extends AbstracComponents{
	
	WebDriver driver;
	public ProductCatalogue(WebDriver d)
	{
		super(d);
		this.driver=d;
		PageFactory.initElements(d, this);
	}
    
	@FindBy(xpath = "//div[contains(@class,'col-lg-4')]")
	List <WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement clickcart;
	//d.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	
	
	By productBy= By.cssSelector(".mb-3");
	By toastMessage= By.cssSelector("#toast-container");
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	
	
	public  List<WebElement> getproductList() throws InterruptedException
	{
		waitForElementToAppear(productBy);
		
		return products;
	}
	
	public WebElement getProductByName(String productName) throws InterruptedException
	{
		WebElement prod= getproductList().stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		 return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		clickcart.click();
	}
}

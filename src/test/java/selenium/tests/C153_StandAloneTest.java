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

public class C153_StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver d=new ChromeDriver();
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 String productName="ZARA COAT 3";
		d.get("https://rahulshettyacademy.com/client");
		d.findElement(By.xpath("//input[@type='email']")).sendKeys("sivamani354@gmail.com");
		d.findElement(By.xpath("//input[@type='password']")).sendKeys("Sivamani354@");
		d.findElement(By.xpath("//input[@name='login']")).click();
		  WebDriverWait w=new WebDriverWait(d, Duration.ofSeconds(10));
		List<WebElement> items= d.findElements(By.xpath("//div[contains(@class,'col-lg-4')]"));
	    WebElement prod=items.stream().filter(s->
	    s.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
	    prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	   
	  
	    w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	    w.until(ExpectedConditions.invisibilityOf(d.findElement(By.cssSelector(".ng-animating"))));
	    d.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	    
	    List <WebElement> cartProducts = d.findElements(By.cssSelector(".cartSection h3"));
	    Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	    Assert.assertTrue(match);
	    d.findElement(By.cssSelector(".totalRow button")).click();
	    
	    Actions a= new Actions(d);
	    a.sendKeys(d.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
	    w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
	    d.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();    
	    d.findElement(By.xpath("//a[text()='Place Order ']")).click();
	    
	    String cm=d.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
	   Assert.assertTrue(cm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	   
	    

	}

}

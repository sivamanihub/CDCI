package sivamani.PagObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sivamani.AbstracComponents.AbstracComponents;

public class LandingPage extends AbstracComponents {
	
	WebDriver driver;
	public LandingPage(WebDriver d)
	{
		super(d);
		this.driver=d;
		PageFactory.initElements(d, this);
	}
    
	@FindBy(xpath = "//input[@type='email']")
	WebElement userEmail;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement password;
	
	@FindBy(xpath = "//input[@name='login']")
	WebElement submit;
	
	@FindBy(id="//div[@role='alert' and contains(@text(),'Incorrect email or password')]")
	WebElement ErrorMsg;
	
	public void Goto(WebDriver d)
	{
		d.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue LoginApplication(String email, String psw)
	{
		userEmail.sendKeys(email);
		password.sendKeys(psw);
		submit.click();
		ProductCatalogue pc= new ProductCatalogue(driver);
		return pc;
		
	}
	

	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(ErrorMsg);
		return ErrorMsg.getText();
	}
	

}

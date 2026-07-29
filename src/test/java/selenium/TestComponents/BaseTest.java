package selenium.TestComponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import sivamani.PagObjectModel.LandingPage;
import sivamani.PagObjectModel.ProductCatalogue;
import sivamani.PagObjectModel.cartPages;

public class BaseTest{
	
	public WebDriver d;
	public LandingPage lp;

	public WebDriver initializeDriver() throws IOException
	{
		String path = System.getProperty("user.dir")
		        + "\\src\\main\\java\\sivamani\\resources\\GlobalData.properties";
		
		Properties prop= new Properties();
		//FileInputStream file= new FileInputStream("C:\\Users\\shash.HACHIDORILAP020\\eclipse-workspace\\SeleniumFrameWorkDesign\\src\\main\\java\\sivamani\\resources\\GlobalData.properties");
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir")+"\\src\\main\\java\\sivamani\\resources\\GlobalData.properties");
		prop.load(file);
		
		
		String browserName= System.getProperty("browser")!=null?System.getProperty("browser"): prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options=new ChromeOptions();//ChromeOptions allows you to customize how Chrome starts (headless, disable GPU, window size, etc.)

			WebDriverManager.chromedriver().setup();// Meaning: It downloads the correct ChromeDriver automatically,You don't need to manually keep the driver in your project
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");//This checks if your browser variable contains the word headless.
				options.addArguments("--headless=new");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");
				options.addArguments("--window-size=1920,1080");
			}
			 d=new ChromeDriver(options);
			 d.manage().window().setSize(new Dimension(1440,900));// helpfull to run full screen 
			//this.d=d;
		}
		else if(browserName.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			 d=new EdgeDriver();
			//edge etc
		}
		else if(browserName.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			 d=new FirefoxDriver();
			//edge etc
		}

		
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		d.manage().window().maximize();
		return d;
	}
	   public List<HashMap<String, String>> getJsonDatatoMap(String string) throws IOException {

	        String jsonContent = FileUtils.readFileToString(
	                new File(System.getProperty("user.dir")+"\\src\\test\\java\\Purches\\PurchaseOrder.json"),
	                StandardCharsets.UTF_8
	        );

	        ObjectMapper mapper = new ObjectMapper();

	        List<HashMap<String, String>> data= mapper.readValue(jsonContent,new TypeReference<List<HashMap<String, String>>>() {
	                        });

	        return data;
	    }
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		d = initializeDriver();
		 lp=new LandingPage(d);
		lp.Goto(d);
		return lp;
		
	}
	
	public ProductCatalogue Productcat(String productName) throws InterruptedException
	{
		ProductCatalogue pc=new ProductCatalogue(d);
		List<WebElement> product=pc.getproductList();
		pc.addProductToCart(productName);
		return pc;
	}
	
	public cartPages cartPage(String productName)
	{
		cartPages c=new cartPages(d);
	    Assert.assertTrue(c.validateCartproducts(productName));
	    c.clickCheckOut();
	   
		return c;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		d.quit();
	}
	@Test
	public String getScreenShot(String testName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)d;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file= new File("C:\\Users\\shash.HACHIDORILAP020\\eclipse-workspace\\SeleniumFrameWorkDesign\\src\\test\\java\\Purches"+testName+".png");
		FileUtils.copyFile(source, file);
		
		return "C:\\Users\\shash.HACHIDORILAP020\\eclipse-workspace\\SeleniumFrameWorkDesign\\src\\test\\java\\Purches"+testName+".png";
		
	}
	
}

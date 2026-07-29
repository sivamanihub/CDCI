package selenium.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.TestComponents.BaseTest;
import sivamani.PagObjectModel.LandingPage;
import sivamani.PagObjectModel.ProductCatalogue;
import sivamani.PagObjectModel.cartPages;

public class StepDefinitionsImpli extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue pc;
	public cartPages c;
	String productName="ZARA COAT 3";
	@Given("I land on Ecommerce Page")
	public void i_land_on_ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password)
	{
	    pc = landingPage.LoginApplication(username, password);
	}
	
	/*@Given("^Logged in withusername (.+) and password (.+)$")
	public void logged_in_withusername_and_password(String userName, String password) {
		pc = landingPage.LoginApplication(userName, password);
	}*/

	@When("^Checkout (.+) to Cart$")
	public void checkout_product_to_cart(String productName) {

	    c = pc.goToCartPage();
	    Boolean match = c.validateCartproducts(productName);
	    Assert.assertTrue(match);

	}
	/*
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = pc.getproductList();
		pc.addProductToCart(productName);
	}*/

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmation_page(String message) {
		//String confirmMessage = c.validateCartproducts(productName);
		Assert.assertTrue(c.equalsIgnoreCase(message));
	}
	
	@Then("{string} message is dispalyed")
    public void message_is_displayed(String expectedMessage) {
		
        Assert.assertEquals(expectedMessage, landingPage.getErrorMessage());
    }
	
	
	
	
}
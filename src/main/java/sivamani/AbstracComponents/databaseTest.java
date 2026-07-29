package sivamani.AbstracComponents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;

public class databaseTest {

	public static void main(String[] args) throws SQLException {
		String host="localhost";
		String port="3306";
		Connection con=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/qadbt","root","sivamani");
		Statement s=con.createStatement();
		ResultSet rs= s.executeQuery("select * from Employeeinfo where name='ram';");
		rs.next();
		
			System.out.println(rs.getString("location"));
			System.out.println(rs.getString("id"));
			
			
			WebDriver driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://rahulshettyacademy.com/loginpagePractise/");
			
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys(rs.getString("id"));
			
		
		
	}
}

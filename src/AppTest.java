import static org.testng.Assert.assertEquals;

import java.net.http.WebSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {

	Connection con;
	Statement stmt;
	ResultSet rs;
	WebDriver driver = new ChromeDriver();
	String CustomerFirstName;
	String CustomerLastName;
	String CustomerEmail;
	String CustomerPassword;

	@BeforeTest
	public void MtSetup() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "1234");

		driver.get("https://smartbuy-me.com/account/register");
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void InsertIntoDataBase() throws SQLException {
		String Query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES (999, 'New Corp', 'Smith', 'John', '123456789', '123 Main St', 'Los Angeles', 'USA', 1370, 50000.00)";
		stmt = con.createStatement();
		int RowEffected = stmt.executeUpdate(Query);
		System.out.println(RowEffected);
	}

	@Test(priority = 2)
	public void UpdateDateBase() throws SQLException {
		String Query = "UPDATE customers SET creditLimit = 75000 WHERE customerNumber = 999;";
		stmt = con.createStatement();
		int RowEffected = stmt.executeUpdate(Query);
		System.out.println(RowEffected);
	}

	@Test(priority = 3)
	public void ReadDateBase() throws SQLException {
		String Query = "SELECT * FROM customers WHERE customerNumber = 999;";
		stmt = con.createStatement();
		rs = stmt.executeQuery(Query);

		while (rs.next()) {
			CustomerFirstName = rs.getNString("contactFirstName");
			CustomerLastName = rs.getNString("contactLastName");

			CustomerEmail = (CustomerFirstName + CustomerLastName).replaceAll("\\s+", "") + "@gmail.com";
			CustomerPassword = rs.getNString("addressLine1");

			System.out.println(CustomerFirstName);
			System.out.println(CustomerLastName);
			System.out.println(CustomerEmail);
			System.out.println(CustomerPassword);

		}
		WebElement FirstNameField = driver.findElement(By.id("customer[first_name]"));
		WebElement LastNameField = driver.findElement(By.id("customer[last_name]"));
		WebElement EmailField = driver.findElement(By.id("customer[email]"));
		WebElement PasswordField = driver.findElement(By.id("customer[password]"));

		FirstNameField.sendKeys(CustomerFirstName);
		LastNameField.sendKeys(CustomerLastName);
		EmailField.sendKeys(CustomerEmail);
		PasswordField.sendKeys(CustomerPassword);

	}

	@Test(priority = 4)
	public void DeleteDateBase() throws SQLException {

		String Query = "DELETE FROM customers WHERE customerNumber = 999;";
		stmt = con.createStatement();
		int RowEffected = stmt.executeUpdate(Query);
		System.out.println(RowEffected);

	}
	
}

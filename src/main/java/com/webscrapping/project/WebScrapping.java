package com.webscrapping.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebScrapping {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String dbURL = "jdbc:mysql://localhost:3306/ds";
		String user = "root";
		String password = "Somanath@123";

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		driver.manage().window().maximize();

		driver.get("https://demo.guru99.com/test/web-table-element.php");
		List<WebElement> companies = driver.findElements(By.xpath("//table[@class='dataTable']//td[1]"));
	//	companies.remove(0);//remove header if exist
		
		// Connect to MySQL database
        Connection connection = DriverManager.getConnection(dbURL, user, password);

        // Prepare SQL statement (replace with your table and column names)
        String sql = "INSERT INTO companies (cname) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(sql);

     
		for (WebElement company : companies) {

		String companyList=company.getText();
			statement.setString(1,companyList);
			 statement.executeUpdate();
		}
		 
		 
		 statement.close();
	        connection.close();
	        driver.quit();

	        System.out.println("Data saved to MySQL table successfully!");
	    
	}

}

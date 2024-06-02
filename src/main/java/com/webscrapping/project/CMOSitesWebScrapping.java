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

public class CMOSitesWebScrapping {

	public static void main(String[] args) throws SQLException, InterruptedException {
		// TODO Auto-generated method stub
		String dbURL = "jdbc:mysql://localhost:3306/ds";
		String user = "root";
		String password = "Somanath@123";

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		driver.manage().window().maximize();
		// Connect to MySQL database
		Connection connection = DriverManager.getConnection(dbURL, user, password);

		// Prepare SQL statement (replace with your table and column names)
		String sql = "INSERT INTO cmo_sites (site_name) VALUES (?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		int id = 0;
		do {
			driver.get("https://district-department-website.projectstatus.in/" + id);

			Thread.sleep(3000);

			String sitetitle = driver.getTitle();
			statement.setString(1, sitetitle);
			statement.executeUpdate();
			id++;
		} while (id <= 10);

		statement.close();
		connection.close();
		driver.quit();

		System.out.println("Data saved to MySQL table successfully!");

	}

}

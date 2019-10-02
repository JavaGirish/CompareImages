package com.tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

public class trivagoImage {
	
	public static WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium-Udemy\\chromedriver_win32\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		
		
		driver.get("https://magazine.trivago.com/");
		driver.manage().timeouts().pageLoadTimeout(2,TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	@Test
	public void compareImages() throws IOException {
		
		Screenshot image = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver,driver.findElement(By.xpath("//span[@itemprop='image']")));
		BufferedImage actual = image.getImage();
		ImageIO.write(image.getImage(),"png", new File("D:\\Selenium-Udemy\\CompareImages\\images\\new.jpeg"));
		
		BufferedImage expected = ImageIO.read(new File("D:\\Selenium-Udemy\\CompareImages\\images\\original.jpeg"));
		ImageDiffer id = new ImageDiffer();
		ImageDiff diff= id.makeDiff(expected, actual);
		
		if(diff.hasDiff()== true)
		{
			System.out.println("images are different");
		}			
			else {
				System.out.println("images are same");
			}
		}
		
	
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	
	}
	




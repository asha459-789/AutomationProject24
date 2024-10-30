package base;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BooksStore {
ChromeDriver driver;
@BeforeTest
public void setup()
{
	driver=new ChromeDriver();
	
}
@BeforeMethod
public void urlloading()
{
	driver.get("https://dcbookstore.com/");
	driver.manage().window().maximize();
}
@Test(priority=1)
public void signup()
{
	driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div/div/div[2]/div/div[2]/a[3]")).click();//sign up click
	driver.findElement(By.xpath("//*[@id=\"register-form\"]/div[2]/div/div[1]/span/input")).sendKeys("Asha");//first name
	driver.findElement(By.xpath("//*[@id=\"register-form\"]/div[2]/div/div[2]/span/input")).sendKeys("Vineeth");//last name
	driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("sdfsjd@gmail.com");//email
	driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("9123456789");//phone
	driver.findElement(By.xpath("//*[@id=\"register-form\"]/div[3]/div/div[3]/input")).click();//female
	driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("hai123#");//password
	driver.findElement(By.xpath("//*[@id=\"re-password\"]")).sendKeys("hai123#");//confirm password
	driver.findElement(By.xpath("//*[@id=\"register-form\"]/div[4]/div[2]/div[1]/input")).click();//sign up button
}
@Test(priority=2)
public void login()
{
	driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div/div/div[2]/div/div[2]/a[2]")).click();//login 
	driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("sdfsjd@gmail.com");//username
	driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("hai123#");//password
	//driver.findElement(By.xpath("//*[@id=\"Loginform\"]/div[2]/div[4]/div[1]/input")).click();
	driver.findElement(By.className("blulogin")).click();//login click
}

@Test(priority=4)
public void windowhandling()//categories search
{
	driver.findElement(By.xpath("//*[@id=\"navbar-collapse-2\"]/ul/li[2]/a")).click();//categories
	driver.findElement(By.xpath("//*[@id=\"navbar-collapse-2\"]/ul/li[2]/ul/li/div/div[1]/div/div/a[23]/span")).click();//childrens book

	String act=driver.getTitle();
	String exp="DcbooksStore";
	if(act.equalsIgnoreCase(exp))
		System.out.println("Same as Expected");
	else
		System.out.println("Not as Expected");

	String parentWindow=driver.getWindowHandle();
	
	System.out.println("Parent Window Title"+driver.getTitle());
	driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/div[2]/div[1]/div/div[6]/a")).click();//view more details
	Set<String>allwindowhandles=driver.getWindowHandles();
	for(String handle:allwindowhandles)
	{
		System.out.println(handle);
		if(!handle.equalsIgnoreCase(parentWindow))
		{
			driver.switchTo().window(handle);
		System.out.println("Child Window Title"+driver.getTitle());
		driver.findElement(By.xpath("//*[@id=\"AddCartForm\"]/div[2]/div[3]/div/div/div[3]/div[1]")).click();//add to cart
		System.out.println("Added to cart");
		driver.close();
	    }
	}
	driver.switchTo().window(parentWindow);
	
}
@Test(priority=3)
public void windowhandling2()//searchbar search
{
	driver.findElement(By.xpath("//*[@id=\"searchbox\"]")).sendKeys("Mathematics");
	driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[3]/div/div/div[2]/form/div/button/img")).click();//childrens book

	String act=driver.getTitle();
	String exp="DcbooksStore";
	if(act.equalsIgnoreCase(exp))
		System.out.println("Same as Expected");
	else
		System.out.println("Not as Expected");

	String parentWindow=driver.getWindowHandle();
	
	System.out.println("Parent Window Title"+driver.getTitle());
	driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/div[2]/div[8]/div/div[6]/a")).click();//view more details
	Set<String>allwindowhandles=driver.getWindowHandles();
	for(String handle:allwindowhandles)
	{
		System.out.println(handle);
		if(!handle.equalsIgnoreCase(parentWindow))
		{
			driver.switchTo().window(handle);
		System.out.println("Child Window Title"+driver.getTitle());
		driver.findElement(By.xpath("//*[@id=\"AddCartForm\"]/div[2]/div[3]/div/div/div[3]/div[1]/img")).click();//add to cart
		System.out.println("Added to cart");
		driver.close();
	    }
	}
	driver.switchTo().window(parentWindow);
	
}

@Test(priority=4)
public void top100books() throws InterruptedException//Dcbooks top 100 search
{
	
	driver.findElement(By.xpath("//*[@id=\"navbar-collapse-2\"]/ul/li[4]/a")).click();//Dc books top 100

	
		Thread.sleep(3000);
		JavascriptExecutor js=(JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,700)", "");
	driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/div[2]/div[126]/div/div[6]/a")).click();//view more details
	driver.findElement(By.xpath("//*[@id=\"AddCartForm\"]/div[2]/div[3]/div/div/div[3]/div[1]/img")).click();
		System.out.println("Added to cart");		
	
}
@Test(priority=6)
public void showcart()
{
	driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[3]/div/div/div[3]/a/img")).click();
	System.out.println("Elements in cart");
	driver.findElement(By.xpath("//*[@id=\"subbutnId\"]")).click();
}
@Test(priority=7)
public void screenshoteachelement() throws IOException
{
	//First Create a folder in our project.Right click on Selenium project->create folder
	//Here folder name is ScreenShot Screnelemet is the name of picture
	WebElement dayelement=driver.findElement((By.xpath("/html/body/div[2]/div[1]/div[2]/div/div/div[2]/div/div[2]/div/button")));
	//xpath of particular field
	File src1=dayelement.getScreenshotAs(OutputType.FILE);
	FileHandler.copy(src1, new File("./ScreenShot//screnelemt.png"));//saved to current project
}
@Test(priority=8)
public void screenshotwholepage() throws IOException
{
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	//FileHandler.copy(src, new File("C://Screenshot.png"));
	FileHandler.copy(src, new File("./ScreenShot//Screenshot1.png"));
	
}
@Test(priority=9)
public void checkurl() throws Exception
{
	URL ob=new URL("https://dcbookstore.com/");
	HttpURLConnection con=(HttpURLConnection)ob.openConnection();
	con.connect();
	if(con.getResponseCode()==200)
		System.out.println("Valid Url");
	else
		System.out.println("Invalid Url");
	
	
}

@AfterTest
public void webbrowserclose()
{
	driver.close();
}
}

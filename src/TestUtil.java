

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestUtil {

	
	WebDriver driver;
	Logger log = Logger.getLogger(Feesbatch.class);

	@BeforeClass
	public void setUp() throws Exception {
	
		PropertyConfigurator.configure("log4j.properties");

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\aakis\\Downloads\\chromedriver_win32_new\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public static void login(WebDriver driver)  {
		 
		   
		    driver.get("https://myclasscampus.com/login");
		    driver.manage().window().maximize();
		   // System.out.println(driver.manage().window().getSize());
		    //Dimension d = new Dimension(698,8051);

		   // driver.manage().window().setSize(d);
		    //System.out.println("window resized to:"+ driver.manage().window().getSize());
		    driver.findElement(By.id("country_id")).click();
		    new Select(driver.findElement(By.id("country_id"))).selectByValue("13");
		    driver.findElement(By.id("mobile")).click();
		    driver.findElement(By.id("mobile")).clear();
		    driver.findElement(By.id("mobile")).sendKeys("2142648764");
		    driver.findElement(By.id("password")).click();
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("test");
		    driver.findElement(By.cssSelector("button.btn.green.uppercase.btn_login")).click();
		    //new Select(driver.findElement(By.id("institutes"))).selectByVisibleText("eagle mountain");
		    //driver.findElement(By.cssSelector("button.btn.green.uppercase.btn_login")).click();
		    //WebDriverWait wait = new WebDriverWait(driver, 10);
		    //Thread.sleep(1000);
		    //driver.findElement(By.name("selectDefault")).click();
		    
		    //driver.manage().window().maximize();
             System.out.println("login worked successfully");
	
	}
	
}

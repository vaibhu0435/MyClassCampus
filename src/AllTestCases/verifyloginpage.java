package AllTestCases;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import LoginPage.Login;
import util.TestUtil;

public class verifyloginpage {

	WebDriver driver;
	Properties config = new Properties();

	@BeforeClass
	public void setUp() throws Exception {
		PropertyConfigurator.configure("log4j.properties");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\aakis\\Downloads\\chromedriver_win32_new\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		config = TestUtil.readProperties();
	}

	@Test
	public void verifylogin(WebDriver driver) throws InterruptedException {
		driver.get("https://myclasscampus.com/login");
		Login login = new Login(driver);
		login.performLogin(config);

}
}

package AllTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.FeeCycleLabels;
import LoginPage.Login;
import util.TestUtil;

public class AddFeeCycleLabels {

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
		Login login = new Login(driver);
		login.performLogin(config);

	}

	@Test(priority = 1)
	public void veriAddfyFeeCycleLabel() throws InterruptedException {
		String FeeUrl = "https://myclasscampus.com/d/feemanager";
		String FeecycleLAbelsURL = "https://myclasscampus.com/feemanager/fee-cycle-labels";

		driver.get(FeeUrl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(FeecycleLAbelsURL);
		FeeCycleLabels fee = new FeeCycleLabels(driver);
		fee.clickCreateButton();
		fee.addlablename("sportsFee");
		fee.clickRedbutton();
		fee.verifyFeeCycleLable("sportsFee");
	}

	@Test(priority = 2)
	public void verifyEditFeeCycleLabel() throws InterruptedException {
		String FeeUrl = "https://myclasscampus.com/d/feemanager";
		String FeecycleLAbelsURL = "https://myclasscampus.com/feemanager/fee-cycle-labels";
		driver.get(FeeUrl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(FeecycleLAbelsURL);
		FeeCycleLabels fee = new FeeCycleLabels(driver);
		fee.clickEditButton();
		fee.addlablename("WinterFee");
		fee.clickRedbutton();
		fee.verifyeditedLabelrName("WinterFee");
	}
}

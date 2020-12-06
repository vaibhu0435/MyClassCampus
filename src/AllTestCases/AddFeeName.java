package AllTestCases;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.FeeNames;
import LoginPage.Login;
import util.TestUtil;

public class AddFeeName {

	WebDriver driver;
	Properties config = new Properties();

	@BeforeClass
	public void setUp() throws Exception {

		PropertyConfigurator.configure("log4j.properties");

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\aakis\\Downloads\\chromedriver_win32_new\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Login login=new Login(driver);
		config = TestUtil.readProperties();
		login.performLogin(config);
	}



	@Test(priority=1)
	public void verifyAddFeeNames()throws InterruptedException {
 
		String FeeUrl ="https://myclasscampus.com/d/feemanager";
		String AddFeeNameURL="https://myclasscampus.com/feemanager/batch/fee-names";
		driver.get(FeeUrl);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
        driver.get(AddFeeNameURL);
		FeeNames fee =new FeeNames(driver);
		fee.clickCreateButton();
		fee.selectCategory();
		fee.addFeeName("feecheck");
		fee.Submit();
		fee.verifyaddedFeeName("feecheck");
		
	}
	@Test(priority = 2)
	public void VerifyeditFeeName() throws InterruptedException {
		String FeeUrl ="https://myclasscampus.com/d/feemanager";
		String AddFeeNameURL="https://myclasscampus.com/feemanager/batch/fee-names";
		driver.get(FeeUrl);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
        driver.get(AddFeeNameURL);
		FeeNames fee =new FeeNames(driver);
		fee.ClickEditbutton();
		fee.selectCategory();
		Thread.sleep(3000);
		fee.addFeeName("final name");
		fee.Submit();
		Thread.sleep(2000);
		driver.get(AddFeeNameURL);
		fee.verifyeditedFeeName("final name");
	}
	@Test(priority = 3)
	public void reomoveFeename() throws InterruptedException {
		String FeeUrl = "https://myclasscampus.com/d/feemanager";
		String AddFeeNameURL = "https://myclasscampus.com/feemanager/batch/fee-names";
		driver.get(FeeUrl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(AddFeeNameURL);
		FeeNames fee = new FeeNames(driver);
		driver.get(AddFeeNameURL);
		String beforeRmoveFeename = fee.GetFeeName();
		fee.RemoveFeeName();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//driver.get(AddFeeNameURL);
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String afterRmoveFeename = fee.GetFeeName();
		if (beforeRmoveFeename.equals(afterRmoveFeename)) {
			System.out.println("Feename not removed something is wrong");
		} else {
			System.out.println("Fee name successfully removed");
		}
	}
}
package AllTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.ChequeHistory;
import LoginPage.Login;
import util.TestUtil;

public class ChequeHistoryTest {
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

//	@Test(priority = 1)
//	public void BulkChequeStatusUpdate() throws InterruptedException {
//		driver.get("https://myclasscampus.com/d/feemanager");
//		driver.get("https://myclasscampus.com/feemanager/chequehistory");
//		ChequeHistory fee = new ChequeHistory(driver);
//		Thread.sleep(2000);
//		fee.clickBulkEditButton();
//		Thread.sleep(3000);
//		fee.changeStatus();
//		Thread.sleep(2000);
//		fee.sendSMS();
//		Thread.sleep(2000);
//		fee.Submit();
//		fee.verifyBulkStatusUpdate();
//
//	}

	
	public void depositeCheque(int student) throws InterruptedException {

		driver.get("https://myclasscampus.com/d/feemanager");
		driver.get("https://myclasscampus.com/feemanager/chequehistory");
		Thread.sleep(2000);
		ChequeHistory fee = new ChequeHistory(driver);
		fee.clickonChequeLink(student);
		Thread.sleep(2000);
		fee.clickonDepositeBtn();
		Thread.sleep(2000);
		fee.addNote();
		Thread.sleep(2000);
		fee.clickConfirmButton();
		Thread.sleep(2000);
		fee.verifyStatusAction();
	}

//	@Test(priority = 2)
//	public void verifydepositeCheque() throws InterruptedException {
//		depositeCheque(1);
//	}
//	
//	@Test(priority = 3)
//	public void verifyCancelBouncecheque() throws InterruptedException {
//		driver.get("https://myclasscampus.com/d/feemanager");
//		driver.get("https://myclasscampus.com/feemanager/chequehistory");
//		Thread.sleep(2000);
//		ChequeHistory fee = new ChequeHistory(driver);
//		fee.clickonChequeLink(1);
//		Thread.sleep(2000);
//		fee.clickOnChequeBounceButton();
//		fee.SubmitBounce();
//		fee.verifyStatusAction();
//	}

//	@Test(priority = 4)
//	public void verifyCancelCheque() throws InterruptedException {
//		driver.get("https://myclasscampus.com/d/feemanager");
//		driver.get("https://myclasscampus.com/feemanager/chequehistory");
//		Thread.sleep(2000);
//		ChequeHistory fee = new ChequeHistory(driver);
//		fee.clickonChequeLink(3);
//		Thread.sleep(2000);
//		fee.clickOnChequeCancelButton();
//		fee.SubmitCancel();
//		fee.verifyStatusAction();
//	}

//	@Test(priority = 5)
//	public void AddnewCheque() throws InterruptedException {
//
//		driver.get("https://myclasscampus.com/d/feemanager");
//		driver.get("https://myclasscampus.com/feemanager/chequehistory");
//		Thread.sleep(2000);
//		ChequeHistory fee = new ChequeHistory(driver);
//		fee.clickonChequeLink(1);
//		Thread.sleep(3000);
//		fee.clickAddnewChequeBtn();
//		Thread.sleep(1000);
//		fee.addChequeNo();
//		Thread.sleep(1000);
//		fee.addBankName();
//		Thread.sleep(1000);
//		fee.addBranchName();
//		Thread.sleep(1000);
//		fee.newChequeSubmit();
//		fee.verifyAddChequeAction();
//
//	}

	@Test(priority = 6)
	public void clearcheque() throws InterruptedException {
		//depositeCheque(4);
		driver.get("https://myclasscampus.com/d/feemanager");
		driver.get("https://myclasscampus.com/feemanager/chequehistory");
		Thread.sleep(2000);
		ChequeHistory fee = new ChequeHistory(driver);
		Thread.sleep(2000);
		fee.clickonChequeLink(4);
		Thread.sleep(2000);
		fee.confirmclearcheque();
		Thread.sleep(2000);
		fee.verifyClearCheque();

	}

}

package AllTestCases;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.Wallet;
import LoginPage.Login;
import util.TestUtil;

public class VerifyWallet {

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

	public void LoadstudentWalletpage() throws InterruptedException {
		String FeeUrl = "https://myclasscampus.com/d/feemanager";
		driver.get(FeeUrl);
		String WalletURL = "https://myclasscampus.com/wallet";
		driver.get(WalletURL);
		Wallet wallet = new Wallet(driver);
		wallet.selectDepartment("Main Department");
		wallet.SelectClassRoom("class A");
		wallet.getStudent();
		wallet.clickOnStudentName("jaimin shah");
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void veriAddwalletAmountByCash() throws InterruptedException {
		String originalWindowHandle = driver.getWindowHandle();
		LoadstudentWalletpage();
		Wallet wallet = new Wallet(driver);
		wallet.loadNewWindow();
		double walletamountBefore = wallet.GetCurrentTotalWalletAmount();
		wallet.clickAddButton();
		wallet.selectPaymenttype("Cash");
		wallet.addDescription("No description");
		wallet.addAmount("50");
		wallet.selectSMSOption();
		wallet.selectCollectionCenter("New collection Center");
		wallet.submitButton();
		Thread.sleep(5000);
		double walletamountAfter = wallet.GetCurrentTotalWalletAmount();
		if (walletamountAfter > walletamountBefore) {
			System.out.println("Sucessfully wallet amount Added");
		} else {
			System.out.println("Something is wrong");
		}
		driver.close();
		driver.switchTo().window(originalWindowHandle);

	}

	@Test(priority = 2)
	public void verifyDeductwalletAmountByCash() throws InterruptedException {
		String originalWindowHandle = driver.getWindowHandle();
		LoadstudentWalletpage();
		Wallet wallet = new Wallet(driver);
		wallet.loadNewWindow();
		double walletamountBefore = wallet.GetCurrentTotalWalletAmount();
		Thread.sleep(2000);
		wallet.clickDeductButton();
		Thread.sleep(2000);
		wallet.addDescription("No description");
		Thread.sleep(2000);
		wallet.addAmount("50");
		Thread.sleep(2000);
		wallet.selectSMSOption();
		Thread.sleep(2000);
		wallet.submitButton();
		Thread.sleep(5000);
		double walletamountAfter = wallet.GetCurrentTotalWalletAmount();
		if (walletamountAfter < walletamountBefore) {
			System.out.println("Sucessfully wallet amount Deducted");
		} else {
			System.out.println("Something is wrong");
		}
		driver.close();
		driver.switchTo().window(originalWindowHandle);
	}

	@Test(priority = 3)
	public void DeleteWalletReceipt() throws InterruptedException {
		String originalWindowHandle = driver.getWindowHandle();
		LoadstudentWalletpage();
		Wallet wallet = new Wallet(driver);
		wallet.loadNewWindow();
		double walletamountBefore = wallet.GetCurrentTotalWalletAmount();
		wallet.clickDeleteButton();
		Thread.sleep(3000);
		wallet.confirmButton();
		Thread.sleep(2000);
		wallet.addReason();
		Thread.sleep(3000);
		double walletamountAfter = wallet.GetCurrentTotalWalletAmount();
		if (walletamountAfter > walletamountBefore) {
			System.out.println("Sucessfully Receipt deleted");
		} else {
			System.out.println("Something is wrong");

		}
		driver.close();
		driver.switchTo().window(originalWindowHandle);

	}

	private void switchToTab(int tabNumber, WebDriver driver) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		// System.out.println("switching tabs");
		driver.switchTo().window(tabs.get(tabNumber));
	}
}

package AllTestCases;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.Wallet;
import FeesPages.WalletDepositSummaryReport;
import LoginPage.Login;
import util.TestUtil;

public class VerifyWalletReports {
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

	public String GetReceiptNo() throws InterruptedException {

		String FeeUrl = "https://myclasscampus.com/d/feemanager";
		driver.get(FeeUrl);
		String WalletURL = "https://myclasscampus.com/wallet";
		driver.get(WalletURL);
		Wallet wallet = new Wallet(driver);
		Thread.sleep(2000);
		wallet.selectDepartment("Main Department");
		wallet.SelectClassRoom("class A");
		wallet.getStudent();
		wallet.clickOnStudentName("jaimin shah");
		Thread.sleep(2000);
		wallet.loadNewWindow();
		String ReceiptNo = wallet.getReceiptNo();
		return ReceiptNo;

	}

	public double GetTotalClosingBlance() throws InterruptedException {

		String FeeUrl = "https://myclasscampus.com/d/feemanager";
		driver.get(FeeUrl);
		String WalletURL = "https://myclasscampus.com/wallet";
		driver.get(WalletURL);
		Wallet wallet = new Wallet(driver);
		wallet.selectDepartment("Main Department");
		wallet.SelectClassRoom("class A");
		wallet.getStudent();
		Thread.sleep(2000);
		double Amount = wallet.getTotalAmount();
		return Amount;

	}

//	@Test(priority=1)
//	public void verifyWalletTransactionReport() throws InterruptedException {
//	
//		
//		String RecepiNoatpaymenttime=GetReceiptNo();
//		String ReportURL="https://myclasscampus.com/wallet/report";
//		driver.get(ReportURL);
//		WalletTransactionReport wallet = new WalletTransactionReport(driver);
//		//wallet.selectDateRange();
//		Thread.sleep(2000);
//		wallet.selectPaymentType();
//		Thread.sleep(1000);
//		wallet.selectCollectionCenter();
//		Thread.sleep(1000);
//		wallet.selectTansactionType();
//		Thread.sleep(1000);
//		wallet.selectDepartment();
//		Thread.sleep(1000);
//		wallet.selectClassRoom();
//		wallet.submit();
//		Thread.sleep(3000);
//		 List<String> ReciptNoOnReport=wallet.CheckReciptNo();
//		   for(String  name: ReciptNoOnReport) {
//			   if (name.equals(RecepiNoatpaymenttime)) {   
//				   System.out.println("Wallet Transaction report is Loaded Successfully and its Matches ReciptNo");
//			   }
//			   
//		   }
	// }

	@Test(priority = 2)
	public void verifyWalletDepositSummaryReport() throws InterruptedException {
		double TotalAmountonwalletpage = GetTotalClosingBlance();
		System.out.println(TotalAmountonwalletpage);
		String ReportURL = "https://myclasscampus.com/wallet/student_wallet_deposit_summary";
		driver.get(ReportURL);
		WalletDepositSummaryReport wallet = new WalletDepositSummaryReport(driver);
		Thread.sleep(1000);
		wallet.selectDepartment();
		Thread.sleep(1000);
		wallet.selectClass();
		Thread.sleep(1000);
		wallet.sumbit();
		Thread.sleep(1000);
		double totalAmountOnReportPage = wallet.GetTotal();
		System.out.println(totalAmountOnReportPage);
		if (TotalAmountonwalletpage == totalAmountOnReportPage) {

			System.out.println("Wallet Deposit Summury report is Loaded Successfully and its matches the TotalAmount");

		}
	}

}

package AllTestCases;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.FeeDueReport;
import FeesPages.FeePaymentReport;
import FeesPages.FeesMasterPaymentReports;
import LoginPage.Login;
import util.TestUtil;

public class VerifyFeesReports {
	WebDriver driver;
	Properties config = new Properties();
	Logger log = Logger.getLogger(VerifyFeesReports.class);

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
	public void verifyMasterpaymentReport() throws InterruptedException {
		FeesMasterPaymentReports report = new FeesMasterPaymentReports(driver);
		report.selectReporttype();
		report.Selectdaterange();
		report.selectTemplate();
		report.selectadditionalcoulumns();
		report.Getreport();
		double paymentamntonreport = report.GetPaybleAmountOnReport();
		double paymentbyuser = Double.valueOf(config.getProperty("Walletamount"));
		if (paymentamntonreport == paymentbyuser) {

			log.info("User Payment is matched with Master Payment Report ");
		}

		// report.ExportToExcel();

	}

	@Test(priority = 2)
	public void verifyPaymnetReport() {
		FeePaymentReport report = new FeePaymentReport(driver);
		report.selectclass(config.getProperty("Classno"));
		report.selectPaymentDateFrom();
		report.selectPaymentDateTo();
		report.GetReport();
		double PaymentByUser = Double.valueOf(config.getProperty("Onlineamount"));
		double PaidAmountOnReport = report.getpaymentamount();
		if (PaidAmountOnReport == PaymentByUser) {

			log.info("User Payment is matched with Payment Report ");

		}

	}

	public void verifydueReport() {
		FeeDueReport report = new FeeDueReport(driver);
		report.loadduereportUrl();
		report.selectclassroom(config.getProperty("Classno"));
		report.getReport();
		double PaymentByUser = Double.valueOf(config.getProperty("Walletamount"));

	}

}

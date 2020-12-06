package AllTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.FeePaymentByCheque;
import FeesPages.FeePaymentByOnline;
import FeesPages.FeePaymentByWallet;
import FeesPages.FeesPaymentByCash;
import FeesPages.StudentPage;
import LoginPage.Login;
import util.TestUtil;

public class StudentFeePayment {

	WebDriver driver;
	Properties config = new Properties();
	Logger log = Logger.getLogger(StudentFeePayment.class);
	String FeesmanagerURL = "https://myclasscampus.com/d/feemanager";

	String StuentpageURL = "https://myclasscampus.com/feemanager/student";

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

	public void loadstudentpage() throws InterruptedException {
		driver.get(StuentpageURL);
		int roomno = Integer.parseInt(config.getProperty("Classno"));
		StudentPage stu = new StudentPage(driver);
		stu.selectdeprtment(config.getProperty("Department"));
		stu.selectclassroom(roomno);
		stu.submitbutton();
	}

	@Test(priority = 1)
	public void VerifyFeeCashPayment() throws InterruptedException {
		log.info("********Verify Fee Payment by cash  started ******");
		String originalWindowHandle = driver.getWindowHandle();
		int student = 7;
		driver.get(FeesmanagerURL);
		loadstudentpage();
		FeesPaymentByCash fees = new FeesPaymentByCash(driver);
		StudentPage stu = new StudentPage(driver);
		double amountbefore = stu.GetCurrentdueOfStudent(student);
		String studentName = stu.studentname(student);
		log.info("Currentdueamount of " + studentName + " is:" + amountbefore);
		if (amountbefore > 0) {
			stu.Clickstudent(student);
			fees.FeepaymentOFstudentBycash(config.getProperty("CashAmount"), student);
			log.info("fees By cash paid successfully of " + studentName);
		} else {
			log.info("fees is fully paid of " + studentName);
		}
		driver.switchTo().window(originalWindowHandle);

		double paidamount = Double.valueOf(config.getProperty("CashAmount"));
		loadstudentpage();
		amountbefore = amountbefore - paidamount;
		double amountafter = stu.GetCurrentdueOfStudent(student);
		if (amountbefore == amountafter) {
			log.info(" due amount after fee payment:" + amountafter);

			log.info("paid amount is deducted from currentdue");

		}
	}

	@Test(priority = 2)
	public void VerifyFeeChequePayment() throws InterruptedException {
		log.info("********Verify Fee Payment by cheque  started ******");
		String originalWindowHandle = driver.getWindowHandle();

		int student = 8;
		driver.get(FeesmanagerURL);
		loadstudentpage();
		FeePaymentByCheque fees = new FeePaymentByCheque(driver);
		StudentPage stu = new StudentPage(driver);
		double amountbefore = stu.GetCurrentdueOfStudent(student);
		double checkamountbefore = stu.getCheckamount(student);
		String studentName = stu.studentname(student);
		log.info("Currentdueamount of " + studentName + " is:" + amountbefore);
		if (amountbefore > 0) {
			stu.Clickstudent(student);
			fees.FeepaymentOFstudentByCheque(config.getProperty("ChequeAmount"), student);
			log.info("fees By Cheque paid successfully of " + studentName);
		} else {
			log.info("fees is fully paid of " + studentName);
		}

		driver.switchTo().window(originalWindowHandle);

		double paidamount = Double.valueOf(config.getProperty("ChequeAmount"));
		loadstudentpage();
		double checkamountafter = stu.getCheckamount(student);

		log.info("check amount before payment is " + checkamountbefore);

		log.info("paid amount is deducted from currentdue and checkamount after payment is " + checkamountafter);

	}

	@Test(priority = 3)
	public void VerifyFeeOnlinePayment() throws InterruptedException {
		log.info("********Verify Fee Payment by online  started ******");
		String originalWindowHandle = driver.getWindowHandle();

		int student = 9;
		driver.get(FeesmanagerURL);
		loadstudentpage();
		FeePaymentByOnline fees = new FeePaymentByOnline(driver);
		StudentPage stu = new StudentPage(driver);
		double amountbefore = stu.GetCurrentdueOfStudent(student);
		String studentName = stu.studentname(student);
		log.info("Currentdueamount of " + studentName + " is:" + amountbefore);
		if (amountbefore > 0) {
			stu.Clickstudent(student);
			fees.FeepaymentofstudentByonline(config.getProperty("OnlineAmount"), student);
			log.info("fees By online paid successfully of " + studentName);
		} else {
			log.info("fees is fully paid of " + studentName);
		}
		driver.switchTo().window(originalWindowHandle);

		double paidamount = Double.valueOf(config.getProperty("OnlineAmount"));
		loadstudentpage();
		amountbefore = amountbefore - paidamount;
		double amountafter = stu.GetCurrentdueOfStudent(student);
		if (amountbefore == amountafter) {
			log.info(" due amount after fee payment:" + amountafter);

			log.info("paid amount is deducted from currentdue");

		}
	}

	@Test(priority = 4)
	public void VerifyFeePaymentbywallet() throws InterruptedException {
		log.info("********Verify Fee Payment by wallet  started ******");
		String originalWindowHandle = driver.getWindowHandle();
		int student = 10;
		driver.get(FeesmanagerURL);
		loadstudentpage();
		FeePaymentByWallet fees = new FeePaymentByWallet(driver);
		StudentPage stu = new StudentPage(driver);
		double amountbefore = stu.GetCurrentdueOfStudent(student);
		String studentName = stu.studentname(student);
		log.info("Currentdueamount of " + studentName + " is:" + amountbefore);
		if (amountbefore > 0) {
			stu.Clickstudent(student);
			fees.FeepaymentofstudentByWallet(config.getProperty("WalletAmount"), student);
			log.info("fees By wallet paid successfully of " + studentName);
		} else {
			log.info("fees is fully paid of " + studentName);
		}
		driver.switchTo().window(originalWindowHandle);
		double paidamount = Double.valueOf(config.getProperty("WalletAmount"));
		loadstudentpage();
		amountbefore = amountbefore - paidamount;
		double amountafter = stu.GetCurrentdueOfStudent(student);
		if (amountbefore == amountafter) {
			log.info(" due amount after fee payment:" + amountafter);

			log.info("paid amount is deducted from currentdue");

		}

	}

	@AfterClass
	public void cleanUp() {

		driver.close();

	}

	private void switchToTab(int tabNumber, WebDriver driver) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("switching tabs");
		driver.switchTo().window(tabs.get(tabNumber));
	}
}

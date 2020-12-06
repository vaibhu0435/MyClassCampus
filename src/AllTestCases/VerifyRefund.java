package AllTestCases;

import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.RefundFee;
import FeesPages.StudentPage;
import LoginPage.Login;
import util.TestUtil;

public class VerifyRefund {

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

	public void loadStudentRefundpage() throws InterruptedException {

		String FeesmanagerURL = "https://myclasscampus.com/d/feemanager";
		driver.get(FeesmanagerURL);
		int student = 3;
		String StuentpageURL = "https://myclasscampus.com/feemanager/student";
		driver.get(StuentpageURL);
		int roomno = Integer.parseInt(config.getProperty("Classno"));
		StudentPage stu = new StudentPage(driver);
		stu.selectdeprtment(config.getProperty("Department"));
		stu.selectclassroom(roomno);
		stu.submitbutton();
		stu.Clickstudent(student);

	}

	@Test(priority = 1)
	public void verifyAddRefundFee() throws InterruptedException {
		String originalWindowHandle = driver.getWindowHandle();
		loadStudentRefundpage();
		RefundFee fee = new RefundFee(driver);
		fee.loadNewWindow(2);
		Thread.sleep(1000);
		fee.loadRefundPage();
		fee.loadNewWindow(3);
		Thread.sleep(1000);
		fee.selectPaymentMode();
		// fee.selectRefundDate();
		fee.addAmount();
		fee.selectCollectionCenter();
		fee.clickRefundbutton();
		Thread.sleep(2000);
		String ReceiptNoatPaymenttime = fee.getFeerecieptNo();
		fee.clickconfirmButton();
		Thread.sleep(2000);
		fee.verifyRefund(ReceiptNoatPaymenttime);
		driver.close();
		driver.close();

	}

	@Test(priority = 2)
	public void verifyViewReceipt() throws InterruptedException {
		String originalWindowHandle = driver.getWindowHandle();
		loadStudentRefundpage();
		RefundFee fee = new RefundFee(driver);
		fee.loadNewWindow(2);
		Thread.sleep(1000);
		fee.loadRefundPage();
		fee.loadNewWindow(3);
		Thread.sleep(1000);
		fee.ViewFeeReciept();
		driver.close();
		driver.close();

	}

	@Test(priority = 3)
	public void verifyEditRefundFee() throws InterruptedException {
		String originalWindowHandle = driver.getWindowHandle();
		loadStudentRefundpage();
		RefundFee fee = new RefundFee(driver);
		fee.loadNewWindow(2);
		Thread.sleep(1000);
		fee.loadRefundPage();
		fee.loadNewWindow(3);
		Thread.sleep(1000);
		fee.clickEditButton();
		Thread.sleep(1000);
		fee.editAmount();
		fee.addpaymentNote();
		fee.addReason();
		fee.confirmEdit();
		Thread.sleep(2000);
		fee.verifyedit();
		driver.close();
		driver.close();

	}

}

package AllTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.FeePaymentHistory;
import FeesPages.StudentPage;
import FeesPages.Wallet;
import LoginPage.Login;
import util.TestUtil;

public class FeePaymentHistoryTest {

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

	public void LoadstudentProfiletpage() throws InterruptedException {
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
	public void verifyViewRecieptPage() throws InterruptedException {
		LoadstudentProfiletpage();
		String originalWindowHandle = driver.getWindowHandle();
		FeePaymentHistory fee = new FeePaymentHistory(driver);
		fee.loadNewWindow();
		Thread.sleep(1000);
		String ReceiptNoOnTable = fee.GetRecieptNo();
		System.out.println(ReceiptNoOnTable);
		fee.viewReciept();
		Thread.sleep(1000);
		String RecieptNoOnViewPage = fee.verifyViewReciept();
		System.out.println(RecieptNoOnViewPage);
		if (ReceiptNoOnTable.equals(RecieptNoOnViewPage)) {
			System.out.println("Reciept view page is working Successfully");
		} else {
			System.out.println("something is wrong");
		}
		driver.close();
		driver.switchTo().window(originalWindowHandle);

	}

	@Test(priority = 2)
	public void verifyRecieptEdit() throws InterruptedException {
		LoadstudentProfiletpage();
		String originalWindowHandle = driver.getWindowHandle();
		FeePaymentHistory fee = new FeePaymentHistory(driver);
		fee.loadNewWindow();
		Thread.sleep(1000);
		String amountbeforeEdit = fee.getAmount();
		System.out.println(amountbeforeEdit);
		fee.clickEditButton();
		Thread.sleep(1000);
		fee.chnagefineAmount();
		fee.changeAmount();
		fee.addNote();
		fee.addReason();
		fee.clickEditFeeButton();
		Thread.sleep(1000);
		String amountAfterEdit = fee.getAmount();
		System.out.println(amountAfterEdit);
		if (amountbeforeEdit.equals(amountAfterEdit)) {
			System.out.println("something is wrong");
		} else {

			System.out.println("Reciept Edited Successfully");
		}
		driver.close();
		driver.switchTo().window(originalWindowHandle);
	}

	@Test(priority = 3)
	public void verifyRecieptDelete() throws InterruptedException {
		LoadstudentProfiletpage();
		String originalWindowHandle = driver.getWindowHandle();
		FeePaymentHistory fee = new FeePaymentHistory(driver);
		fee.loadNewWindow();
		Thread.sleep(1000);
		String DeletedReceiptNo = fee.GetRecieptNo();
		System.out.println(DeletedReceiptNo);
		Thread.sleep(1000);
		fee.clickDeleteButton();
		fee.clickConfirmButton();
		fee.addDeleteReason();
		Thread.sleep(3000);
		String ReciptNoOnTable = fee.GetRecieptNo();
		System.out.println(ReciptNoOnTable);
		if (DeletedReceiptNo.equals(ReciptNoOnTable)) {
			System.out.println("something is wrong");
		} else {

			System.out.println("Reciept Deleted Successfully");
		}
		driver.close();
		driver.switchTo().window(originalWindowHandle);
	}

	@Test(priority = 4)
	public void verifyReceiptCancel() throws InterruptedException {
		LoadstudentProfiletpage();
		String originalWindowHandle = driver.getWindowHandle();
		FeePaymentHistory fee = new FeePaymentHistory(driver);
		fee.loadNewWindow();
		Thread.sleep(1000);
		fee.clickCancelbutton();
		Thread.sleep(1000);
		fee.confirmCancel();
		Thread.sleep(2000);
		fee.addCancelReason();
		Thread.sleep(1000);
		driver.close();
		driver.switchTo().window(originalWindowHandle);
	}

}

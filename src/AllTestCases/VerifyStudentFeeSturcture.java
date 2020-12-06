package AllTestCases;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.StudentPage;
import FeesPages.studentFeeStructure;
import LoginPage.Login;
import util.TestUtil;

public class VerifyStudentFeeSturcture {

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

	public void loadStudenFeeStucturepage() throws InterruptedException {
		String FeesmanagerURL = "https://myclasscampus.com/d/feemanager";
		driver.get(FeesmanagerURL);
		int student = 4;
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
	public void verifyAddNewFeeCycle() throws InterruptedException {
		String originalWindowHandle = driver.getWindowHandle();
		loadStudenFeeStucturepage();
		studentFeeStructure fee = new studentFeeStructure(driver);
		fee.loadNewWindow();
		fee.clickAddNewButton();
		fee.selectCategory();
		fee.selectFeeLabel();
		fee.selectDate();
		fee.addAmount();
		fee.clickCreateBtn();
		fee.VerifyAddcycle();
		driver.close();
		driver.switchTo().window(originalWindowHandle);
	}

	@Test(priority = 2)
	public void VerifyBulkDiscountFeenameWise() throws InterruptedException {
		String originalWindowHandle = driver.getWindowHandle();
		loadStudenFeeStucturepage();
		studentFeeStructure fee = new studentFeeStructure(driver);
		fee.loadNewWindow();
		String FeedueBeforeDicount = fee.getDue();
		fee.ClickBukdiscountFeenamewiseBtn();
		fee.selectCategoryforFeenamewiseDiscount();
		fee.addPercentage();
		fee.clickcheckboxWithTax();
		fee.Calculate();
		fee.ApplyDiscount();
		Thread.sleep(3000);
		String FeedueAfterDiscount = fee.getDue();
		if (FeedueBeforeDicount.equals(FeedueAfterDiscount)) {
			System.out.println("Something is wrong with Bulk Disount Feename Wise");
		} else {
			System.out.println("Bulk Discount Feename wise applied successfully");
		}
		driver.close();
		driver.switchTo().window(originalWindowHandle);
	}

	@Test(priority = 3)
	public void VerifyBulkDiscountCategorywise() throws InterruptedException {
		String originalWindowHandle = driver.getWindowHandle();
		loadStudenFeeStucturepage();
		studentFeeStructure fee = new studentFeeStructure(driver);
		fee.loadNewWindow();
		String FeedueBeforeDicount = fee.getDue();
		fee.clickBulkdiscountCategorywise();
		fee.selectCategoryforCategorywiseDiscount();
		fee.addcategorypercentage();
		fee.AddDiscountAmount();
		fee.selectDiscountType();
		fee.clickcheckbox();
		fee.ApplycategoryeiseDisocunt();
		Thread.sleep(1000);
		fee.confirmDisount();
		Thread.sleep(3000);
		String FeedueAfterDiscount = fee.getDue();
		if (FeedueBeforeDicount.equals(FeedueAfterDiscount)) {
			System.out.println("Something is wrong with Bulk Disount Category Wise");
		} else {
			System.out.println("Bulk Discount Category wise applied successfully");
		}
		driver.close();
		driver.switchTo().window(originalWindowHandle);

	}

}

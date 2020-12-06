package AllTestCases;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.Transportation;
import LoginPage.Login;
import util.TestUtil;

public class VerifyTransportation {
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

	public void loadTransportaionURL() {
		String FeeUrl = "https://myclasscampus.com/d/feemanager";
		String TransportationURL = "https://myclasscampus.com/feemanager/transportation";
		driver.get(FeeUrl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(TransportationURL);
	}

	@Test(priority = 1)
	public void EnableBatchesForTransportation() throws InterruptedException {
		loadTransportaionURL();
		Transportation fee = new Transportation(driver);
		fee.selectBatchToEnable(8);
		fee.saveBatches();
	}

	@Test(priority = 2)
	public void verifyaddWaypoint() throws InterruptedException {
		loadTransportaionURL();
		Transportation fee = new Transportation(driver);
		fee.clickAddwaypointButton();
		fee.addStructureName("paldi");
		fee.selectLabel();
		fee.addAmount();
		fee.selectLabel();
		fee.addNewRow();
		fee.clickRedbutton();
		fee.verifyWaypoint("paldi");

	}

	@Test(priority = 3)
	public void verifyEditWaypoint() throws InterruptedException {
		loadTransportaionURL();
		Transportation fee = new Transportation(driver);
		fee.ClickEditbutton();
		fee.addStructureName("sindhubhavan");
		fee.editLabel();
		fee.editAmount();
		fee.editLabel();
		fee.editAmount();
		fee.clickRedbutton();
		fee.verifyeditedEditedName("sindhubhavan");

	}

	@Test(priority = 4)
	public void verifyimportstudent() throws InterruptedException {
		loadTransportaionURL();
		Transportation fee = new Transportation(driver);
		int studentcountbefore = fee.getStudentCount();
		fee.clickImportStudentButton();
		fee.selectBatchdropdown();
		fee.selecBatch();
		fee.clickongetstudent();
		fee.clickoncheckbox();
		fee.clickRedbtntoSaveStudent();
		fee.clickconfirmbtntosubmit();
		int studentcountafter = fee.getStudentCount();
		if (studentcountafter > studentcountbefore) {
			System.out.println("Students imported successfully");
		} else {
			System.out.println("Students imported successfully Something is wrong");
		}

	}

	@Test(priority = 5)
	public void verifyRemovestudent() throws InterruptedException {
		loadTransportaionURL();
		Transportation fee = new Transportation(driver);
		int studentcountbefore = fee.getStudentCount();
		fee.Clickonwaypoint();
		fee.removeStudent();
		fee.confirmremovestudent();
		String TransportationURL = "https://myclasscampus.com/feemanager/transportation";
		driver.get(TransportationURL);
		int studentcountafter = fee.getStudentCount();
		if (studentcountafter < studentcountbefore) {
			System.out.println("Student removed successfully");
		} else {
			System.out.println("Student not removed Something is wrong");
		}
	}

	@Test(priority = 6)
	public void verifydisablestudent() throws InterruptedException {
		loadTransportaionURL();
		Transportation fee = new Transportation(driver);
		fee.Clickonwaypoint();
		fee.clickdiablebutton();
		fee.clickonconfirmbtn();
		fee.clickRedBtn();
		fee.verifydisablestudent();
	}

	@Test(priority = 7)
	public void verifyEnablestudent() throws InterruptedException {
		loadTransportaionURL();
		Transportation fee = new Transportation(driver);
		fee.Clickonwaypoint();
		fee.clickEnablebutton();
		Thread.sleep(2000);
		fee.clickRedBtn();
		Thread.sleep(1000);
		fee.verifyEnablestudent();
	}

	@Test(priority = 8)
	public void verifyChangeWayPoint() throws InterruptedException {
		loadTransportaionURL();
		Transportation fee = new Transportation(driver);
		fee.Clickonwaypoint();
		Thread.sleep(1000);
		fee.clickChangeWaypoint();
		Thread.sleep(1000);
		fee.clickblueBtnTochangeWaypoint();
		Thread.sleep(1000);
		fee.verifychangeWaypoint();
	}

}

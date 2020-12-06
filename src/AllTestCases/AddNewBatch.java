package AllTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.BatchSetup;
import LoginPage.Login;
import util.TestUtil;

public class AddNewBatch {

	WebDriver driver;
	Properties config = new Properties();

	@BeforeClass
	public void setUp() throws Exception {

		PropertyConfigurator.configure("log4j.properties");

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\aakis\\Downloads\\chromedriver_win32_new\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Login login = new Login(driver);
		login.performLogin(config);
		config = TestUtil.readProperties();
	}

	@Test
	public void verifynewBach() throws InterruptedException {
		BatchSetup batch = new BatchSetup(driver);
		batch.loadbatchsetuppage(config.getProperty("BatchID"));
		// Enter the months you want to select before active date and after active date
		int months = Integer.parseInt(config.getProperty("months"));
		batch.enterstartdateandenddate(months);
		// Select At Which Interval You Want To Take Fees / months,days
		batch.Entercyclemonth(config.getProperty("cyclemonths"), config.getProperty("cycledays"));
		// Date To Start Taking Fees
		batch.Selectdatetostartfee();
		// Select Fee Labels
		batch.SelectFeelabel(config.getProperty("Feelabel"));
		// Fee Structure
		int categories = Integer.parseInt(config.getProperty("Noofcategory"));
		// batch.SelectFeesCategoryOne(config.getProperty("categoryOne")
		// ,config.getProperty("FeenameOne") , config.getProperty("amount"));
		for (int i = 1; i <= categories; i++) {

			batch.SelectFeesCategory(config.getProperty("category" + i), config.getProperty("Feename" + i),
					config.getProperty("amount" + i), i);
			Thread.sleep(2000);
		}

		batch.clickaddnewbatch();
		// import student
		batch.Importstudent();
		driver.close();

	}

}

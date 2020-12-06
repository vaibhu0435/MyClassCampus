package AllTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.FeeCategory;
import LoginPage.Login;
import util.TestUtil;

public class AddNewFeeCategory {

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

	// Add Function
	@Test(priority = 1)
	public void verifyAddFeecategory() throws InterruptedException {
		FeeCategory fee = new FeeCategory(driver);
		String FeeUrl = "https://myclasscampus.com/d/feemanager";
		String AddCategoryURL = "https://myclasscampus.com/feemanager/categories";
		driver.get(FeeUrl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(AddCategoryURL);
		fee.clickaddnewbutton();
		fee.addCategoryName("check Final");
		fee.addInstituteName();
		fee.addInstititeAddress();
		fee.addReceieptheader();
		fee.selectSignature();
		fee.selectTaxtype();
		fee.addTaxPercentage();
		fee.selectGStcodetype();
		fee.addGSTCode();
		fee.addGSTcodedescription();
		fee.addFeename("Extra Activity");
		fee.clickaddnewRedbutton();
		Thread.sleep(2000);
		fee.verifyAddedcategoryName("check Final");
		Thread.sleep(2000);
	}

	// EditFunction
	@Test(priority = 2)
	public void verifyEditFeeCategory() throws InterruptedException {
		FeeCategory fee = new FeeCategory(driver);
		String FeeUrl = "https://myclasscampus.com/d/feemanager";
		String AddCategoryURL = "https://myclasscampus.com/feemanager/categories";
		driver.get(FeeUrl);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(AddCategoryURL);
		int lastRowcount = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody"))
				.findElements(By.tagName("tr")).size();
		fee.clickEditButton(lastRowcount);
		Thread.sleep(3000);
		fee.addCategoryName("check Final1");
		fee.addInstituteName();
		fee.addInstititeAddress();
		fee.addReceieptheader();
		fee.selectSignature();
		fee.selectTaxtype();
		fee.addTaxPercentage();
		fee.selectGStcodetype();
		fee.addGSTCode();
		fee.addGSTcodedescription();
		fee.addFeename("Sports1");
		fee.clickaddnewRedbutton();
		Thread.sleep(2000);
		fee.veryfyeditedcategoryName(lastRowcount, "check Final1");

	}

}

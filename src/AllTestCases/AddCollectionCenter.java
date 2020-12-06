package AllTestCases;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import FeesPages.CollectionCenter;
import LoginPage.Login;
import util.TestUtil;

public class AddCollectionCenter {
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
		Login login=new Login(driver);
		login.performLogin(config);
		
	}
	
	@Test(priority=1)
	public void verifyAddCollectionCenter()throws InterruptedException {
		
		String FeeUrl ="https://myclasscampus.com/d/feemanager";
		String CollectionCenterURL="https://myclasscampus.com/feemanager/collectioncenter";
		driver.get(FeeUrl);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
        driver.get(CollectionCenterURL);
        CollectionCenter fee =new CollectionCenter(driver);
        fee.clickAddNewButton();
        fee.addNameOfCenter("New Center4");
        fee.selectEmpolyee();
        fee.addRedButtonclick();
        fee.verifyCollectionCenter("New Center4");
	
	}
	
	@Test(priority=2)
	public void verifyEditcollectioncenter()throws InterruptedException {
		String FeeUrl ="https://myclasscampus.com/d/feemanager";
		String CollectionCenterURL="https://myclasscampus.com/feemanager/collectioncenter";
		driver.get(FeeUrl);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
        driver.get(CollectionCenterURL);
        CollectionCenter fee =new CollectionCenter(driver);
        fee.clickEditButton();
        fee.addNameOfCenter("New Center5");
        fee.EditemployeeName();
        fee.addRedButtonclick();
        fee.verifyeditedCenterName("New Center5");
        
	
	}
	@Test(priority=3)
	public void verifyRemoveCollectionCenter() throws InterruptedException {
		String FeeUrl ="https://myclasscampus.com/d/feemanager";
		String CollectionCenterURL="https://myclasscampus.com/feemanager/collectioncenter";
		driver.get(FeeUrl);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
        driver.get(CollectionCenterURL);
        CollectionCenter fee =new CollectionCenter(driver);
        String beforeRmoveCentername = fee.GetCenter();
		fee.RemoveCenterName();
		String afterRmoveCentername = fee.GetCenter();
		if (beforeRmoveCentername.equals(afterRmoveCentername)) {
			System.out.println("Collection Center not removed, something is wrong");
		} else {
			System.out.println("Collection Center successfully removed");
		}
	}
		
	}
	
	
	
	
	


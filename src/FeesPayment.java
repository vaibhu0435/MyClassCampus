

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FeesPayment {


	WebDriver driver;
	Logger log = Logger.getLogger(FeesPayment.class);

	@BeforeClass
	public void setUp() throws Exception {
	
		PropertyConfigurator.configure("log4j.properties");

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\aakis\\Downloads\\chromedriver_win32_new\\chromedriver.exe");
		
		driver = new ChromeDriver();
	
	}
	
	public  void payment() throws InterruptedException {
		
		TestUtil.login(driver);

    	driver.get("https://myclasscampus.com/d/feemanager");	
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
		
	   
	
	    for(int i=1; i<=2; i++){
	    	
	    	driver.get("https://myclasscampus.com/feemanager/student");
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
		   Thread.sleep(3000);

		    driver.findElement(By.id("department")).click();
		   
		    new Select(driver.findElement(By.id("department"))).selectByValue("9266");
		     Thread.sleep(3000);
		    driver.findElement(By.className("multiselect-selected-text")).click();
		    driver.findElement(By.xpath("/html/body/div[5]/div[4]/div/div/div/div/div[2]/form/span/div/ul/li[8]/a/label")).click();
		    driver.findElement(By.id("submitFilter")).click();
		    
	    	
	    	WebElement element = driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr["+i+"]/td[13]"));
	        double num = Double.valueOf(element.getText());
	        if(num > 0)
	        {
	        	 driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr["+i+"]/td[4]/a")).click();
	        	 waitForNumberOfWindowsToEqual(i+1, driver);
	        	 switchToTab(i, driver);
	        	 log.info("switched to second tab which has uRL= " +driver.getCurrentUrl());
	        	
	        	 driver.findElement(By.linkText("Pay Fees")).click();
	        	 
	        	 driver.findElement(By.id("amount_1")).click();
	        	    driver.findElement(By.id("amount_1")).clear();
	        	    driver.findElement(By.id("amount_1")).sendKeys("0050");
	        	    Thread.sleep(2000);
	        	    driver.findElement(By.cssSelector("button.btn.btn-lg.green-jungle.takeFeeBtn")).click();
	        	    Thread.sleep(2000);
	        	    driver.findElement(By.cssSelector("button.confirm.btn.btn-lg.btn-primary")).click();
	        	    Thread.sleep(3000);
	        	    driver.findElement(By.cssSelector("button.btn.btn-default")).click();
	        	    log.info("fees paid successfully of " +element);
	       }
	        else
	        {
	        	WebElement element1=driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr["+i+"]/td[4]/a"));
	            //System.out.printf("fees is fully paid of " , element1);
                log.info("due amount 0 and fees is fully paid of" +element1);
	        }
	        
	    }   
	}
	
	private void waitForNumberOfWindowsToEqual(final int numberOfWindows,WebDriver driver) {
	    new WebDriverWait(driver, 10) {
	    }.until(new ExpectedCondition<Boolean>() {
	        @Override
	        public Boolean apply(WebDriver driver) {                        
	            return (driver.getWindowHandles().size() == numberOfWindows);
	        }
	    });
	}
	
	private void waitForElementToBeClickable(WebDriver driver, By locator) {
		new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
	}
	
	private void switchToTab(int tabNumber,WebDriver driver) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabNumber));
	}

}
	


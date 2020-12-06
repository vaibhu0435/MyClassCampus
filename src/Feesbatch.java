

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Feesbatch {

	
	WebDriver driver;
	Logger log = Logger.getLogger(Feesbatch.class);

	@BeforeClass
	public void setUp() throws Exception {
	
		PropertyConfigurator.configure("log4j.properties");

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\aakis\\Downloads\\chromedriver_win32_new\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test(priority=0)
    public  void loadbatchsetuppage() throws  InterruptedException {
    // Label: Test
    // ERROR: Caught exception [ERROR: Unsupported command [resizeWindow | 1536,722 | ]]
		TestUtil.login(driver);
		 log.info("login sucessfully");
    	driver.get("https://myclasscampus.com/d/feemanager");	
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
        driver.get("https://myclasscampus.com/feemanager/batch/create/55015");
        
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("caption-subject")));
        assertEquals(driver.findElements(By.className("caption-subject")).size() ,2, "page caption is not present");

        //assertEquals(driver.findElement(By.className("caption-subject")).getText(),"SETUP BATCH : CLASS C","Incorrect title of the page");
        assertTrue(driver.findElement(By.className("caption-subject")).getText().contains("SETUP BATCH"),"Incorrect title of the page");
        
         log.info("batch setup page loaded successfully");
	}
  
	@Test(priority=1)
	 public  void enterstartdateandenddate() throws  InterruptedException {
      

	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
    Thread.sleep(3000);
    driver.findElement(By.name("start_date")).click();
    driver.findElement(By.xpath("/html/body/div[4]/div[1]/table/tbody/tr[1]/td[5]")).click();
    driver.findElement(By.name("end_date")).click();
    driver.findElement(By.cssSelector("th.next")).click();
    driver.findElement(By.cssSelector("th.next")).click();
    driver.findElement(By.xpath("//tr[3]/td[3]")).click();
    driver.findElement(By.name("cycle_months")).click();
    driver.findElement(By.name("cycle_months")).clear();
    driver.findElement(By.name("cycle_months")).sendKeys("3");
    driver.findElement(By.name("cycle_date")).click();
    driver.findElement(By.xpath("/html/body/div[4]/div[1]/table/tbody/tr[1]/td[5]")).click();
    Thread.sleep(3000);
   
//   WebDriverWait wait = new WebDriverWait(driver,5);
//  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tableOfLabelData\"]/table/thead/tr[1]/th")));
//   assertEquals(driver.findElements(By.xpath("//*[@id=\"tableOfLabelData\"]/table/thead/tr[1]/th")).size() ,1, "page caption is not present");
//  assertEquals(driver.findElement(By.xpath("//*[@id=\"tableOfLabelData\"]/table/thead/tr[1]/th")).getText(),"Select Fee Labels","date is not selected properly");
//    
        log.info("start date and End date selected");
        Thread.sleep(3000);

	 }
    
	@Test(priority=2)
	public  void SelectValidFeeStructure()throws  InterruptedException{

		driver.findElement(By.xpath("//*[@id=\"tableOfLabelData\"]/table/tbody/tr/td[2]/select")).click();
        new Select(driver.findElement(By.xpath("//*[@id=\"tableOfLabelData\"]/table/tbody/tr/td[2]/select"))).selectByValue("88112");
      //driver.findElement(By.name("feelabels[2020-09-01]")).click();
   
      
      driver.findElement(By.name("fc[]")).click();
    new Select(driver.findElement(By.name("fc[]"))).selectByVisibleText("student fee");
    driver.findElement(By.name("fc[]")).click();
    driver.findElement(By.name("feename[]")).click();
    new Select(driver.findElement(By.name("feename[]"))).selectByVisibleText("term fee");
    driver.findElement(By.name("feename[]")).click();
    driver.findElement(By.name("feeamount[]")).click();
    driver.findElement(By.name("feeamount[]")).clear();
    driver.findElement(By.name("feeamount[]")).sendKeys("500");
    driver.findElement(By.cssSelector("table.table.feestructure-table")).click();
    driver.findElement(By.cssSelector("#tr_2 > td > select[name=\"fc[]\"]")).click();
    new Select(driver.findElement(By.cssSelector("#tr_2 > td > select[name=\"fc[]\"]"))).selectByVisibleText("student fee");
    driver.findElement(By.cssSelector("#tr_2 > td > select[name=\"fc[]\"]")).click();
    driver.findElement(By.xpath("(//select[@name='feename[]'])[2]")).click();
    new Select(driver.findElement(By.xpath("(//select[@name='feename[]'])[2]"))).selectByVisibleText("tution fee");
    driver.findElement(By.xpath("(//select[@name='feename[]'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='feeamount[]'])[2]")).click();
    driver.findElement(By.xpath("(//input[@name='feeamount[]'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@name='feeamount[]'])[2]")).sendKeys("500");
    Thread.sleep(3000);

    driver.findElement(By.cssSelector("table.table.feestructure-table")).click();
    driver.findElement(By.xpath("//tr[@id='tr_3']/td[4]/button")).click();
    Thread.sleep(3000);

    log.info("valid fee structure selected");
  }
	@Test(priority=3)
    public  void batchsetupsuccessfull()throws  InterruptedException{
    	
     driver.findElement(By.cssSelector("button.btn.red-intense")).click();
    log.info("batchsetup successfully");
    Thread.sleep(3000);

	}
    @Test(priority=4)
    public  void importstudent() throws  InterruptedException{
    	Thread.sleep(2000);
    	driver.findElement(By.linkText("Import Students")).click();
        Thread.sleep(3000);

    driver.findElement(By.cssSelector("input.big-checkbox.ckall")).click();
    Thread.sleep(3000);

    driver.findElement(By.cssSelector("button.btn.blue.saveFormData")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("button.confirm.btn.btn-lg.btn-primary")).click();
    Thread.sleep(3000);

    
    log.info("student imported successfully");
    }

@Test(priority=5)
public  void payment() throws InterruptedException {
	
	//TestUtil.login(driver);

	driver.get("https://myclasscampus.com/d/feemanager");	
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
	
   

    for(int i=1; i<=4; i++){
    	
    	driver.get("https://myclasscampus.com/feemanager/student");
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
	   Thread.sleep(3000);

	    driver.findElement(By.id("department")).click();
	   
	    new Select(driver.findElement(By.id("department"))).selectByValue("9266");
	     Thread.sleep(3000);
	     WebDriverWait wait = new WebDriverWait(driver, 10); 
	     WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("multiselect-selected-text")));

	    driver.findElement(By.className("multiselect-selected-text")).click();
	    driver.findElement(By.xpath("//li[9]/a/label")).click();
	    driver.findElement(By.id("submitFilter")).click();
	    
    	
    	WebElement element1 = driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr["+i+"]/td[13]"));
    	element1.getText();
      double num = Double.valueOf(element1.getText());
        WebElement el= driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr["+i+"]/td[4]/a"));
        String str =el.getText();
        if(num > 0)
        {
        	 driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr["+i+"]/td[4]/a")).click();
        	 waitForNumberOfWindowsToEqual(i+1, driver);
        	 switchToTab(i, driver);
        	 //log.info("switched to second tab which has uRL= " +driver.getCurrentUrl());
        	
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
        	    
        	    log.info("fees paid successfully of " +str);
       }
        else
        {
        	            log.info("due amount 0 and fees is fully paid of "+str);
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
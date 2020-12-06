package FeesPages;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FeePaymentByOnline {
	
	
	WebDriver driver;
	Logger log = Logger.getLogger(FeePaymentByOnline.class);
	By PayMode=By.id("pay_mode");
	By RefNo =By.xpath("//*[@id=\"cheque_no\"]");
	By BranchName=By.cssSelector("span.select2-selection__placeholder");
	By IFSC=By.name("branch_name");
	By payfeesbtn = By.linkText("Pay Fees");
	By Feeamt1 = By.id("amount_1");
	By takefeebtn = By.cssSelector("button.btn.btn-lg.green-jungle.takeFeeBtn");
	By okbtn = By.cssSelector("button.confirm.btn.btn-lg.btn-primary");
	By closebtn = By.cssSelector("button.btn.btn-default");

	
	public FeePaymentByOnline(WebDriver driver) {

		this.driver = driver;
	}
	
	public void selectFeepaybutton() throws InterruptedException {
		driver.findElement(payfeesbtn).click();
		Thread.sleep(2000);
		log.info("payment page is loaded suceessfully");
		
	}
	
	public void selectPaymentMode() {
		
	    driver.findElement(PayMode).click();
	    new Select(driver.findElement(PayMode)).selectByVisibleText("Online");
	    driver.findElement(PayMode).click();
		
	}
	
	public void AddRefNo() throws InterruptedException {
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(RefNo));
		driver.findElement(RefNo).click();
	    driver.findElement(RefNo).clear();
	    driver.findElement(RefNo).sendKeys("7575755657");
	}
	
	public void SelectbranchName() {
		 
		driver.findElement(BranchName).click();
		   driver.findElement(By.xpath("//*[@id=\"bank_name\"]/option[2]")).click();
		
	    
	}
	
	public void AddbranchIFSC() {
		driver.findElement(IFSC).click();
	    driver.findElement(IFSC).clear();
	    driver.findElement(IFSC).sendKeys("SBIet");
	}
	
	public void Feepay(String amt) throws InterruptedException {
		
		
		driver.findElement(Feeamt1).clear();
		//System.out.println("cleared the amount and sending amount:" + amt);
		driver.findElement(Feeamt1).sendKeys(amt);
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(takefeebtn));
		driver.findElement(takefeebtn).click();
		Thread.sleep(2000);
		driver.findElement(okbtn).click();
		Thread.sleep(3000);
		driver.findElement(closebtn).click();
	
	}

	public void FeepaymentofstudentByonline(String amount , int student) throws InterruptedException {


		int wind = 1;
	    String originalWindowHandle = driver.getWindowHandle();
		System.out.println("waiting for number of windows to equal:"+ wind);
		waitForNumberOfWindowsToEqual(wind + 1, driver);
		switchToTab(wind, driver);
		//log.info("switched to second tab");
		selectFeepaybutton();
		selectPaymentMode();
		AddRefNo();
		SelectbranchName();
		AddbranchIFSC();
		Feepay(amount);
		driver.close();
	}
	
	
	  
		private void waitForNumberOfWindowsToEqual(final int numberOfWindows, WebDriver driver) {
			new WebDriverWait(driver, 10) {
			}.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return (driver.getWindowHandles().size() == numberOfWindows);
				}
			});
		}
	
		private void switchToTab(int tabNumber, WebDriver driver) {
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("switching tabs");
			driver.switchTo().window(tabs.get(tabNumber));
		}
	}


	





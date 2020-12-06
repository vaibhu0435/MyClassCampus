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

public class FeePaymentByCheque {

	
	WebDriver driver;
	Logger log = Logger.getLogger(FeesPaymentByCash.class);
	//By closebtn = By.cssSelector("button.btn.btn-default");
	By paymode =By.id("pay_mode");
	By account =By.id("chequeAccountSelectInput");
	By chequestatus =By.id("chequeStatusSelect");
	By Chequeno =By.id("cheque_no");
	By bankname=By.cssSelector("span.select2-selection__placeholder");
	By branchname=By.name("branch_name");
	By payfeesbtn = By.linkText("Pay Fees");
	By Feeamt1 = By.id("amount_1");
	By takefeebtn = By.cssSelector("button.btn.btn-lg.green-jungle.takeFeeBtn");
	By okbtn = By.cssSelector("button.confirm.btn.btn-lg.btn-primary");
	By closebtn = By.cssSelector("button.btn.btn-default");

	public FeePaymentByCheque(WebDriver driver) {

		this.driver = driver;
	}

	public void selectFeepaybutton() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(payfeesbtn).click();
		Thread.sleep(2000);
		log.info("payment page is loaded suceessfully");
		
	}
	
	public void selectpaymentmode() {
	
		driver.findElement(paymode).click();
	    new Select(driver.findElement(paymode)).selectByVisibleText("Cheque");
	    driver.findElement(paymode).click();
	
	}

	public void SelectAccount(){
		
		 driver.findElement(account).click();
		    new Select(driver.findElement(account)).selectByVisibleText("SBI account");
		    driver.findElement(account).click();
	}
	
//	public void Selectstatus() throws InterruptedException {
//		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(chequestatus));
//		driver.findElement(chequestatus).click();
//		 new Select(driver.findElement(chequestatus)).selectByVisibleText("Recived");
//	}
		
		
		public void Addchequenumner() {
			
			    driver.findElement(Chequeno).click();
			    driver.findElement(Chequeno).clear();
			    driver.findElement(Chequeno).sendKeys("D88558825");
		}
		
		public void selectBankAndBranchname() {
			
			   driver.findElement(bankname).click();
			   driver.findElement(By.xpath("//*[@id=\"bank_name\"]/option[2]")).click();
			    driver.findElement(branchname).click();
			    driver.findElement(branchname).clear();
			    driver.findElement(branchname).sendKeys("Sbi555");
		}
		
		public void Feepay(String amt) throws InterruptedException {
			
			//*[@id=\"chequeinfo\"]/div[4]/div/div/span/span[1]/span/span[2]
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
			
		
	}
	

	public void FeepaymentOFstudentByCheque(String amount, int student) throws InterruptedException {

		    int wind = 1;
		    String originalWindowHandle = driver.getWindowHandle();
			System.out.println("waiting for number of windows to equal:"+ wind);
			waitForNumberOfWindowsToEqual(wind + 1, driver);
			switchToTab(wind, driver);
			//log.info("switched to second tab");
			selectFeepaybutton();
			selectpaymentmode();
			SelectAccount();
			//Selectstatus();
			Addchequenumner();
			selectBankAndBranchname();
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

	
	
	
	
	


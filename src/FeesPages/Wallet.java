package FeesPages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wallet {
	
	
 WebDriver driver;
 
 By Departmentname = By.id("department_id");
 By ClassRoom = By.name("classroom_id");
 By GetstudentBtn = By.cssSelector("button.btn.blue.submitBtn > i.fa.fa-search");
 By AddBtn = By.cssSelector("button.btn.blue.addBtn");
 By description = By.name("description");
 By amount = By.name("amount");
 By collectionCenter = By.name("credit_collection_center");
 By submitBtn = By.cssSelector("button.btn.btn-primary.addSubmitBtn");
 By PaymentType = By.name("payment_type");
 By chequeNo = By.name("cheque_no");
 By BankName = By.name("bank_name");
 By BranchName = By.name("branch_name");
 By ChequeStatus = By.name("cheque_status");
 By DeductBtn =By.cssSelector("button.btn.red.deductBtn");
 	public Wallet(WebDriver driver) {
		
			this.driver = driver;
		
		}

 	public void selectDepartment(String Department) throws InterruptedException {
	 driver.findElement(Departmentname).click();
	    new Select(driver.findElement(Departmentname)).selectByVisibleText(Department);
	    driver.findElement(Departmentname).click();
	    Thread.sleep(3000);
 	}

 	public void SelectClassRoom(String Class) {
 		WebDriverWait wait = new WebDriverWait(driver,10);
 		wait.until(ExpectedConditions.visibilityOfElementLocated(ClassRoom));
 		driver.findElement(ClassRoom).click();
 		new Select(driver.findElement(ClassRoom)).selectByVisibleText(Class);
 		driver.findElement(ClassRoom).click();
		}
 	public void getStudent() throws InterruptedException {
	 driver.findElement(GetstudentBtn).click(); 
	 Thread.sleep(2000);
 	}

 	public void clickOnStudentName(String StudentName) throws InterruptedException {
 		driver.findElement(By.linkText(StudentName)).click();
 		Thread.sleep(2000);
 		
 	}
 	
 	public void loadNewWindow() {
 		waitForNumberOfWindowsToEqual(2, driver);
		switchToTab(1, driver);
 	}
 	public void clickAddButton() {
 		
		WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(AddBtn));
 		 driver.findElement(AddBtn).click();
 	}
 	
 	public void clickDeductButton() {
 		 driver.findElement(DeductBtn).click();
 	}
 	 
 	public void selectPaymenttype(String Type) {
 		WebDriverWait wait = new WebDriverWait(driver,10);
 		wait.until(ExpectedConditions.visibilityOfElementLocated(PaymentType));
 		driver.findElement(PaymentType).click();
 	   new Select(driver.findElement(PaymentType)).selectByVisibleText(Type);
 	  driver.findElement(PaymentType).click();
 	}
 	
 
 	public void addChequeNo() {
 		 driver.findElement(chequeNo).click();
 	    driver.findElement(chequeNo).clear();
 	    driver.findElement(chequeNo).sendKeys("545454545");
 	}
 	
 	public void addBankName() {
 		driver.findElement(BankName).click();
 	    driver.findElement(BankName).clear();
 	    driver.findElement(BankName).sendKeys("SBI");
 	}
 	
 	public void addBranchName() {
 		 driver.findElement(BranchName).click();
 	    driver.findElement(BranchName).clear();
 	    driver.findElement(BranchName).sendKeys("SBI paldi");
 	}
 	
 	public void selectChequeStatus() {
 		 driver.findElement(ChequeStatus).click();
 	    new Select(driver.findElement(ChequeStatus)).selectByVisibleText("Cleared");
 	    driver.findElement(ChequeStatus).click();
 	}
 	
 	public void addDescription(String Descrptn) {
 	 driver.findElement(description).click();
     driver.findElement(description).clear();
     driver.findElement(description).sendKeys(Descrptn);
 	}
 	public void addAmount(String amnt) {
 		driver.findElement(amount).click();
 	    driver.findElement(amount).clear();
 	    driver.findElement(amount).sendKeys(amnt);
 	}
 	public void selectSMSOption() {
 		 driver.findElement(By.name("send_sms[]")).click();
 	}
 	public void selectCollectionCenter(String Centername) {
 		driver.findElement(collectionCenter).click();
 	    new Select(driver.findElement(collectionCenter)).selectByVisibleText(Centername);
 	   driver.findElement(collectionCenter).click();
 	}
 	public void submitButton() {
 		WebDriverWait wait = new WebDriverWait(driver,10);
 		wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn));
 		 driver.findElement(submitBtn).click();
 	}
 	
	public double GetCurrentTotalWalletAmount() {
 		
 		WebElement Walletamount = driver.findElement(By.cssSelector("h4 > span.font-red"));
 		double amount = Double.valueOf(Walletamount.getText().substring(1));
 		System.out.println(amount);
 		return amount;
 	}
	
	
	
	
	public void clickDeleteButton() {
		driver.findElement(By.xpath("//tr[1]/td[7]/ul/li[2]/a/i")).click();
	}
	
	public void confirmButton() throws InterruptedException {
		driver.findElement(By.cssSelector("button.confirm.btn.btn-lg.btn-primary")).click();
		Thread.sleep(2000);
		 new Actions(driver).sendKeys(Keys.RETURN).perform();
	    driver.findElement(By.cssSelector("button.confirm.btn.btn-lg.btn-primary")).click();
		Thread.sleep(2000);
		 new Actions(driver).sendKeys(Keys.RETURN).perform();
	    driver.findElement(By.cssSelector("button.confirm.btn.btn-lg.btn-primary")).click();
		Thread.sleep(2000);

	}
	public void addReason() throws InterruptedException {
			driver.findElement(By.cssSelector("body > div.sweet-alert.showSweetAlert.visible.show-input > div.form-group > input")).click();
		
		 driver.findElement(By.cssSelector("body > div.sweet-alert.showSweetAlert.visible.show-input > div.form-group > input")).clear();
		    driver.findElement(By.cssSelector("body > div.sweet-alert.showSweetAlert.visible.show-input > div.form-group > input")).sendKeys("no Reason");   
		    driver.findElement(By.cssSelector("body > div.sweet-alert.showSweetAlert.visible.show-input > div.sa-button-container > div > button")).click();

		    
			 

		    
	}
	
	public String getReceiptNo() {
	    driver.findElement(By.linkText("Print")).click();
	    WebElement RcNo =	driver.findElement(By.xpath("//table[@id='printTable']/tbody/tr/td[2]"));
	    String ReceiptNo= RcNo.getText();
	    return ReceiptNo;
		
	  }
	
	public double getTotalAmount() throws InterruptedException {
		Thread.sleep(2000);
		 driver.findElement(By.name("DataTables_Table_0_length")).click();
		    new Select(driver.findElement(By.name("DataTables_Table_0_length"))).selectByVisibleText("All");
		    driver.findElement(By.name("DataTables_Table_0_length")).click();
		    Thread.sleep(2000);
		   WebElement total= driver.findElement(By.cssSelector("span.font-red.totalWalletAmount"));
			double totalAmount=valueof(total.getText());
		   return totalAmount;
	}
	
 	private double valueof(String text) {
		// TODO Auto-generated method stub
		return 0;
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
		//System.out.println("switching tabs");
		driver.switchTo().window(tabs.get(tabNumber));
	}
}

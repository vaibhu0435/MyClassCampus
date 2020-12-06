package FeesPages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ChequeHistory {
	
	WebDriver driver;
	By alert=By.cssSelector("p.alert.alert-info.alert-dismissable");
	
	public ChequeHistory(WebDriver driver) {
	
		this.driver=driver;
	}
	
	public void clickBulkEditButton() {
		driver.findElement(By.id("bulkEditBtn")).click();
	}
	
	public void changeStatus() {
		
		driver.findElement(By.cssSelector("#myTable > tr:nth-child(1) > td:nth-child(6) > select")).click();
	    new Select(driver.findElement(By.cssSelector("#myTable > tr:nth-child(1) > td:nth-child(6) > select"))).selectByVisibleText("Deposited");
	    driver.findElement(By.cssSelector("#myTable > tr:nth-child(1) > td:nth-child(6) > select")).click();
	    
	
	}
	
	public void sendSMS() {
		
		driver.findElement(By.name("sendSmsStudent")).click();
	}
	
	public void Submit() {
		driver.findElement(By.cssSelector("button.btn.btn-info")).click();
	}
	
	public String GetStatus() {
		WebElement St=driver.findElement(By.xpath("//table[@id='datatable']/tbody/tr[6]/td[8]"));
		String Status = St.getText();
		return Status;
	}
	
	public void clickonChequeLink(int student) {
		
		driver.findElement(By.xpath("//*[@id=\"datatable\"]/tbody/tr["+student+"]/td[4]/a")).click();	
	}
	
	public void clickonDepositeBtn() {
		driver.findElement(By.cssSelector("button.btn.blue.btn-block.bold")).click();
	}
	
	public void addNote() {
		   driver.findElement(By.cssSelector("#depositModal > div > div > form > div.modal-body > textarea")).click();
		    driver.findElement(By.cssSelector("#depositModal > div > div > form > div.modal-body > textarea")).clear();
		    driver.findElement(By.cssSelector("#depositModal > div > div > form > div.modal-body > textarea")).sendKeys("no reason");

	}
	
	public void clickConfirmButton() throws InterruptedException {
		
		   driver.findElement(By.cssSelector("#depositModal > div > div > form > div.modal-footer > button.btn.btn-primary")).click();
		
	}
	
	
	public void clickOnChequeBounceButton() {
		driver.findElement(By.cssSelector("button.btn.red.btn-block.bold")).click();
		
	}
	
	public void SubmitBounce() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("#bounceModal > div > div > form > div.modal-footer > button.btn.btn-primary")).click();
	}
	
	
	public void verifyStatusAction() {
		assertEquals(driver.findElement(alert).getText(),"×\n" + 
		 		"Status updated.","Somehting is Wrong");
	}
	
	public void verifyBulkStatusUpdate() {
		assertEquals(driver.findElement(alert).getText(),"×\n" + 
		 		"Successfully cheque bulk updated.","Something is Wrong");
	}
	
	public void clickOnChequeCancelButton() {
		driver.findElement(By.cssSelector("button.btn.red.btn-block.bold")).click();
	}
	public void SubmitCancel() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#cancelModal > div > div > form > div.modal-footer > button.btn.btn-primary")).click();
	}
	
	public void clickAddnewChequeBtn() {
		driver.findElement(By.cssSelector("body > div.page-container > div.page-content-wrapper > div > div.row > div > div > div.portlet-body > div.row > div:nth-child(2) > button")).click();
	}
	
	public void addChequeNo() {
		driver.findElement(By.name("cheque_no")).click();
	    driver.findElement(By.name("cheque_no")).clear();
	    driver.findElement(By.name("cheque_no")).sendKeys("123456");
	}
	
	public void addBankName() {
		driver.findElement(By.name("bank_name")).click();
	    driver.findElement(By.name("bank_name")).clear();
	    driver.findElement(By.name("bank_name")).sendKeys("SBI");
	}
	
	public void addBranchName() {
		driver.findElement(By.name("branch_name")).click();
	    driver.findElement(By.name("branch_name")).clear();
	    driver.findElement(By.name("branch_name")).sendKeys("DFGG");
	}
	public void newChequeSubmit() {
	    driver.findElement(By.cssSelector("#addNewChequeModal > div > div > form > div.modal-footer > button.btn.btn-primary")).click();
	}
	
	public void verifyAddChequeAction() {
		assertEquals(driver.findElement(alert).getText(),"×\n" + 
		 		"New cheque added.","Somehting is Wrong");
	}
	
	public void clickclearchequeBtn() {
		driver.findElement(By.cssSelector("body > div.page-container > div.page-content-wrapper > div > div.row > div > div > div.portlet-body > div.row > div:nth-child(1) > button")).click();
	}
	
	public void confirmclearcheque() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#chequeCleared > div.modal-footer > button.btn.btn-primary.thrice-confirm")).click();
	}
	
	public void verifyClearCheque() {
		
		assertEquals(driver.findElement(By.cssSelector("h4.modal-title")).getText() , "Payment Successfully Done." ,"Something is wrong");
	}
}

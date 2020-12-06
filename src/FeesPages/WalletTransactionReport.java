package FeesPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WalletTransactionReport {
	
	WebDriver driver;
	By ClassRoom=By.cssSelector("div.form-group.classroomGroup.col-md-2 > span.multiselect-native-select > div.btn-group > button.multiselect.dropdown-toggle.btn.btn-default > span.multiselect-selected-text");
    By Department=By.cssSelector("div.form-group.col-md-3 > span.multiselect-native-select > div.btn-group > button.multiselect.dropdown-toggle.btn.btn-default > span.multiselect-selected-text");
	By Transactiontype=By.id("transaction_type");
	By submitBtn=By.cssSelector("button.btn.blue.submitBtn");
	By collectionCenterDropdown=By.cssSelector("span.multiselect-selected-text");
	By PaymentType =By.id("payment_type");
    public WalletTransactionReport(WebDriver driver) {
		
		this.driver=driver;
	}
	
	public void selectDateRange() throws InterruptedException {
	    Thread.sleep(2000);
		 WebDriverWait wait = new WebDriverWait(driver,10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reportrange")));
		driver.findElement(By.id("reportrange")).click();
		 Thread.sleep(5000);
		 driver.findElement(By.xpath("//div[5]/div/ul/li[4]")).click();
	}
	
	public void selectPaymentType() {
		driver.findElement(PaymentType).click();
	    new Select(driver.findElement(PaymentType)).selectByVisibleText("All");
	    driver.findElement(PaymentType).click();
	}
	
	public void selectCollectionCenter() {
		driver.findElement(collectionCenterDropdown).click();
	    driver.findElement(By.xpath("//li[4]/a/label")).click();
	}
	
	public void selectTansactionType() {
		driver.findElement(Transactiontype).click();
	    new Select(driver.findElement(Transactiontype)).selectByVisibleText("All");
	}
	
	public void selectDepartment() {
		 driver.findElement(Department).click();
		    driver.findElement(By.xpath("//div[5]/span/div/ul/li[3]/a/label")).click();
	}
	public void selectClassRoom() {
		 WebDriverWait wait = new WebDriverWait(driver,10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(ClassRoom));
		driver.findElement(ClassRoom).click();
	    driver.findElement(By.xpath("//div[2]/div/span/div/ul/li[3]/a/label")).click();
	}
	public void submit() {
	    driver.findElement(submitBtn).click();

	}
	   
	   public List<String> CheckReciptNo() {
			List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
			List<String> RcNo = new ArrayList<>();
			for(WebElement  element: rows) {
				RcNo.add(element.findElement(By.xpath("td[3]/a")).getText());
			}
			return RcNo;
		}
		 
}

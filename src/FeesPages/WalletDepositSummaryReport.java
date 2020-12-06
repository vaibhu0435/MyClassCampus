package FeesPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WalletDepositSummaryReport {

	
	WebDriver driver;
	


	public WalletDepositSummaryReport(WebDriver driver) {
	  
		this.driver=driver;
		
	}
	
	public void selectDepartment() {
		driver.findElement(By.cssSelector("button.multiselect.dropdown-toggle.btn.btn-default > b.caret")).click();
	    driver.findElement(By.xpath("//form[@id='uploadForm']/div/div/div/span/div/ul/li[3]/a/label")).click();
	}
	
	public void selectClass() {
		driver.findElement(By.cssSelector("div.form-group.classroomGroup > span.multiselect-native-select > div.btn-group > button.multiselect.dropdown-toggle.btn.btn-default > span.multiselect-selected-text")).click();
	    driver.findElement(By.xpath("//form[@id='uploadForm']/div/div/div[2]/span/div/ul/li[3]/a/label")).click();
	}
	
	public void sumbit() {
		driver.findElement(By.id("fetchData")).click();
	}
	
	public double GetTotal() {
		WebElement total=driver.findElement(By.xpath("//*[@id=\"htmlContent\"]/div/table/tbody[2]/tr[3]/td[4]"));
		double TotalAmount=valueof(total.getText());
		return TotalAmount;
	}

	private double valueof(String text) {
		// TODO Auto-generated method stub
		return 0;
	}
}
package FeesPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DemandLetter {
	
	WebDriver driver;
	By submitButton =By.cssSelector("button.btn.btn-info");
	By dateTextbox =By.id("report_date");
	By Date =By.xpath("/html/body/div[5]/div[1]/table/tbody/tr[3]/td[3]");
	



	public DemandLetter(WebDriver driver) {
	
		this.driver=driver;
	}

	public void clickonDate() throws InterruptedException {
	
		 driver.findElement(dateTextbox).click();
		 Thread.sleep(2000);
	}
	
	public void selectDate() throws InterruptedException {
		
		driver.findElement(Date).click();
		Thread.sleep(2000);
	}
	
	public void submit() {
		driver.findElement(submitButton).click();
		
	}
}
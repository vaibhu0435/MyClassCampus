package FeesPages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewDemandLetter {

	WebDriver driver;
	
	public ViewDemandLetter(WebDriver driver) {
	
		this.driver=driver;
	
	}
	
	public void clickDemandletterBtn() {
		driver.findElement(By.linkText("View Demand Letter")).click();
	}
	
	public void selectDate() {
		driver.findElement(By.id("report_date")).click();
	    driver.findElement(By.id("report_date")).clear();
	    driver.findElement(By.id("report_date")).sendKeys("2020-11-09");
	}
	
	public void loadNewWindow(int wind) {
		waitForNumberOfWindowsToEqual(wind, driver);
		switchToTab(wind-1, driver);
	}
	
	public void submit() {
		driver.findElement(By.cssSelector("button.btn.btn-info")).click();
	}
   
	public String getCurrrentDueOnStudentListpage() {
		
		WebElement due = driver.findElement(By.xpath("//table[@id='dataTable']/tbody/tr[3]/td[13]"));
		String currentDue =due.getText().substring(0,5);
		return currentDue;
		
	}
	
	public String GetCurrentdueOnDemamndLetter() {
		WebElement due=driver.findElement(By.xpath("//tr[4]/td[6]"));
		String currentDue=due.getText();
		return currentDue;
		 
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
		// System.out.println("switching tabs");
		driver.switchTo().window(tabs.get(tabNumber));
	}

}

   
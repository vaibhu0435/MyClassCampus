package FeesPages;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FeesMasterPaymentReports {


	WebDriver driver;
   Logger log = Logger.getLogger(FeesPaymentByCash.class);
   String FeesmanagerURL ="https://myclasscampus.com/d/feemanager";
  String ReportUrl ="https://myclasscampus.com/feemanager/reports";
  By paymentReport =By.xpath("//a[5]/div/div/div");
  By Daterangeoption=By.name("date_range");
  By daterange= By.xpath("/html/body/div[4]/div[1]/ul/li[4]");
  By template = By.name("applyTemplate");
  By addcolmndropdwm=By.cssSelector("span.multiselect-selected-text");
  By checkBox=By.cssSelector("label.checkbox");
  By getreport =By.cssSelector("button.btn.btn-primary.searchData1");
  By Export=By.cssSelector("#exportToExcelForm > button.btn.btn-sm.btn-primary");
  By ExporttoExcel =By.cssSelector("div.modal-content > div.modal-footer > button.btn.btn-primary");
   
	public FeesMasterPaymentReports(WebDriver driver) {
		
		this.driver=driver;
	}
	
	public void selectReporttype() throws InterruptedException {
		driver.get(FeesmanagerURL);	
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
		driver.get(ReportUrl);
		driver.findElement(paymentReport).click();
		waitForNumberOfWindowsToEqual(2, driver);
		switchToTab(1, driver);
		log.info("Reporttypeselected");
    
	}
   public void Selectdaterange() throws InterruptedException {

	   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
	   WebDriverWait wait = new WebDriverWait(driver,10);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(Daterangeoption));
	   driver.findElement(Daterangeoption).click();
		Thread.sleep(3000);
	   driver.findElement(daterange).click();
	   log.info("date range selected");
   }
   public void selectTemplate() throws InterruptedException {
	   driver.findElement(template).click();
	   new Select(driver.findElement(template)).selectByVisibleText("Cash Collection");
	   driver.findElement(template).click();
		Thread.sleep(3000);
		log.info("template selected");

   }
   public void selectadditionalcoulumns() throws InterruptedException {
	   driver.findElement(addcolmndropdwm).click();
	   driver.findElement(checkBox).click();
		Thread.sleep(3000);
		log.info("Additional columns selected");

   }
   public void Getreport() throws InterruptedException {
	   driver.findElement(getreport).click();
		Thread.sleep(3000);

   }
   
  public double GetPaybleAmountOnReport() {
	  WebElement payment =  driver.findElement(By.xpath("//td[11]"));
	    double payamtonreport =Double.valueOf(payment.getText());
	    return payamtonreport;
  }
 
   public void ExportToExcel() throws InterruptedException {
	   driver.findElement(Export).click();
	   driver.findElement(ExporttoExcel).click();
		Thread.sleep(3000);
		log.info("Report Exported to Excel");
 
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
	
	private void switchToTab(int tabNumber,WebDriver driver) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabNumber));
	}
	
}

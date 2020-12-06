package FeesPages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FeePaymentReport {

	WebDriver driver;
	Logger log = Logger.getLogger(FeesPaymentByCash.class);
	String FeesmanagerURL ="https://myclasscampus.com/d/feemanager";
	By classroom_dropdown= By.cssSelector("span.multiselect-selected-text");
	By StartDate=By.id("startDate");
	By ActiveDateFrom=By.cssSelector("td.active.day");
	By EndDate=By.id("endDate");
	By ActiveDateTo=By.cssSelector("td.active.day");
	By Getreportbutton =By.name("button");
	String ReportURL="https://myclasscampus.com/feemanager/reports/payment";
	By amount =By.xpath("//table[@id='dataTable']/tbody/tr/td[12]");

  public FeePaymentReport(WebDriver driver) {
		
		this.driver=driver;
	
	}
  public void selectclass(String classno) {
	    driver.get(FeesmanagerURL);	
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
		driver.get(ReportURL);
	  driver.findElement(classroom_dropdown).click();
	  By Classroom=By.xpath("//form[@id='frm_fee_payment_report']/div/div/span/div/ul/li["+classno+"]/a/label");
	  driver.findElement(Classroom);
	  
	}
  
  public void selectPaymentDateFrom() {
	 
	  driver.findElement(StartDate).click();
	  driver.findElement(ActiveDateFrom).click();
  
}
  public void selectPaymentDateTo() {
	  
	  driver.findElement(EndDate).click();
	  driver.findElement(ActiveDateTo).click();
  }
  
  public void GetReport() {
	  
	  driver.findElement(Getreportbutton).click();
	  
	 
  }
  
  
  public double getpaymentamount() {
	  
	WebElement payment =  driver.findElement(amount);
	double paidamount = Double.valueOf(payment.getText());
	return paidamount;
  }

}

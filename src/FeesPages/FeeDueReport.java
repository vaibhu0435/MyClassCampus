package FeesPages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FeeDueReport {

	
	WebDriver driver;
	By Classroomdropdown=By.cssSelector("button.multiselect.dropdown-toggle.btn.btn-default");
	By date =By.name("date");
	By activedate=By.className("active day");
	By getreportbutton=By.cssSelector("button.btn.btn-primary");
	String FeesmanagerURL ="https://myclasscampus.com/d/feemanager";
    String ReportURL="https://myclasscampus.com/feemanager/reports/due";
   public FeeDueReport(WebDriver driver) {
		
		this.driver=driver;
	
	}
	public void loadduereportUrl()
	{
		driver.get(FeesmanagerURL);	
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
		driver.get(ReportURL);
	}
    public void selectclassroom(String classno) {
	     classno=classno+1;
        driver.findElement(Classroomdropdown).click();
        driver.findElement(By.xpath("//li["+classno+"]/a/label")).click();
    }  
    public void getReport()
    {
        driver.findElement(getreportbutton).click();
    }
}

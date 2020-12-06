package FeesPages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FeeCycleLabels {


		WebDriver driver;
		By Createbutton=By.linkText("Create");
		By Labelname =By.name("name");
		By Redbutton=By.cssSelector("button.btn.red-intense");
		
	public FeeCycleLabels(WebDriver driver) {
			
			this.driver = driver;
			
		}
		public List<String> getFeeNames() {
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"dataTable\"]/tbody/tr"));
		List<String> Labels = new ArrayList<>();
			for(WebElement  element: rows) {
			Labels.add(element.findElement(By.xpath("td[2]")).getText());
		}
		return Labels;
		}
	 
	public void verifyFeeCycleLable(String userLabelrname)
	{
	  List<String> LabelName= getFeeNames();
	   for(String  name: LabelName) {
		   if (name.equals(userLabelrname)) {
			   System.out.println(" Fee Label suceessfully added");
		   }
		   
	   }

	}
	public void clickCreateButton() throws InterruptedException {
		 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
	        WebDriverWait wait = new WebDriverWait(driver,10);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(Createbutton));
			Thread.sleep(5000);
		driver.findElement(Createbutton).click();
	}
	
	public void addlablename(String Label) {
		 
		driver.findElement(Labelname).click();
		    driver.findElement(Labelname).clear();
		    driver.findElement(Labelname).sendKeys(Label);
		    
	}
	
	public void clickRedbutton() {
		
		driver.findElement(Redbutton).click();
	}
	
	public void clickEditButton() throws InterruptedException{
		Thread.sleep(3000);
		 driver.findElement(By.cssSelector("i.fa.fa-edit")).click();
	}
	public void verifyeditedLabelrName(String Usereditedname) {
		  WebElement LabelName=driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr[1]/td[2]"));
		  String Editedname=LabelName.getText();
		  if(Editedname.equals(Usereditedname)) {
			  System.out.println("Label name suceessfully edited");
		  }
	}
	
	}


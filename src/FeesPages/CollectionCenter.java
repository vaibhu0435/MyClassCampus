package FeesPages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CollectionCenter {
	WebDriver driver;
	By addnewButton = By.linkText("Add New Center");
	By Centername =By.name("name");
	By EmployeeDropdown =By.cssSelector("span.multiselect-selected-text");
	By EmployeeName =By.xpath("//form[@id='validation']/div/div[2]/div/span/div/ul/li[3]/a/label");
	By Editemployeename =By.xpath("//form[@id='validation']/div/div[2]/div/span/div/ul/li[4]/a/label");
	By RedButton =By.cssSelector("button.btn.red-intense");
	 By ConfirmButton=By.cssSelector("button.confirm.btn.btn-lg.btn-primary");
public CollectionCenter(WebDriver driver) {
		
		this.driver = driver;
		
	}
	public List<String> getFeeNames() {
	List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
	List<String> centers = new ArrayList<>();
		for(WebElement  element: rows) {
		centers.add(element.findElement(By.xpath("td[2]")).getText());
	}
	return centers;
	}
 
public void verifyCollectionCenter(String userCentername)
{
  List<String> centerName= getFeeNames();
   for(String  name: centerName) {
	   if (name.equals(userCentername)) {
		   System.out.println("suceessfully added");
	   }
	   
   }

}

public void verifyeditedCenterName(String Usereditedname) {
	  WebElement CenteryName=driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[2]"));
	  String Editedname=CenteryName.getText();
	  if(Editedname.equals(Usereditedname)) {
		  System.out.println("Center name suceessfully edited");
	  }
}
	
 	public void clickAddNewButton() {
 		
 		driver.findElement(addnewButton).click();
 		
 	}
 	public void clickEditButton(){
		driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[4]/a[1]")).click();	
	}
 	public void addNameOfCenter(String name) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
 		WebDriverWait wait = new WebDriverWait(driver,10);
       wait.until(ExpectedConditions.visibilityOfElementLocated(Centername));
 		driver.findElement(Centername).click();
 	    driver.findElement(Centername).clear();
 	    driver.findElement(Centername).sendKeys(name);
 	}
 	public void selectEmpolyee() {
 	  
 		 driver.findElement(EmployeeDropdown).click();
 	    driver.findElement(EmployeeName).click();
 	}
 	public void addRedButtonclick() {
 	    driver.findElement(By.cssSelector("div.form-body")).click();
 	    driver.findElement(RedButton).click();
 	}
 
 	
 	public void EditemployeeName() {
 		
 		 driver.findElement(EmployeeDropdown).click();
  	    driver.findElement(Editemployeename).click();
  	
 	}
 	
 	
 	public void RemoveCenterName() throws InterruptedException {
 	   driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[4]/a[2]")).click();
 		WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmButton));
 	   driver.findElement(ConfirmButton).click();
 	  Thread.sleep(3000);
 	  new Actions(driver).sendKeys(Keys.RETURN).perform();
 	 driver.findElement(ConfirmButton).click();
 	   Thread.sleep(3000);

    }
   
    	public String GetCenter() throws InterruptedException {
 	  
    		WebElement name =  driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[2]/td[2]"));
    		String Centername =name.getText();
    		
    		return Centername;
 	}

}



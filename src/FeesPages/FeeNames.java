package FeesPages;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;



public class FeeNames {
	
	
	WebDriver driver;

    By createButton =By.linkText("Create");
    By Category =By.name("categoryId");
    By Feename=By.name("name");
    By SubmitButton =By.cssSelector("button.btn.red-intense");
    By ConfirmButton=By.cssSelector("button.confirm.btn.btn-lg.btn-primary");
 
	public FeeNames(WebDriver driver) {
	
		this.driver =driver;	

	}
	
	public void clickCreateButton() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));
		Thread.sleep(5000);
		driver.findElement(createButton).click();
	}
	
	public void ClickEditbutton() {
		driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr[1]/td[4]/a")).click();
	}
	
	public void selectCategory() {
		
		driver.findElement(Category).click();
		    new Select(driver.findElement(Category)).selectByVisibleText("Final Category2");
	}
 
    public void addFeeName(String name) {
    	driver.findElement(Feename).click();
        driver.findElement(Feename).clear();
        driver.findElement(Feename).sendKeys(name);
    }

   public void Submit() {
	    driver.findElement(SubmitButton).click();

   }
   
   public void RemoveFeeName() throws InterruptedException {
	   driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr[1]/td[4]/button")).click();
	   
	   WebDriverWait wait = new WebDriverWait(driver,10);
       wait.until(ExpectedConditions.visibilityOfElementLocated(ConfirmButton));
	   driver.findElement(ConfirmButton).click();
	   new Actions(driver).sendKeys(Keys.RETURN).perform();
	   Thread.sleep(2000);
	 	 driver.findElement(ConfirmButton).click();
	 	   Thread.sleep(3000);
	   
   }
  
   	public String GetFeeName() throws InterruptedException {
	  
   		WebElement name =  driver.findElement(By.xpath("//*[@id=\"dataTable\"]/tbody/tr[1]/td[3]"));
   		String Feename =name.getText();
   		
   		return Feename;
 
	   
   }
   
   
   
   public List<String> getFeeNames() {
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='dataTable']/tbody/tr"));
		List<String> feeNames = new ArrayList<>();
		for(WebElement  element: rows) {
			feeNames.add(element.findElement(By.xpath("td[3]")).getText());
		}
		return feeNames;
	}
	 
  public void verifyaddedFeeName(String userFeename)
  {
	  List<String> Feename= getFeeNames();
	   for(String  name: Feename) {
		   if (name.equals(userFeename)) {
			   
			   System.out.println("Fee name suceessfully added");
		   }
		   
	   }
  }
  public void verifyeditedFeeName(String Usereditedname) {
	  WebElement CategoryName=driver.findElement(By.xpath("//table[@id='dataTable']/tbody/tr[1]/td[3]"));
	  String Editedname=CategoryName.getText();
	  if(Editedname.equals(Usereditedname)) {
		  System.out.println("Fee name suceessfully edited");
	  }
  }


}
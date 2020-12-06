package FeesPages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Transportation {

	WebDriver driver;
	
	By SaveBatchBtn =By.cssSelector("button.btn.btn-primary");
	By addWaypointbtn=By.linkText("Add New Waypoint");
	By StructureName=By.name("name");
	By importButton =By.xpath("(//a[contains(text(),'Import Students')])[1]");
	By BatchDropdowwn =By.cssSelector("button.multiselect.dropdown-toggle.btn.btn-default");
	By Batch=By.xpath("//li[2]/a/label");
	By getStudentBtn=By.cssSelector("button.btn.btn-primary");
	By CheckBox=By.cssSelector("input.ckall.big-checkbox");
	By confirmbtn=By.cssSelector("button.confirm.btn.btn-lg.btn-primary");
	By redbutton=By.cssSelector("button.btn.btn-danger.saveFormData");
	By WayPoint=By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[2]/a");
	By removeButton=By.linkText("Remove");
	By confirmButtontoremove=By.cssSelector("button.confirm.btn.btn-lg.btn-primary");
	By Diablebutton=By.linkText("Disable");
    By confirmtoReomoveBtn=	By.cssSelector("button.confirm.btn.btn-lg.btn-primary");
    By redbuttontoDisable =By.cssSelector("button.btn.red.col-md-offset-4");
    By alert=By.cssSelector("p.alert.alert-info.alert-dismissable");
    By changeWaypointBtn =By.linkText("Change Waypoint");
    By bluebutton =By.cssSelector("button.btn.blue.col-md-offset-4");
	
	
	public Transportation(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	public List<String> getFeeNames() {
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
		List<String> Waypoint = new ArrayList<>();
			for(WebElement  element: rows) {
				Waypoint.add(element.findElement(By.xpath("td[2]")).getText());
		}
		return Waypoint;
		}
	 
	public void verifyWaypoint(String userWaypointname)
	{
	  List<String> WaypointName= getFeeNames();
	   for(String  name: WaypointName) {
		   if (name.equals(userWaypointname)) {
			   System.out.println("Way poiny suceessfully added");
		   }
		   
	   }

	}
	
	
	public void selectBatchToEnable(int BatchNO) {
		 driver.findElement(By.xpath("(//input[@name='batch[]'])["+BatchNO+"]")).click();
	}
	
	public void saveBatches() throws InterruptedException {
		Thread.sleep(2000);
	    driver.findElement(SaveBatchBtn).click();

	}
	
	public void clickAddwaypointButton() throws InterruptedException {
		Thread.sleep(2000);
	    driver.findElement(addWaypointbtn).click();

	}
	public void addStructureName(String name) throws InterruptedException  {
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,10);
	       wait.until(ExpectedConditions.visibilityOfElementLocated(StructureName));
		driver.findElement(StructureName).click();
	    driver.findElement(StructureName).clear();
	    driver.findElement(StructureName).sendKeys(name);
	}
	public void selectLabel() {
	
		driver.findElement(By.name("cycleLabels[]")).click();
	    new Select(driver.findElement(By.name("cycleLabels[]"))).selectByVisibleText("WinterFee");
	    driver.findElement(By.name("cycleLabels[]")).click();
	}
	
	public void editLabel() {
		driver.findElement(By.name("defaultCycle[22451][cycleLabel]")).click();
	    new Select(driver.findElement(By.name("defaultCycle[22451][cycleLabel]"))).selectByVisibleText("summer fee");
	    driver.findElement(By.name("defaultCycle[22451][cycleLabel]")).click();
	}
	
	public void addAmount() {
		driver.findElement(By.name("amount[]")).click();
	    driver.findElement(By.name("amount[]")).clear();
	    driver.findElement(By.name("amount[]")).sendKeys("1000");
	}
	
	public void editAmount() {
		driver.findElement(By.name("defaultCycle[22451][amount]")).click();
	    driver.findElement(By.name("defaultCycle[22451][amount]")).clear();
	    driver.findElement(By.name("defaultCycle[22451][amount]")).sendKeys("500");
	}
	
	public void addNewRow() {
		driver.findElement(By.cssSelector("button.btn.btn-info.btn-sm")).click();
	}
	public void clickRedbutton() {
		driver.findElement(By.cssSelector("button.btn.red-intense")).click();
	}
	
	public void ClickEditbutton() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[4]/a[1]")).click();
		
	}
	public void verifyeditedEditedName(String Usereditedname) {
		  WebElement WapointName=driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[2]"));
		  String Editedname=WapointName.getText();
		  if(Editedname.equals(Usereditedname)) {
			  System.out.println("Center name suceessfully edited");
		  }
	}
	
	
	public void clickImportStudentButton() throws InterruptedException {
		Thread.sleep(2000);
	    driver.findElement(importButton).click();
	    Thread.sleep(3000);

	}
	public void selectBatchdropdown() {
		WebDriverWait wait = new WebDriverWait(driver,10);
	       wait.until(ExpectedConditions.visibilityOfElementLocated(BatchDropdowwn));
	    driver.findElement(BatchDropdowwn).click();

	}
	public void selecBatch() {
	    driver.findElement(Batch).click();

	}
	public void clickongetstudent() throws InterruptedException {
	    driver.findElement(getStudentBtn).click();
	    Thread.sleep(3000);

	}
	
	public void clickoncheckbox() {
		WebDriverWait wait = new WebDriverWait(driver,10);
	      wait.until(ExpectedConditions.visibilityOfElementLocated(CheckBox));
	  //  driver.findElement(CheckBox).click();
		for(int i = 1; i < 2;i++){
	    driver.findElement(By.xpath("(//input[@name='ckbox[]'])["+i+"]")).click();;
		}

	}
	public void clickRedbtntoSaveStudent() {
	    driver.findElement(redbutton).click();

	}
	
	public void clickconfirmbtntosubmit() throws InterruptedException {
		 driver.findElement(confirmbtn).click();
		  Thread.sleep(2000);
	 	  new Actions(driver).sendKeys(Keys.RETURN).perform();
		    driver.findElement(confirmbtn).click();
		    Thread.sleep(2000);
		 	  new Actions(driver).sendKeys(Keys.RETURN).perform();
		    driver.findElement(confirmbtn).click();
		    Thread.sleep(2000);
		 	  new Actions(driver).sendKeys(Keys.RETURN).perform();
		   
	}
	
	public int getStudentCount() {
		
		WebElement count= driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr[1]/td[3]"));
		int stucount =Integer.parseInt(count.getText());
		return stucount;
		
	}
	
	public void Clickonwaypoint() throws InterruptedException {
		 driver.findElement(WayPoint).click();
		 Thread.sleep(2000);
	}
	
	public void removeStudent() throws InterruptedException {
		
		  driver.findElement(removeButton).click();
		  Thread.sleep(2000);
	}
	
	public void confirmremovestudent() {
	    driver.findElement(confirmButtontoremove).click();
	}
	
	
	public void clickdiablebutton() throws InterruptedException {
		 driver.findElement(Diablebutton).click();
		 Thread.sleep(2000);
	}
	
	public void clickonconfirmbtn() throws InterruptedException {
	    driver.findElement(confirmtoReomoveBtn).click();
	    Thread.sleep(2000);

	}
	public void clickRedBtn() throws InterruptedException {
		 driver.findElement(redbuttontoDisable).click();
		 Thread.sleep(2000);
	}
	
	public void verifydisablestudent() {
	     
		 
	     assertEquals(driver.findElements(alert).size() ,1, "Success Alert is not present");
		 assertEquals(driver.findElement(alert).getText().substring(0, 30),"×\n" + 
		 		"Student disabled in waypoint","Incorrect Output");
		
	}
	
	public void clickEnablebutton() {
		 driver.findElement(By.linkText("Enable")).click();
	}
	
	public void verifyEnablestudent() {
		 assertEquals(driver.findElement(alert).getText().substring(0,30),"×\n" + 
			 		"Student enabled successfully","Incorrect Output");
		
	}
	
	public void clickChangeWaypoint() {
		 driver.findElement(changeWaypointBtn).click();
	}
	public void clickblueBtnTochangeWaypoint() {
		driver.findElement(bluebutton).click();
	}
	
	public void verifychangeWaypoint() {
		
			 assertEquals(driver.findElement(alert).getText(),"×\n" + 
				 		"Transportation waypoint updated successfully.","Incorrect Output");
	}
	
	
}

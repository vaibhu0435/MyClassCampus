package FeesPages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BatchSetup {

	WebDriver driver;
   Logger log = Logger.getLogger(BatchSetup.class);
   
   By start_date = By.name("start_date");
   By prevbutton= By.cssSelector("i.fa.fa-angle-left");
   By startmonthdate = By.xpath("//tr[2]/td[4]");
   By end_date =By.name("end_date");
    By fwdbutton = By.cssSelector("th.next");
	By Endmonthdate = By.xpath("//tr[3]/td[5]");
	String FeeUrl ="https://myclasscampus.com/d/feemanager";
	
	By cycle_months =By.name("cycle_months");
	By cycle_days =By.name("cycle_days");
	By cycle_date=By.name("cycle_date");
	By feestartdate=By.xpath("/html/body/div[4]/div[1]/table/tbody/tr[1]/td[5]");
	By Feelabel=By.xpath("//*[@id=\"tableOfLabelData\"]/table/tbody/tr/td[2]/select");
	By Feelabelcaption =By.xpath("//*[@id=\"tableOfLabelData\"]/table/thead/tr[1]/th");
	//By category= By.name("fc[]");
	By Feenameone= By.name("feename[]");
	By Feeamountone =By.name("feeamount[]");
	//By categorytwo=By.cssSelector("#tr_2 > td > select[name=\"fc[]\"]");
	By Feenametwo=By.xpath("(//select[@name='feename[]'])[2]");
	By Feeamounttwo=By.xpath("(//input[@name='feeamount[]'])[2]");
	By AddNewbatch = By.cssSelector("button.btn.red-intense");
	//By OKbtn =By.xpath("//tr[@id='tr_3']/td[4]/button");
	By Addfee =By.cssSelector("button.btn.blue.btn-sm.addFeestructureRow");
	By allcategory=By.cssSelector("input.big-checkbox.ckall");
	By importbutton=By.cssSelector("button.btn.blue.saveFormData");
	By importokbtn =By.cssSelector("button.confirm.btn.btn-lg.btn-primary");
	By successalert =By.xpath("/html/body/div[4]/div[4]/div/p");
	By import_student= By.linkText("Import Students");
	public BatchSetup(WebDriver driver) {
		
		this.driver=driver;
	}
	
	
	 public  void loadbatchsetuppage(String BatchId) throws  InterruptedException {
		      
		       driver.get(FeeUrl);	
	           driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
	           String batchUrl ="https://myclasscampus.com/feemanager/batch/create/"+BatchId;
		        driver.get(batchUrl); 
		        WebDriverWait wait = new WebDriverWait(driver,10);
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("caption-subject")));
		        assertEquals(driver.findElements(By.className("caption-subject")).size() ,2, "page caption is not present");

      	        assertTrue(driver.findElement(By.className("caption-subject")).getText().contains("SETUP BATCH"),"Incorrect title of the page");
		        
		         log.info("batch setup page loaded successfully");
			}
	 
	 public  void enterstartdateandenddate(int st) throws  InterruptedException {
		    
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
		    Thread.sleep(3000);
		    driver.findElement(start_date).click();
		    int end=st*2;
			for(int i=1; i<=st;i++)
			{
		    driver.findElement(prevbutton).click();
		    } 
		    driver.findElement(startmonthdate).click();
			driver.findElement(end_date).click();

			for(int i=1; i<=end;i++)
			{
		    driver.findElement(fwdbutton).click();
		    }
		    driver.findElement(Endmonthdate).click();
		    
		    log.info("start date and End date are selected");
		    
		   }

	public void Entercyclemonth(String months , String days) {
		
		driver.findElement(cycle_months).click();
	    driver.findElement(cycle_months).clear();
	    driver.findElement(cycle_months).sendKeys(months);
	    driver.findElement(cycle_days).click();
	    driver.findElement(cycle_days).clear();
	    driver.findElement(cycle_days).sendKeys(days);
	    log.info("cyclemonth and day are selected");
	    
	    }
	
	public void Selectdatetostartfee() throws InterruptedException
	{
		    driver.findElement(cycle_date).click();
		    driver.findElement(feestartdate).click();
	        Thread.sleep(3000);
            
	        log.info("Date to start fee is selected");
		
	}
	
	public void verifydateselection() {
	 WebDriverWait wait = new WebDriverWait(driver,5);
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(Feelabelcaption));
	 	assertEquals(driver.findElements(Feelabelcaption).size() ,1, "Fee label caption is not present");
	 	assertEquals(driver.findElement(Feelabelcaption).getText(),"Select Fee Labels","date is not selected properly");
  
	        

	}
	
	public void SelectFeelabel(String label) {
		driver.findElement(Feelabel).click();
        new Select(driver.findElement(Feelabel)).selectByVisibleText(label);
        log.info("Fees label is selected properly");
        
	}
	
//	public void SelectFeesCategoryOne(String cat ,String feename , String amount) throws InterruptedException {
//		
//		 driver.findElement(categoryone).click();
//		    new Select(driver.findElement(categoryone)).selectByVisibleText(cat);
//		    driver.findElement(categoryone).click();
//		    driver.findElement(Feenameone).click();
//		    new Select(driver.findElement(Feenameone)).selectByVisibleText(feename);
//		    driver.findElement(Feenameone).click();
//		    driver.findElement(Feeamountone).click();
//		    driver.findElement(Feeamountone).clear();
//		    driver.findElement(Feeamountone).sendKeys(amount);
//		    Thread.sleep(3000);
//
//		    
//		    log.info("Fee category one is selected properly");
//		    
//	     }
//	
   public void SelectFeesCategory(String cat ,String feename , String amount , int num) throws InterruptedException {
         	   
	       Thread.sleep(1000);
	       By category= By.xpath("//*[@id=\"tr_"+num+"\"]/td[1]/select"); 
	       By nameoffee=By.xpath("//*[@id=\"tr_"+num+"\"]/td[2]/select");
	       By feeamount=By.xpath("//*[@id=\"tr_"+num+"\"]/td[3]/input");
		    driver.findElement(category).click();
		    new Select(driver.findElement(category)).selectByVisibleText(cat);
		    driver.findElement(category).click();
		    driver.findElement(nameoffee).click();
		    new Select(driver.findElement(nameoffee)).selectByVisibleText(feename);
		    driver.findElement(nameoffee).click();
		    driver.findElement(feeamount).click();
		    driver.findElement(feeamount).clear();
		    driver.findElement(feeamount).sendKeys(amount);
		    Thread.sleep(3000);
		    driver.findElement(Addfee).click();
		    log.info("Fees category "+num+" is seleted properly");
		    
		    
           // log.info(" Fees categories is seleted properly");   
   }
    public void clickaddnewbatch() throws InterruptedException {
    	//driver.findElement(By.cssSelector("table.table.feestructure-table")).click();
       // driver.findElement(By.xpath("//tr[@id='tr_3']/td[4]/button")).click();
        Thread.sleep(3000);

		    driver.findElement(AddNewbatch).click();
		    //driver.findElement(OKbtn).click();
		    Thread.sleep(3000);
//		    WebDriverWait wait = new WebDriverWait(driver,5);
//		 	wait.until(ExpectedConditions.visibilityOfElementLocated(import_student));
//		 	assertEquals(driver.findElements(import_student).size() ,1, "Import student is not avalilable"); 
//
//		
		    log.info("Batchsetup is suceessfull");
		   //Thread.sleep(3000);
	}
    public  void Importstudent() throws  InterruptedException{
    	Thread.sleep(2000);
    	driver.findElement(import_student).click();
        Thread.sleep(3000);

        driver.findElement(allcategory).click();
        Thread.sleep(3000);

        driver.findElement(importbutton).click();
        Thread.sleep(3000);
        driver.findElement(importokbtn).click();
        Thread.sleep(3000);
     
        
    
//        WebDriverWait wait = new WebDriverWait(driver,5);
//	 	wait.until(ExpectedConditions.visibilityOfElementLocated(successalert));
//	 	assertEquals(driver.findElements(successalert).size() ,1, "Import student is not avalilable");   
//	
        log.info("student imported successfully");
    }
	
}


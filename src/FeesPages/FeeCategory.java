package FeesPages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FeeCategory {

	
	WebDriver driver;
	Logger log = Logger.getLogger(FeeCategory.class);
	By Addcategorybutton = By.linkText("Add New Category");
	By EDitButton= By.linkText("Edit");
	By CategoryName = By.name("category_name");
	By InstituteName = By.name("institute_name");
	By InstituteAddress = By.name("institute_address");
	By ReceiptHeader= By.name("receipt_header");
	By SignaturePic = By.name("signature_pic");
	By Textypedropdown = By.name("tax_type");
	By TaxPercentage = By.name("tax_percentage");
	By GSTCodeType = By.name("gst_code_type");
	By GSTCode = By.name("gst_code");
	By GSTCodeDescription = By.name("gst_hsn_description");
	By FeeName = By.name("feenames[]");
	By Addbutton = By.cssSelector("table.table.feestructure-table");
	By Redaddnewbutton = By.cssSelector("button.btn.red-intense");
	public FeeCategory(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	public void clickaddnewbutton() {
		
		driver.findElement(Addcategorybutton).click();
		
	}
	
	public void clickEditButton(int lastadded){
		driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+lastadded+"]/td[9]/a")).click();	
	}
	public void addCategoryName(String Category) {
		 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
	     WebDriverWait wait = new WebDriverWait(driver,10);
	     wait.until(ExpectedConditions.visibilityOfElementLocated(CategoryName));
		driver.findElement(CategoryName).click();
	    driver.findElement(CategoryName).clear();
	    driver.findElement(CategoryName).sendKeys(Category);
		
	}
	public List<String> getCategoryNames() {
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
		List<String> category = new ArrayList<>();
		for(WebElement  element: rows) {
			category.add(element.findElement(By.xpath("td[2]")).getText());
		}
		return category;
	}
	 
   public void verifyAddedcategoryName(String userCategoryname)
   {
	  List<String> Categoryname= getCategoryNames();
	   for(String  name: Categoryname) {
		   if (name.equals(userCategoryname)) {
			   
			   System.out.println("Category is suceessfully added");
		   }
	   }  
	}

   public void veryfyeditedcategoryName(int lastEdited ,String userCategoryname) {
	   
	  WebElement EditedRowCategory = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr["+lastEdited+"]/td[2]"));
	 String categoryName= EditedRowCategory.getText();
	 if(categoryName.equals(userCategoryname)) {
		 
		 System.out.println("Category is suceessfully Edited");
	 }
	
	   
   }
	
	public void addInstituteName() {
		
			driver.findElement(InstituteName).click();
		    driver.findElement(InstituteName).clear();
		    driver.findElement(InstituteName).sendKeys("Progress Academy");
	}
	
	public void addInstititeAddress() {
		driver.findElement(InstituteAddress).click();
	    driver.findElement(InstituteAddress).clear();
	    driver.findElement(InstituteAddress).sendKeys("Xyz");
	}
	


	public void addReceieptheader() {
		
		  driver.findElement(ReceiptHeader).click();
		    driver.findElement(ReceiptHeader).clear();
		    driver.findElement(ReceiptHeader).sendKeys("Fee Management of Progress Academy");
		
	}
	
	public void selectSignature() {
		driver.findElement(SignaturePic).click();
	    new Select(driver.findElement(SignaturePic)).selectByVisibleText("vaibhavi");
	}
	
	public void selectTaxtype() {
		driver.findElement(Textypedropdown).click();
	    //driver.findElement(By.cssSelector("div.form-body")).click();
	}
	
	public void addTaxPercentage() {
		 driver.findElement(TaxPercentage).click();
		    driver.findElement(TaxPercentage).clear();
		    driver.findElement(TaxPercentage).sendKeys("10");
	}
	
	public void selectGStcodetype() {
		
		driver.findElement(GSTCodeType).click();
	    new Select(driver.findElement(GSTCodeType)).selectByVisibleText("HSN");
	}
	
	public void addGSTCode() {
		driver.findElement(GSTCode).click();
	    driver.findElement(GSTCode).clear();
	    driver.findElement(GSTCode).sendKeys("GHHHYH");
	}
	
	public void addGSTcodedescription() {
		    driver.findElement(GSTCodeDescription).click();
		    driver.findElement(GSTCodeDescription).clear();
		    driver.findElement(GSTCodeDescription).sendKeys("NO description");
	}
	
	public void addFeename(String Feename) {
		driver.findElement(FeeName).click();
	    driver.findElement(FeeName).clear();
	    driver.findElement(FeeName).sendKeys(Feename);
	    
	}
	
	public void clickAddFeebutton() {
	    driver.findElement(Addbutton).click();

	}
	
	public void clickaddnewRedbutton() throws InterruptedException {
	    driver.findElement(By.cssSelector("div.form-body")).click();
		Thread.sleep(2000);
	    driver.findElement(Redaddnewbutton).click();
	}
	
	
	
	
	
}

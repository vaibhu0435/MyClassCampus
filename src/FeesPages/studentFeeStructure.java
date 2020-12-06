package FeesPages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class studentFeeStructure {

	WebDriver driver;
	By AddNewBtn=By.cssSelector("button.btn.btn-outline.green-jungle");
	By Category =By.name("fm_structure_id");
	By Label =By.name("label_id");
	By DateDropDown=By.name("date");
	By Date =By.xpath("//div[5]/div/table/tbody/tr[2]/td[4]");
	By Amount =By.name("amount");
	By CreateBtn=By.cssSelector("button.btn.btn-primary");
	By Alert=By.cssSelector("p.alert.alert-info.alert-dismissable");
	By BulkDiscountBtn = By.xpath("//button[@onclick=\"if (!window.__cfRLUnblockHandlers) return false; $('.discountWell').toggle(); $('.categoryDiscountWell').css('display', 'none'); $('.addNewCycleWell').css('display', 'none'); $('.applyScholarshipWell').css('display', 'none');\"]");	
	By DiscountCategory =By.cssSelector("select.form-control.autoDiscountCategory");
	By Percentages =By.cssSelector("input.form-control.autoDiscountPercentage");
	By CheckBox =By.cssSelector("input.big-checkbox.autoDiscountWithTax");
	By CalculateBtn=By.cssSelector("button.btn.btn-outline.blue.autoDiscountCalculate");
	By ApplyBtn =By.cssSelector("button.btn.blue.autoDiscountBtn");
	By DiscountCategorywsieBtn = By.xpath("//button[@onclick=\"if (!window.__cfRLUnblockHandlers) return false; $('.categoryDiscountWell').toggle(); $('.discountWell').css('display', 'none'); $('.addNewCycleWell').css('display', 'none'); $('.applyScholarshipWell').css('display', 'none');\"]");
	By CategoryDropdown=By.cssSelector("span.multiselect-selected-text");
	By CategorywisePercentage=By.cssSelector("input.form-control.autoCategoryWiseDiscountPercentage");
	By DiscountAmt =By.cssSelector("input.form-control.autoCategoryWiseDiscountAmount");
	By Discounttype =By.cssSelector("select.autoCategoryWiseDiscountReason.form-control");
	By Discountcheckbox=By.cssSelector("input.big-checkbox.autoCategoryWiseDiscountWithTax");
	By CategoryWiseDiscountBtn=By.cssSelector("button.btn.btn-outline.blue.autoCategoryWiseDiscountBtn");
	By ConfirmBtn=By.cssSelector("button.confirm.btn.btn-lg.btn-primary");
	public studentFeeStructure(WebDriver driver) {
	
		this.driver=driver;
	}
	public void loadNewWindow() {
 		waitForNumberOfWindowsToEqual(2, driver);
		switchToTab(1, driver);
 	}
	public void clickAddNewButton() {
		driver.findElement(AddNewBtn).click();
	}
	
	public void selectCategory() {
		 driver.findElement(Category).click();
		    new Select(driver.findElement(Category)).selectByVisibleText("student fee - tution fee");
		    driver.findElement(Category).click();
	}
	public void selectFeeLabel() {
		 driver.findElement(Label).click();
		    new Select(driver.findElement(Label)).selectByVisibleText("Diwali fee");
		    driver.findElement(Label).click();
	}
	
	public void selectDate() {
		driver.findElement(DateDropDown).click();
	    driver.findElement(Date).click();
	}
	 
	public void addAmount() {
		driver.findElement(Amount).click();
	    driver.findElement(Amount).clear();
	    driver.findElement(Amount).sendKeys("0100");
	}
	public void clickCreateBtn() {

	    driver.findElement(CreateBtn).click();
	}
	
	public void VerifyAddcycle() {
		
		 
		 assertEquals(driver.findElements(Alert).size() ,1, "Success Alert is not present");
		 assertEquals(driver.findElement(Alert).getText(),"×\n" + 
		 		"New cycle added successfully.","Incorrect Output");
		 System.out.println("New Fee Cycle Added SuccessFully ");
	}
	
	public void ClickBukdiscountFeenamewiseBtn() {
		
		driver.findElement(BulkDiscountBtn).click();
		
	}
	
	public void selectCategoryforFeenamewiseDiscount() {
		
		driver.findElement(DiscountCategory).click();
	    new Select(driver.findElement(DiscountCategory)).selectByVisibleText("student fee - tution fee");
	    driver.findElement(DiscountCategory).click();
		
	}
	public void addPercentage() {
		driver.findElement(Percentages).click();
	    driver.findElement(Percentages).clear();
	    driver.findElement(Percentages).sendKeys("10");
	}
	
	public void clickcheckboxWithTax() {
	  
	  driver.findElement(CheckBox).click();
	}
  
  	public void Calculate() {
  	    driver.findElement(CalculateBtn).click();

  	}
  		public void ApplyDiscount() {
  		
  		 driver.findElement(ApplyBtn).click();
  	}
  		
  		public void clickBulkdiscountCategorywise() {

  			 driver.findElement(DiscountCategorywsieBtn).click();
  		}
  		
  		public void selectCategoryforCategorywiseDiscount() {
  			driver.findElement(CategoryDropdown).click();
  		    driver.findElement(By.xpath("//li[3]/a/label")).click();
  		}
	    
  		public void addcategorypercentage() {
  			driver.findElement(CategorywisePercentage).click();
  		    driver.findElement(CategorywisePercentage).clear();
  		    driver.findElement(CategorywisePercentage).sendKeys("10");
  		}
  		
  		public void AddDiscountAmount() {
  			driver.findElement(DiscountAmt).click();
  		    driver.findElement(DiscountAmt).clear();
  		    driver.findElement(DiscountAmt).sendKeys("200");
  		}
  		
  		public void selectDiscountType() {
  			driver.findElement(Discounttype).click();
  		    new Select(driver.findElement(Discounttype)).selectByVisibleText("score discount");
  		  driver.findElement(Discounttype).click();
  		}
  		public void clickcheckbox() {
  			driver.findElement(Discountcheckbox).click();
  		}
  		
  		public void ApplycategoryeiseDisocunt() {
  		    driver.findElement(CategoryWiseDiscountBtn).click();

  		}
  		
  		public void confirmDisount() {
  			driver.findElement(ConfirmBtn).click();
  		}
  		
  		public String getDue() {
  			WebElement due =driver.findElement(By.xpath("//h4[2]/span"));
  			String FeeDue=due.getText();
  			 System.out.println(FeeDue);
  			return FeeDue;
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
  			//System.out.println("switching tabs");
  			driver.switchTo().window(tabs.get(tabNumber));
  		}
}



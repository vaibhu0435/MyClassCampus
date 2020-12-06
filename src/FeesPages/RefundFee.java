package FeesPages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RefundFee {
	
	WebDriver driver;
	By PageHeader=By.cssSelector("span.caption-subject.bold.uppercase > a");
	
	public RefundFee(WebDriver driver) {
		
		this.driver=driver;
		
	}
	
	public void loadRefundPage() {
		driver.findElement(By.linkText("Refund")).click();
	}
	
	public void loadNewWindow(int wind) {
 		waitForNumberOfWindowsToEqual(wind, driver);
		switchToTab(wind-1, driver);
 	}
	
	public void selectPaymentMode() {
		
		driver.findElement(By.id("pay_mode")).click();
		  new Select(driver.findElement(By.id("pay_mode"))).selectByVisibleText("Cash");
	    driver.findElement(By.id("pay_mode")).click();
	}
	
	public void selectRefundDate() {
		 driver.findElement(By.name("pay_date")).click();
		    driver.findElement(By.xpath("//div[5]/div/table/tbody/tr[3]/td[3]")).click();
	}
	
	public void addAmount() {
		driver.findElement(By.name("amount[]")).click();
	    driver.findElement(By.name("amount[]")).clear();
	    driver.findElement(By.name("amount[]")).sendKeys("10");
	}
	
	public void selectCollectionCenter() {
		 driver.findElement(By.name("collection_center")).click();
		    new Select(driver.findElement(By.name("collection_center"))).selectByVisibleText("academy admin");
		    driver.findElement(By.name("collection_center")).click();
	}
	
	public void selectSMSoption() {
		  driver.findElement(By.name("send_sms[]")).click();
	}
	
	public void clickRefundbutton() {
		driver.findElement(By.cssSelector("button.btn.btn-danger")).click();
	}
	
	public void clickconfirmButton() {
		driver.findElement(By.cssSelector("button.btn.btn-default")).click();
	}
	
	
	public void  clickEditButton() {
		driver.findElement(By.linkText("Edit")).click();
	}
	
	public void editAmount() {
		driver.findElement(By.name("amount[]")).click();
	    driver.findElement(By.name("amount[]")).clear();
	    driver.findElement(By.name("amount[]")).sendKeys("10");
	}
	
	public void addpaymentNote() {
		 driver.findElement(By.name("payment_note")).click();
		    driver.findElement(By.name("payment_note")).clear();
		    driver.findElement(By.name("payment_note")).sendKeys("note");
	}
	
	public void addReason() {
		driver.findElement(By.name("update_note")).click();
	    driver.findElement(By.name("update_note")).clear();
	    driver.findElement(By.name("update_note")).sendKeys("no reason");
	}
	
	public void confirmEdit() {
		 driver.findElement(By.cssSelector("button.btn.btn-primary.takeFeeBtn")).click();
	}

	
	
	
	public String getFeerecieptNo() {
	WebElement receipt=	driver.findElement(By.xpath("//*[@id=\"modal\"]/div/div/div[1]/table/tbody/tr/td[2]"));
	String receiptNo =receipt.getText();
	System.out.println("Payment Receipt:" +receiptNo);
	return receiptNo;
	}
	
    
    public List<String> getReceiptFromTabel() {
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr"));
		List<String> Receipts = new ArrayList<>();
		for(WebElement  element: rows) {
			Receipts.add(element.findElement(By.xpath("td[1]")).getText());
		}
		return Receipts;
	}
	 
  public void verifyRefund( String ReceiptNoatPaymenttime)
  {
	 
	  List<String> ReciptsonTable= getReceiptFromTabel();
	   for(String  No: ReciptsonTable) {
		   if (No.equals(ReceiptNoatPaymenttime)) {
			   
			   System.out.println("Refund is successfull");
		   }
		   
	   }
  }
  
  public void verifyedit() {
	 
	  
	  assertEquals(driver.findElements(PageHeader).size() ,1, "Take Fee page is not present");
		 assertEquals(driver.findElement(PageHeader).getText(),"STUDENT'S PROFILE","Incorrect Page Redirection");
		 System.out.println("EditRefund is successfull");
  }
  
  public void ViewFeeReciept() {
	  
	  WebElement receipt = driver.findElement(By.cssSelector("td.sorting_1"));
	  String ReceiptOnTabel =receipt.getText();
	  System.out.println(ReceiptOnTabel);
	  driver.findElement(By.cssSelector("i.fa.fa-eye")).click();
	  WebElement Receipt=driver.findElement(By.cssSelector("div.col-xs-6.invoice-payment.print-right > ul.list-unstyled > li"));
	  String RecieptNoOnviewReceipt=Receipt.getText().substring(13);
	  System.out.println(RecieptNoOnviewReceipt);
	  if(ReceiptOnTabel.equals(RecieptNoOnviewReceipt)) {
		  
		  System.out.println("Reciept page is open sucessfully and matches the RecieptNo");
		  
	  }else {
		  System.out.println("Something is wrong with Reciept page");
	  }
	  
	  
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



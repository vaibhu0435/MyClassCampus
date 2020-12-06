package FeesPages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FeePaymentHistory {

	By viewButton = By.linkText("View");
	By Amount = By.name("amount[]");
	By paymentNote = By.name("payment_note");
	By updateNote = By.name("update_note");
	By EditFeeBtn = By.cssSelector("button.btn.btn-primary.takeFeeBtn");
	By EditBtn = By.linkText("Edit");
	By fineAmount = By.name("fine_amount[]");
	WebDriver driver;
	By clickBtn = By.linkText("Cancel");

	public FeePaymentHistory(WebDriver driver) {

		this.driver = driver;
	}

	public void viewReciept() {
		driver.findElement(viewButton).click();
	}

	public String GetRecieptNo() {
		WebElement RcptNo = driver.findElement(By.xpath("//table[@id='paymentTable']/tbody/tr/td[2]"));
		String RecieptNoOnTabel = RcptNo.getText();
		return RecieptNoOnTabel;
	}

	public String verifyViewReciept() {
		WebElement RcptNo = driver
				.findElement(By.cssSelector("div.col-xs-6.invoice-payment.print-right > ul.list-unstyled > li"));
		String ReceiptNoOnviewPage = RcptNo.getText().substring(13);
		return ReceiptNoOnviewPage;
	}

	public void loadNewWindow() {
		waitForNumberOfWindowsToEqual(2, driver);
		switchToTab(1, driver);
	}

	public void clickEditButton() {
		driver.findElement(EditBtn).click();
	}

	public void changeAmount() {
		driver.findElement(Amount).click();
		driver.findElement(Amount).clear();
		driver.findElement(Amount).sendKeys("050");
	}

	public void chnagefineAmount() {
		driver.findElement(fineAmount).click();
		driver.findElement(fineAmount).clear();
		driver.findElement(fineAmount).sendKeys("010");
	}

	public void addNote() {
		driver.findElement(paymentNote).click();
		driver.findElement(paymentNote).clear();
		driver.findElement(paymentNote).sendKeys("no note");
	}

	public void addReason() {
		driver.findElement(updateNote).click();
		driver.findElement(updateNote).clear();
		driver.findElement(updateNote).sendKeys("no reason");
	}

	public void clickEditFeeButton() {
		driver.findElement(EditFeeBtn).click();
	}

	public String getAmount() {
		WebElement amt = driver.findElement(By.xpath("//table[@id='paymentTable']/tbody/tr/td[4]"));
		String amount = amt.getText();
		return amount;
	}

	public void clickDeleteButton() {
		driver.findElement(By.linkText("Delete")).click();
	}

	public void clickConfirmButton() throws InterruptedException {
		driver.findElement(By.cssSelector("button.confirm.btn.btn-lg.btn-primary")).click();
		Thread.sleep(1000);
		new Actions(driver).sendKeys(Keys.RETURN).perform();
		driver.findElement(By.cssSelector("button.confirm.btn.btn-lg.btn-primary")).click();
		Thread.sleep(1000);
		new Actions(driver).sendKeys(Keys.RETURN).perform();
		driver.findElement(By.cssSelector("button.confirm.btn.btn-lg.btn-primary")).click();
		Thread.sleep(1000);
	}

	public void addDeleteReason() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("body > div.sweet-alert.visible.showSweetAlert.show-input > div.form-group > input")).click();
		driver.findElement(By.cssSelector("body > div.sweet-alert.visible.showSweetAlert.show-input > div.form-group > input")).clear();
		driver.findElement(By.cssSelector("body > div.sweet-alert.visible.showSweetAlert.show-input > div.form-group > input")).sendKeys("no Reason");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("body > div.sweet-alert.visible.showSweetAlert.show-input > div.sa-button-container > div > button")).click();
		Thread.sleep(1000);
	}

	public void confirmDelete() {
		driver.findElement(By.cssSelector("button.confirm.btn.btn-lg.btn-primary")).click();
	}

	public void clickCancelbutton() {
		driver.findElement(clickBtn).click();
	}

	public void addCancelReason() throws InterruptedException {
		driver.findElement(By.cssSelector("body > div.sweet-alert.showSweetAlert.visible.show-input > div.form-group > input")).click();
		driver.findElement(By.cssSelector("body > div.sweet-alert.showSweetAlert.visible.show-input > div.form-group > input")).clear();
		driver.findElement(By.cssSelector("body > div.sweet-alert.showSweetAlert.visible.show-input > div.form-group > input")).sendKeys("cancel");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("body > div.sweet-alert.showSweetAlert.visible.show-input > div.sa-button-container > div > button")).click();
	}
	
	public void confirmCancel() {
		driver.findElement(By.cssSelector("button.confirm.btn.btn-lg.btn-primary")).click();
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
		// System.out.println("switching tabs");
		driver.switchTo().window(tabs.get(tabNumber));
	}

}

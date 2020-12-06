package LoginPage;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Login {

	WebDriver driver;
	Logger log = Logger.getLogger(Login.class);
	By country = By.id("country_id");
	By mobilenum = By.id("mobile");
	By pwd = By.id("password");
	By loginbtn = By.cssSelector("button.btn.green.uppercase.btn_login");

	public Login(WebDriver driver) {
		this.driver = driver;
	}

	public void selectCountry(String cid) throws InterruptedException {
		driver.findElement(country).click();
		Thread.sleep(2000);
		System.out.println(cid);
		new Select(driver.findElement(country)).selectByValue(cid);
		driver.findElement(country).click();
	}

	public void typeMobile(String Monum) {

		driver.findElement(mobilenum).click();
		driver.findElement(mobilenum).clear();
		driver.findElement(mobilenum).sendKeys(Monum);
	}

	public void typepwd(String pass) {
		driver.findElement(pwd).click();
		driver.findElement((pwd)).clear();
		driver.findElement(pwd).sendKeys(pass);

	}

	public void clickloginbutton() {

		driver.findElement(loginbtn).click();
		log.info("login worked successfully");

	}
	
	public void performLogin(Properties config) throws InterruptedException {
		driver.get("https://myclasscampus.com/login");
		selectCountry(config.getProperty("CountryID"));
		typeMobile(config.getProperty("mobile"));
		typepwd(config.getProperty("password"));
		clickloginbutton();
	}

}

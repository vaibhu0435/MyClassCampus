package FeesPages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentPage {
	
	WebDriver driver;
	Logger log = Logger.getLogger(StudentPage.class);

	String FeesmanagerURL = "https://myclasscampus.com/d/feemanager";

	String StuentpageURL = "https://myclasscampus.com/feemanager/student";
	By Department = By.id("department");
	By classroomdropdown = By.className("multiselect-selected-text");
	// By classname =By.xpath("//li[9]/a/label");
	By submitbtn = By.id("submitFilter");
	
	public StudentPage(WebDriver driver) {

		this.driver = driver;
	}
	public void selectdeprtment(String Dept) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(3000);
		driver.findElement(Department).click();
		new Select(driver.findElement(Department)).selectByVisibleText(Dept);
		Thread.sleep(3000);
	}

	public void selectclassroom(int classno) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(classroomdropdown));

		driver.findElement(classroomdropdown).click();
		driver.findElement(By.xpath("//li[" + classno + "]/a/label")).click();

	}

	public void submitbutton() {

		driver.findElement(submitbtn).click();
	}

    public  double GetCurrentdueOfStudent(int student) {
		
		By currentdue =By.xpath("//*[@id=\"dataTable\"]/tbody/tr["+student+"]/td[13]");
		WebElement dueamount=driver.findElement(currentdue);
		dueamount.getText();
		double currentdueamount=Double.valueOf(dueamount.getText());
	    return currentdueamount;
	}
	
	public String studentname(int student) {
		By studentname = By.xpath("//*[@id=\"dataTable\"]/tbody/tr[" + student + "]/td[4]/a");
		WebElement el = driver.findElement(studentname);
		String stuname = el.getText();
		return stuname;
		
	}
	
	public void Clickstudent(int student) {
		
		By studentname = By.xpath("//*[@id=\"dataTable\"]/tbody/tr[" + student + "]/td[4]/a");

		driver.findElement(studentname).click();

	}
	
	public double getCheckamount(int student) {
		By getcheckamount = By.xpath("//*[@id=\"dataTable\"]/tbody/tr["+student+"]/td[14]");
		WebElement checkamt=driver.findElement(getcheckamount);
		double checkamount =Double.valueOf(checkamt.getText());
		return checkamount;
	}

}

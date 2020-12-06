package AllTestCases;

import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import FeesPages.StudentPage;
import FeesPages.ViewDemandLetter;
import LoginPage.Login;
import util.TestUtil;

public class VerifyDemandLetter {
	WebDriver driver;
	Properties config = new Properties();

	@BeforeClass
	public void setUp() throws Exception {
	
		PropertyConfigurator.configure("log4j.properties");

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\aakis\\Downloads\\chromedriver_win32_new\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		config = TestUtil.readProperties();
		Login login=new Login(driver);
		login.performLogin(config);
		
	}
	
	public void LoadstudentProfiletpage() throws InterruptedException {
		String FeesmanagerURL = "https://myclasscampus.com/d/feemanager";
		driver.get(FeesmanagerURL);
		int student = 3;
		String StuentpageURL = "https://myclasscampus.com/feemanager/student";
		driver.get(StuentpageURL);
		int roomno = Integer.parseInt(config.getProperty("Classno"));
		StudentPage stu = new StudentPage(driver);
		stu.selectdeprtment(config.getProperty("Department"));
		stu.selectclassroom(roomno);
		stu.submitbutton();
		stu.Clickstudent(student);
	}
	
	@Test(priority=1)
	public void verifyDemandLetter()throws InterruptedException {
	
		LoadstudentProfiletpage();
        ViewDemandLetter fee = new ViewDemandLetter(driver);
        String getCurrrentDueOnStudentListpage=fee.getCurrrentDueOnStudentListpage();
        System.out.println(getCurrrentDueOnStudentListpage);
        int wind=2;
        fee.loadNewWindow(wind);
        wind++;
        fee.clickDemandletterBtn();
        fee.loadNewWindow(wind);
        fee.selectDate();
        fee.submit();
        String GetCurrentdueOnDemamndLetter =fee.GetCurrentdueOnDemamndLetter();
        System.out.println(GetCurrentdueOnDemamndLetter);
        if(getCurrrentDueOnStudentListpage.equals(GetCurrentdueOnDemamndLetter)) {
        	
        	System.out.println("DemandLetter is opened and its showing correct data ");
        }
        else {
        	System.out.println("Something is wrong with DemandLetter");
        }
}

}
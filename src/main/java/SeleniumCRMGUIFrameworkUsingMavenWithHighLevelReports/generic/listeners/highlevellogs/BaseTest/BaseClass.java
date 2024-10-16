package SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.listeners.highlevellogs.BaseTest;

import java.sql.SQLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.DatabaseUtility.*;
import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.FileUtility.*;
import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.JavaUtility.*;
import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.ObjectRepository.HomePage;
import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.ObjectRepository.LoginPage;
import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.webDriverUtility.*;

public class BaseClass {
	/* Create Object and declare it as public(Access-Specifier) 
	 * because they are available in different packages*/
	 public DataBaseUtility dbLib = new DataBaseUtility();
	 public FileUtility fLib = new FileUtility();
	 public ExcelUtility eLib = new ExcelUtility();
	 public JavaUtility jLib = new JavaUtility();
	 public WebDriverUtility wLib = new WebDriverUtility();
	 public WebDriver driver = null;
	 public static WebDriver sdriver = null;
	 /* If we declare driver variable as static it won't participate in parallel execution because
	  static variables has only one instance which isn't possible to use in parallel execution to
	  avoid that we will create another driver variable */
	@BeforeSuite
	public void configBS() throws SQLException {
		System.out.println("====connect to DB, Report Config===");
		dbLib.getDbconnection();
	}

	@BeforeClass
	public void configBC() throws Throwable {
		System.out.println("===launch browser===");
		/* String BROWSER = fLib.getDataFromPropertiesFile("browser"); */
		String BROWSER = System.getProperty("browser");
		/* Run-time Polymorphism.In order to achieve run-time polymorphism, we need to follow three rules.
		They are : 1.Up casting, 2. Inheritance 3. overriding */
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}

	@BeforeMethod
	public void configBM() throws Throwable {
		System.out.println("=login=");
		LoginPage lp = new LoginPage(driver);
		/* String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password"); */
		String URL = System.getProperty("url");
		String USERNAME = System.getProperty("username");
		String PASSWORD = System.getProperty("password");
		lp.loginToapp(URL, USERNAME, PASSWORD); 
	}

	@AfterMethod
	public void configAM() {
		System.out.println("=logout=");
		HomePage hp = new HomePage(driver);
		hp.logOut();
	}

	@AfterClass
	public void configAC() {
		System.out.println("===close the browser===");
		driver.quit();
	}

	@AfterSuite
	public void configAS() throws SQLException {
		System.out.println("===close Db, Report backup===");
		dbLib.closeDbConnection();
		}

}

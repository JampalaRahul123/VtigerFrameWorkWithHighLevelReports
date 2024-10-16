package SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.webDriverUtility.WebDriverUtility;

/**
 * 
 * @author DELL
 * 
 * contains login page elements and business libraries like login()
 * 
 */

	//Rule - 1 : create a separate java class
public class LoginPage extends WebDriverUtility{ 
	// Rule - 2 : Object Creation
	WebDriver driver;
	// Declare the driver variable as Global Variable because every test script launches a new browser 
	// as the driver object is declared in test script
	// Rule - 3 : Object Initialization
	// Provide a constructor and declare pageFactory class inside it
	public LoginPage(WebDriver driver) {  //Local Variable
		this.driver=driver;
		// To avoid confusion between global and local variable and load local variable in global variable
		PageFactory.initElements(driver,this); // this keyword represents to current class object reference
		//The advantage here is at the time of object creation, automation engineer don't need to worry 
		//about initialization because we can call a constructor which will take care of initialization
		//At the time of object creation, this constructor is being executed.
		// This constructor will take care of initialization of object,loading the object
		// and also it will take care of execution of all @FindBy annotations and load the element with the current address
	}
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	// If you keep the elements with default access specifier, this elements cannot be accessible outside the package.
	// If you want to access the elements, make them as public.
	// As per the rule of automation, we shouldn't hardcode the element.
	// Instead of this, we should get the element locator from object repository.
	// The problem here is since all the elements are public, other automation engineers can modify/re-initialize this element.
	//  As per the rule of automation, we shouldn't allow modify the element locator in their test script
	// because automation rule always says if there is any change in UI, come to repository and change it.
	// To provide such flexibility, i want to avoid write access for every body.
	// To do this, we should make all the elements as private
	
	// Rule - 4 : Object Encapsulation
	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	/**
	 * 
	 * login to application based on username, password,url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	// Rule - 5 : Provide Action (Object Utilization)
	public void loginToapp(String url,String username, String password) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginBtn.click();
	}
}

package SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.webDriverUtility.*;

//Rule - 1 : create a separate java class
public class CreatingNewContactPage extends WebDriverUtility {
	// Rule - 2 : Object Creation
	WebDriver driver;
	// Declare the driver variable as Global Variable because every test script launches a new browser
	// as the driver object is declared in test script
	// Rule - 3 : Object Initialization
	// Provide a constructor and declare pageFactory class inside it
	public CreatingNewContactPage(WebDriver driver) { // Local Variable
		this.driver = driver;
		// To avoid confusion between global and local variable and load local variable in global variable
		PageFactory.initElements(driver, this); // this keyword represents to current class object reference
		// The advantage here is at the time of object creation, automation engineer don't need to worry
		// about initialization because we can call a constructor which will take care of initialization
		// At the time of object creation, this constructor is being executed.
		// This constructor will take care of initialization of object,loading the object and also
		// it will take care of execution of all @FindBy annotations and load the element with the current address
	}

	@FindBy(xpath = "//input[@name='firstname']")
	private WebElement firstNameEdit;

	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement lastNameEdit;

	@FindBy(xpath = "//input[@name='support_start_date']")
	private WebElement startDateEdit;

	@FindBy(xpath = "//input[@name='support_end_date']")
	private WebElement endDateEdit;

	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement orgBtn;

	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement searchtext;

	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchBtn;

	@FindBy(xpath = "//td[@id='mouseArea_Organization Name']")
	private WebElement orgName;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	// If you keep the elements with default access specifier, this elements cannot be accessible outside the package.
	// If you want to access the elements, make them as public.
	// As per the rule of automation, we shouldn't hardcode the element.
	// Instead of this, we should get the element locator from object repository.
	// The problem here is since all the elements are public, other automation engineers can modify/re-initialize this element.
	// As per the rule of automation, we shouldn't allow modify the element locator in their test script
	// because automation rule always says if there is any change in UI, come to repository and change it.
	// To provide such flexibility, i want to avoid write access for every body.
	// To do this, we should make all the elements as private
	// Rule - 4 : Object Encapsulation
	public WebElement getFirstNameEdit() {
		return firstNameEdit;
	}

	public WebElement getLastNameEdit() {
		return lastNameEdit;
	}

	public WebElement getStartDateEdit() {
		return startDateEdit;
	}

	public WebElement getEndDateEdit() {
		return endDateEdit;
	}

	public WebElement getOrgBtn() {
		return orgBtn;
	}

	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getsearchText() {
		return searchtext;
	}

	public WebElement getsearchBtn() {
		return searchBtn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	// Rule - 5 : Provide Action (Object Utilization)
	public void createContact(String firstName, String lastName) {
		firstNameEdit.sendKeys(firstName);
		lastNameEdit.sendKeys(lastName);
		saveBtn.click();
	}

	public void createContactWithSupportDate(String firstName, String lastName, String startDate, String endDate) {
		firstNameEdit.sendKeys(firstName);
		lastNameEdit.sendKeys(lastName);
		startDateEdit.clear();
		startDateEdit.sendKeys(startDate);
		endDateEdit.clear();
		endDateEdit.sendKeys(endDate);
		saveBtn.click();
	}

	public void createContactWithOrg(String firstName, String lastName, String startDate, String endDate,String orgName) {
		firstNameEdit.sendKeys(firstName);
		lastNameEdit.sendKeys(lastName);
		startDateEdit.clear();
		startDateEdit.sendKeys(startDate);
		endDateEdit.clear();
		endDateEdit.sendKeys(endDate);
		orgBtn.click();
		shiftToChildBrowser(orgName);
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click(); // Dynamic Xpath
		shiftToParentBrowser();
		saveBtn.click();
	}

	public void shiftToChildBrowser(String orgName) {
		switchToTabOnURL(driver, "module=Accounts");
		searchtext.sendKeys(orgName);
		searchBtn.click();
	}

	public void shiftToParentBrowser() {
		switchToTabOnURL(driver, "Contacts&action");
	}
}
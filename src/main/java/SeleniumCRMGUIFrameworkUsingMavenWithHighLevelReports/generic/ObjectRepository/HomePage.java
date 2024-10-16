package SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
	//Rule - 1 : create a separate java class
public class HomePage {
	// Rule - 2 : Object Creation
	WebDriver driver;
	// Declare the driver variable as Global Variable because every test script launches a new browser 
	// as the driver object is declared in test script
	// Rule - 3 : Object Initialization
	// Provide a constructor and declare pageFactory class inside it
	public HomePage(WebDriver driver) {  //Local Variable
		this.driver=driver;
		// To avoid confusion between global and local variable and load local variable in global variable
		PageFactory.initElements(driver,this); // this keyword represents to current class object reference
		//The advantage here is at the time of object creation, automation engineer don't need to worry 
		//about initialization because we can call a constructor which will take care of initialization
		//At the time of object creation, this constructor is being executed.
		// This constructor will take care of initialization of object,loading the object
		// and also it will take care of execution of all @FindBy annotations and load the element with the current address
	}
	@FindBy(linkText="Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignLink;
	
	@FindBy(linkText="More")
	private WebElement moreLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLink;
	
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

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getCampaignLink() {
		return campaignLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}
	// Rule - 5 : Provide Action (Object Utilization)
	public void navigateToCampaignPage() {
		Actions action = new Actions(driver);
		action.moveToElement(moreLink).perform();
		campaignLink.click();
	}
	public void logOut() {
		Actions action = new Actions(driver);
		action.moveToElement(adminImg).perform();
		signOutLink.click();
	}
}

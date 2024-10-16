package practice.testng.baseclass.listener.extentreport.highlevellogs.contacttest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.ObjectRepository.ContactInfoPage;
import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.ObjectRepository.ContactsPage;
import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.ObjectRepository.CreatingNewContactPage;
import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.ObjectRepository.CreatingNewOrganizationPage;
import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.ObjectRepository.HomePage;
import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.ObjectRepository.OrganizationInfoPage;
import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.ObjectRepository.OrganizationsPage;
import SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.listeners.highlevellogs.BaseTest.*;

public class CreateContactTest extends BaseClass {

	@Test
	public void createContactTest() throws Throwable {
		SoftAssert AssertObj = new SoftAssert();  /* Soft Assert Object Creation */
		/* Read data From Excel File */
		String firstName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber(); /* To Generate random Number */
		String lastName = eLib.getDataFromExcel("contact", 1, 3);
		/* step 1 : Navigate To Contact Module */
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		/* step 2 : Click on Create Contact Button */
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();
		/* step 3 : Enter all the details and create new contact */
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact(firstName, lastName);
		wLib.waitForPageToLoad(driver);
		/* Step 4 : Verify Contact Name info Expected Result */
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actContactName = cip.getHeaderMsg().getText();
		boolean status1 = actContactName.contains(lastName);
		boolean status2 = actContactName.contains(firstName);
		Assert.assertEquals(status1, true); /* Hard Assert */
		AssertObj.assertEquals(status2, true); /* Soft Assert */
		AssertObj.assertAll();
	}

	@Test
	public void createContactWithSupportDateTest() throws Throwable {
		SoftAssert AssertObj = new SoftAssert();  /* Soft Assert Object Creation */
		/* Read data From Excel File */
		String firstName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber(); /* To Generate random Number */
		String lastName = eLib.getDataFromExcel("contact", 4, 3);
		/* step 1 : Navigate To Contact Module */
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		/* step 2 : Click on Create Contact Button */
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();
		/* step 3 : Enter all the details and create new contact */
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYDDMM(30);
		cncp.createContactWithSupportDate(firstName, lastName, startDate, endDate);
		wLib.waitForPageToLoad(driver);
		/* Step 4 : Verify Contact Name info Expected Result */
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actContactName = cip.getHeaderMsg().getText();
		boolean status1 = actContactName.contains(lastName);
		boolean status2 = actContactName.contains(firstName);
		Assert.assertEquals(status1, true); /* Hard Assert */
		AssertObj.assertEquals(status2, true); /* Soft Assert */
		String actStartDate = cip.getSupportStartDate().getText();
		Assert.assertEquals(actStartDate, startDate); /* Hard Assert */
		String actEndDate = cip.getSupportEndDate().getText();
		Assert.assertEquals(actEndDate,endDate); /* Hard Assert */
		AssertObj.assertAll();	
	}

	@Test
	public void createContactWithOrgTest() throws Throwable {
		SoftAssert AssertObj = new SoftAssert();  /* Soft Assert Object Creation */
		/* Read data From Excel File */
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();/* To Generate random Number */
		String firstName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcel("contact", 7, 4);
		/* step 1 : Navigate To Organization Module */
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		/* step 2 : Click on Create Organization Button */
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		/* step 3 : Enter all the details and create new organization */
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		wLib.waitForPageToLoad(driver);
		/* Step 4 : Verify Org Name info Expected Result */
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerInfo = oip.getHeaderMsg().getText();
		boolean status=headerInfo.contains(orgName); 
		Assert.assertEquals(status, true);	/* Hard Assert */
		/* Step 5 : Navigate To Contact Module */
		hp.getContactLink().click();
		/* step 6 : Click on Create Contact Button */
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();
		/* step 7 : Enter all the details and create new contact */
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYDDMM(30);
		cncp.createContactWithOrg(firstName, lastName, startDate, endDate, orgName);
		wLib.waitForPageToLoad(driver);
		/* Step 8 : Verify Contact Name info Expected Result */
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actContactName = cip.getHeaderMsg().getText();
		boolean status1 = actContactName.contains(lastName);
		boolean status2 = actContactName.contains(firstName);
		Assert.assertEquals(status1, true); /* Hard Assert */
		AssertObj.assertEquals(status2, true); /* Soft Assert */
		String actOrgName = cncp.getOrgName().getText();
		boolean status3=actOrgName.contains(orgName);
		/* Step 9 : Verify Act OrgName info Expected Result */
		Assert.assertEquals(status3,true); /* Hard Assert */
		String actStartDate = cip.getSupportStartDate().getText();
		AssertObj.assertEquals(actStartDate, startDate); /* Soft Assert */
		String actEndDate = cip.getSupportEndDate().getText();
		AssertObj.assertEquals(actEndDate,endDate);  /* Soft Assert */
		AssertObj.assertAll();	
	}
	
}

package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master","Sanity"})
	public void verify_account_registration()
	{
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		try
		{
			logger.info("***** Starting TC001_AccountRegistrationTest ******");
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link");
			hp.clickRegister();
			logger.info("Clicked on Register Link");

			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			logger.info("Providing customer details");
			regPage.setFirstName(randomString().toUpperCase());
			regPage.setLastName(randomString().toUpperCase());
			regPage.setEmail(randomString().toUpperCase()+"@gmail.com");
			regPage.setTelephone(randomNumber());
			String password = randomAlphaNumeric();
			regPage.setPassword(password);
			regPage.setConfPassword(password);
			regPage.CheckPolicy();
			regPage.clickButtonContinue();

			logger.info("Validation expected message...");
			String confMsg = regPage.getConfirmationMsg();
			Assert.assertEquals(confMsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed...");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		logger.info("***** Finished TC001_AccountRegistrationTest *****");
	}
}
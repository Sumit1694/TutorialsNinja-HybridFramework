package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC005_AddProductToCart extends BaseClass{

	@Test(groups={"Master"})
	public void AddToCartPageTest()
	{
		logger.info("*******Starting_TC005_AddProductTOCart *******");
		try {
			HomePage hp = new HomePage(driver);
			hp.enterProductName("iPhone");
			hp.clickSearch();

			SearchPage sp = new SearchPage(driver);
			if(sp.isProductExists("iPhone"))
			{
				sp.selectProduct("iPhone");
				sp.setQuantity("2");
				sp.clickButtonCart();
			}
            Assert.assertEquals(sp.checkConfMsg(), true);
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("****** Finished_TC005_AddProductTOCart ******");
	}
}
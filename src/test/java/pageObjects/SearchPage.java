package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//h4//a") List<WebElement> productsDisplayed;
	@FindBy(xpath="//input[@id='input-quantity' and @name='quantity']") WebElement inputQty;
	@FindBy(xpath="//button[@id='button-cart']") WebElement buttonCart;
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']") WebElement checkConfMsg;

	public boolean isProductExists(String ProductName)
	{
		for(WebElement product:productsDisplayed)
		{
			if(product.getText().equalsIgnoreCase(ProductName))
			{
				return true;
			}
		}
		return false;
	}

	public void selectProduct(String productName)
	{
		for (WebElement product : productsDisplayed)
		{
			if (product.getText().trim().equalsIgnoreCase(productName))
			{
				product.click();
				return; // exit method after clicking
			}
		}
		throw new RuntimeException("Product not found: " + productName);
	}

	public void setQuantity(String quantity)
	{
		inputQty.sendKeys(quantity);
	}

	public void clickButtonCart()
	{
		buttonCart.click();
	}

	public boolean checkConfMsg()
	{
		return checkConfMsg.isDisplayed();
	}
}
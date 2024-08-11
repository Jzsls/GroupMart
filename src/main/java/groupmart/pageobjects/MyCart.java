package groupmart.pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import groupmart.AbstractComponents.AbstractComponent;

public class MyCart extends AbstractComponent {

	WebDriver driver;
	By productNameInCart = By.tagName("h3");

	public MyCart(WebDriver driver) throws IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// introducing PageFactory page design
	@FindBy(className = "infoWrap")
	List<WebElement> cartProducts;

	@FindBy(css = ".subtotal button")
	WebElement checkoutButton;

	public boolean verifyProductInCart(String productName) {
		boolean cartProduct = cartProducts.stream()
				.anyMatch(product -> product.findElement(productNameInCart).getText().equalsIgnoreCase(productName));
		return cartProduct;

	}

	public PaymentPage clickOnCheckout() throws IOException {
		checkoutButton.click();
		return new PaymentPage(driver);

	}

}

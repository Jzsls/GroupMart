package groupmart.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import groupmart.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;
	By orderedProductName = By.xpath("//td[2]");
	

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// introducing PageFactory page design
	@FindBy(xpath = "//tbody/tr")
	List<WebElement> orderedProducts;
	
//	@FindBy(css = ".subtotal button")
//	WebElement checkoutButton;
	

	public boolean verifyProductInOrderList(String productName) {
		boolean cartProduct = orderedProducts.stream()
				.anyMatch(product -> product.findElement(orderedProductName).getText().equalsIgnoreCase(productName));
		return cartProduct;

	}

//	public PaymentPage clickOnCheckout() {
//		checkoutButton.click();
//		return new PaymentPage(driver);
//
//	}


}

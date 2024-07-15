package groupmart.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import groupmart.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	By individualProduct = By.tagName("b");
	By addToCartButton = By.cssSelector("button:nth-of-type(2)");
	By addToCardMessageDisplay = By.id("toast-container");

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// introducing PageFactory page design
	@FindBy(xpath = "//div[@class='card-body']")
	List<WebElement> allProductsList;

	@FindBy(className = "ng-animating")
	WebElement loadingAnimationLocater;

	public List<WebElement> getAllProducts() {
		waitForAllElementsToAppear(allProductsList, 7);
		return allProductsList;
	}

	public WebElement getProductbyName(String productName) {
		WebElement prod = getAllProducts().stream()
				.filter(product -> product.findElement(individualProduct).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;

	}

	public void addDesiredProductToCart(String productName) {
		WebElement item = getProductbyName(productName);
		item.findElement(addToCartButton).click();

	}

	public void verifyAddToCartMessageDisplay() {
		waitForAnElementToAppear(addToCardMessageDisplay, 7);

	}

	public void verifyLoadingAnimationDisappear() throws InterruptedException {
		waitForAnElementToDisappear(loadingAnimationLocater, 7);

	}

}

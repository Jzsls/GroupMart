package groupmart.pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import groupmart.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;
	By orderedProductName = By.xpath("//td[2]");
	By noOrdersText = By.xpath("//div[contains(text(),'No Orders')]");

	public OrdersPage(WebDriver driver) throws IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//tbody/tr")
	List<WebElement> orderedProducts;

	@FindBy(xpath = "//button[text()='Delete']")
	List<WebElement> deleteButtons;

	@FindBy(css = "[aria-label*='Deleted Successfully']")
	WebElement orderDeletionMessage;

	public boolean verifyProductInOrderList(String productName) {
		boolean cartProduct = orderedProducts.stream()
				.anyMatch(product -> product.findElement(orderedProductName).getText().equalsIgnoreCase(productName));
		return cartProduct;

	}

	public void deleteOrders() throws InterruptedException {
		waitForAllElementsToAppear(deleteButtons, 7);
		if (deleteButtons != null) {

			int index = 0;
			while (index < deleteButtons.size()) {
				try {
					WebElement deleteButton = deleteButtons.get(index);
					deleteButton.click();
					// System.out.println("Delete button clicked successfully");
					// Wait for the page to refresh
					Thread.sleep(1000);
					// Getting the buttons after each successful deletion
					deleteButtons = driver.findElements(By.xpath("//button[text()='Delete']"));
				} catch (StaleElementReferenceException e) {
					System.out.println("Encountered stale element, refetching Delete buttons");
					Thread.sleep(1000);
					deleteButtons = driver.findElements(By.xpath("//button[text()='Delete']"));
				} catch (Exception e) {
					System.out.println("Error clicking delete button: " + e.getMessage());
					index++; // Move to next button if there's an error
				}
			}

		} else {
			System.out.println("No order to delete");
		}

	}

	public String orderDeletionConfirmation() {
		waitForAnElementToAppear(noOrdersText, 7);
		return driver.findElement(noOrdersText).getText();

	}

}

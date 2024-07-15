package groupmart.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import groupmart.pageobjects.MyCart;
import groupmart.pageobjects.OrdersPage;

public class AbstractComponent {
	WebDriver driver;
	@FindBy(css = "[routerlink$='cart']")
	WebElement cartButton;
	@FindBy(css = "[routerlink*='/myorders']")
	WebElement myOrdersButton;

	public AbstractComponent(WebDriver driver) {
	this.driver = driver;

	}

	public void waitForAllElementsToAppear(List<WebElement> webElementList, int timeInSeconds) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(webElementList));
	}

	public void waitForAnElementToAppear(By locator, int timeInSeconds) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForAnElementToDisappear(WebElement webElement, int timeInSeconds) throws InterruptedException {
//		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
//		explicitWait.until(ExpectedConditions.invisibilityOf(webElement));
		Thread.sleep(2000);
	}

	public MyCart jsCartbuttonClick() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", cartButton);
		return new MyCart(driver);
	}//
	
	public OrdersPage jsMyOrderClick() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", myOrdersButton);
		return new OrdersPage(driver);
	}

}

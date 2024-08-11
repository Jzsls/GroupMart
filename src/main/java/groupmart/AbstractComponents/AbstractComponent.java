package groupmart.AbstractComponents;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import groupmart.pageobjects.MyCart;
import groupmart.pageobjects.OrdersPage;

public class AbstractComponent {
	private String envVariablesPath = "//src//test//java//groupmart//data//env.json";
	public JsonNode envJson;
	WebDriver driver;
	@FindBy(css = "[routerlink$='cart']")
	WebElement cartButton;
	@FindBy(css = "[routerlink*='/myorders']")
	WebElement myOrdersButton;

	public AbstractComponent(WebDriver driver) throws IOException {
		this.driver = driver;
		this.envJson = getJsonData(envVariablesPath);

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

	public MyCart jsCartbuttonClick() throws IOException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", cartButton);
		return new MyCart(driver);
	}

	public OrdersPage jsMyOrderClick() throws IOException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", myOrdersButton);
		return new OrdersPage(driver);
	}


	public JsonNode getJsonData(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + filePath),
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(jsonContent);
	}
}
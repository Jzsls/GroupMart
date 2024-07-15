package groupmart.Tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// storing the desired product name in a variable
		String productName = "ZARA COAT 3";
		String desiredCountry = "Germany";
		String succOrderText =  "THANKYOU FOR THE ORDER.";
		// preparing browser driver to interact with browser
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		// 1-Allow, 2-Block, 0-default
		options.setExperimentalOption("prefs", prefs);
		options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
		options.addArguments("disable-infobars");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		// putting implicit wait on global level to avoid any sync wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		// doing login page automation
		//driver.findElement(By.id("userEmail")).sendKeys("abc+1@jmail.com");
		//loginPage.emailField;
		driver.findElement(By.id("userPassword")).sendKeys("Abc123@#");
		driver.findElement(By.id("login")).click();
		// applying implicit wait on elements that show and vanish and also that are
		// loading slow
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(7));
		// getting all the products displayed on page
		explicitWait.until(
				ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//div[@class='card-body']"))));
		List<WebElement> productList = driver.findElements(By.xpath("//div[@class='card-body']"));
//		for (WebElement product : productList) {
//			String productTitle = product.findElement(By.tagName("b")).getText();
//			System.out.println(productTitle);
//			
//		}
		// searching for ZARA COAT 3
		WebElement item = productList.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		// clicking on the desired item once found
		item.findElement(By.xpath("//button[2]")).click();

		// checking if 'Product Added To Cart' message appears
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		// checking if loading graphics disappears
		explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));

		// driver.findElement(By.cssSelector("[routerlink$='cart']")).is
//		explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("[routerlink$='cart']"))));
//		driver.findElement(By.cssSelector("[routerlink$='cart']")).click();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.cssSelector("[routerlink$='cart']"));
		executor.executeScript("arguments[0].click();", element);
		List<WebElement> cartProducts = driver.findElements(By.className("infoWrap"));
		boolean cartProduct = cartProducts.stream()
				.anyMatch(product -> product.findElement(By.tagName("h3")).getText().equalsIgnoreCase(productName));
		Assert.assertTrue(cartProduct);
		driver.findElement(By.cssSelector(".subtotal button")).click();
		WebElement staticDropdown1 = driver.findElement(By.cssSelector("select:first-of-type"));
		Select dropdown1 = new Select(staticDropdown1);
		dropdown1.selectByVisibleText("09");
		WebElement staticDropdown2 = driver.findElement(By.cssSelector("select:last-of-type"));
		Select dropdown2 = new Select(staticDropdown2);
		dropdown2.selectByVisibleText("22");
		driver.findElement(By.xpath("//span[contains(text(),'?')]/parent::div/following-sibling::input"))
				.sendKeys("123");
		driver.findElement(By.xpath("//div[contains(text(),'Name on Card')]/following-sibling::input"))
				.sendKeys("Ana Miller");
		// driver.findElement(By.cssSelector("[fdprocessedid='kb07ic']")).sendKeys("Ana
		// Miller");
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("Ger");
		explicitWait.until(
				ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(".ta-item span"))));
		List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-item span"));
		//System.out.println(countryList.size());
//		for (WebElement country : countryList) {
//			System.out.println("inside for: " + country);
//			System.out.println(country);
//			String countryText = country.findElement(By.xpath("//span[normalize-space()='" + desiredCountry + "']"))
//					.getText();
//			if (country.getText().equalsIgnoreCase(desiredCountry)) {
//				System.out.println(country.getText());
//				country.click();
//				break;
//			}

		//}
		countryList.stream().filter(country -> country.getText().equals(desiredCountry)).limit(1)
				.forEach(country -> country.click());
	   driver.findElement(By.className("action__submit")).click();
	   String expectedSucessOrder = driver.findElement(By.className("hero-primary")).getText().trim();
	   //System.out.println(expectedSucessOrder);
	   Assert.assertTrue(expectedSucessOrder.equals(succOrderText));
	
	}

}

package groupmart.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import groupmart.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

	WebDriver driver;
	String url = "https://rahulshettyacademy.com/client";
	By incorrectLoginErrorText = By.className("toast-message");

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// WebElement emailField = driver.findElement(By.id("userEmail"));
	// introducing PageFactory page design
	@FindBy(id = "userEmail")
	WebElement emailField;

	@FindBy(id = "userPassword")
	WebElement passwordField;

	@FindBy(id = "login")
	WebElement submit;

//	@FindBy(className = "toast-message")
//	WebElement incorrectLoginErrorText;

	public ProductCatalogue appLogin(String email, String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}

	public void goTo() {
		driver.get(url);

	}

	public String getInvalidLoginError() {
		waitForAnElementToAppear(incorrectLoginErrorText, 7);
		String errorText = driver.findElement(incorrectLoginErrorText).getText().trim();
		return errorText;
	}
	
	public String getLoginPageTitle() {
		return driver.getTitle();

	}

}

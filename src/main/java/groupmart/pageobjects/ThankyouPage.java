package groupmart.pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import groupmart.AbstractComponents.AbstractComponent;

public class ThankyouPage extends AbstractComponent {

	WebDriver driver;

	public ThankyouPage(WebDriver driver) throws IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// introducing PageFactory page design
	@FindBy(className = "hero-primary")
	WebElement thankyouMessage;

	public String getThankyouMessage() {
		return thankyouMessage.getText().trim();

	}

}

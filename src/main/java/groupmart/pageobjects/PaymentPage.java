package groupmart.pageobjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.fasterxml.jackson.databind.JsonNode;

import groupmart.AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {

	WebDriver driver;

	public PaymentPage(WebDriver driver) throws IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// introducing PageFactory page design
	@FindBy(css = "select:first-of-type")
	WebElement staticDropdown1;

	@FindBy(css = "select:last-of-type")
	WebElement staticDropdown2;

	@FindBy(xpath = "//span[contains(text(),'?')]/parent::div/following-sibling::input")
	WebElement cvvCodeField;

	@FindBy(xpath = "//div[contains(text(),'Name on Card')]/following-sibling::input")
	WebElement nameOnCardField;

	@FindBy(css = "[placeholder='Select Country']")
	WebElement countrySearchField;

	@FindBy(css = ".ta-item span")
	List<WebElement> searchedCountryList;

	@FindBy(className = "action__submit")
	WebElement placeOrderButton;

	public void fillCardDetails(JsonNode cardDetails) {
		Select dropdown1 = new Select(staticDropdown1);
		dropdown1.selectByVisibleText(cardDetails.path("expiryYear").asText());
		Select dropdown2 = new Select(staticDropdown2);
		dropdown2.selectByVisibleText(cardDetails.path("expiryday").asText());
		cvvCodeField.sendKeys(cardDetails.path("cvvCode").asText());
		nameOnCardField.sendKeys(cardDetails.path("nameOnCard").asText());

	}

	public void selectShippingCountry(String countrySearchText, String desiredCountry) {
		countrySearchField.sendKeys(countrySearchText);
		waitForAllElementsToAppear(searchedCountryList, 7);
		searchedCountryList.stream().filter(country -> country.getText().equals(desiredCountry)).limit(1)
				.forEach(country -> country.click());

	}

	public ThankyouPage clickPlaceOrderButton() throws IOException {
		placeOrderButton.click();
		return new ThankyouPage(driver);
	}

}

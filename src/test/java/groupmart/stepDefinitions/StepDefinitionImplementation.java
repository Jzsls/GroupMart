package groupmart.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import groupmart.TestComponents.BaseTest;
import groupmart.pageobjects.MyCart;
import groupmart.pageobjects.PaymentPage;
import groupmart.pageobjects.ProductCatalogue;
import groupmart.pageobjects.ThankyouPage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest {
	ProductCatalogue productCatalogue;
	MyCart myCart;
	PaymentPage paymentPage;
	ThankyouPage thankyouPage;

	String desiredCountry = "Germany";
	String desiredCountrySearchText = "ger";
	// String succOrderText = "THANKYOU FOR THE ORDER.";
	String[] cardDetails = { "09", "22", "123", "Ana Miller" };
	String loginPageTitle = "Let's Shop";
//	JsonNode envJson = null;
//
//	public StepDefinitionImplementation() {
//		try {
//			envJson = getEnvJsonData();
//		} catch (IOException e) {
//			e.printStackTrace();
//
//		}
//	}

	@Given("The groupmart app is launched")
	public void The_groupmart_app_is_launched() throws IOException {
		launchApp();
	}

	@Given("The user is on the Login webpage")
	public void The_user_is_on_loginPage() throws IOException {
		Assert.assertEquals(loginPage.getLoginPageTitle(), loginPageTitle);
	}

	@Given("^The user logged in with (.+) and (.+)$")
	public void The_user_logged_in_with_email_and_password(String email, String password) throws IOException {
		System.out.println(password);
		productCatalogue = loginPage.appLogin(email, password);
	}

	@When("^The user add the (.+) in the cart$")
	public void The_user_add_the_product_in_the(String productName) throws InterruptedException, IOException {
		productCatalogue.addDesiredProductToCart(productName);
		// checking if 'Product Added To Cart' message appears
		productCatalogue.verifyAddToCartMessageDisplay();
		// checking if loading graphics disappears
		productCatalogue.verifyLoadingAnimationDisappear();
		myCart = productCatalogue.jsCartbuttonClick();

	}

	@And("^with the (.+) in the cart user checkout to the payment page$")
	public void with_the_product_in_the_cart_user_checkout_to_the_payment_page(String productName) throws IOException {
		boolean cartProduct = myCart.verifyProductInCart(productName);
		Assert.assertTrue(cartProduct);
		paymentPage = myCart.clickOnCheckout();

	}

	@And("The user provide the details and click on the place order button")
	public void The_user_provide_the_details_and_click_on_the_place_order_button() throws IOException {
		paymentPage.fillCardDetails(envJson.path("paymentPage").path("cardDetails"));
		paymentPage.selectShippingCountry(desiredCountrySearchText, desiredCountry);
		thankyouPage = paymentPage.clickPlaceOrderButton();
	}

	@Then("{string} text is displayed on the thank you page")
	public void message_displayed_confirmationPage(String string) {
		String expectedSucessOrder = thankyouPage.getThankyouMessage();
		Assert.assertTrue(expectedSucessOrder.equals(string));

	}

	@After("@Regression or @IncorrectEmailAndPassword or @IncorrectEmailAndCorrectPassword")
	@Override
	public void teardown() throws IOException {
		// TODO Auto-generated method stub
		super.teardown();
	}

	@Then("{string} error is displayed on the screen")
	public void error_displayed_loginPage(String string) {
		String actualError = loginPage.getInvalidLoginError();
		Assert.assertEquals(actualError, string);
	}

}

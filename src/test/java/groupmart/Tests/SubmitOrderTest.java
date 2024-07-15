package groupmart.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import groupmart.TestComponents.BaseTest;
import groupmart.pageobjects.MyCart;
import groupmart.pageobjects.OrdersPage;
import groupmart.pageobjects.PaymentPage;
import groupmart.pageobjects.ProductCatalogue;
import groupmart.pageobjects.ThankyouPage;

public class SubmitOrderTest extends BaseTest {
//	String productName = "ZARA COAT 3";
//	String email = "abc+1@jmail.com";
//	String password = "Abc123@#";
	String filePath = "//src//test//java//groupmart//data//PurchaseOrder.json";
//	String sshotFilePath = "//Screenshots//sshoot.png";

	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String> user) throws InterruptedException, IOException {

		String desiredCountry = "Germany";
		String desiredCountrySearchText = "ger";
		String succOrderText = "THANKYOU FOR THE ORDER.";
		String[] cardDetails = { "09", "22", "123", "Ana Miller" };
//		String expiryDateMonth = "09";
//		String expiryDateday = "22";
//		String cvvCode = "123";
//		String nameOnCard = "Ana Miller";

		// doing login page automation
		ProductCatalogue productCatalogue = loginPage.appLogin(user.get("email"), user.get("password"));

		// doing catalogue page automation

		// clicking on the desired product
		productCatalogue.addDesiredProductToCart(user.get("productName"));
		// checking if 'Product Added To Cart' message appears
		productCatalogue.verifyAddToCartMessageDisplay();
		// checking if loading graphics disappears
		productCatalogue.verifyLoadingAnimationDisappear();
		MyCart myCart = productCatalogue.jsCartbuttonClick();

		// doing MyCart page automation
		boolean cartProduct = myCart.verifyProductInCart(user.get("productName"));
		Assert.assertTrue(cartProduct);
		PaymentPage paymentPage = myCart.clickOnCheckout();

		// doing Payment page automation
		paymentPage.fillCardDetails(cardDetails);
		paymentPage.selectShippingCountry(desiredCountrySearchText, desiredCountry);
		ThankyouPage thankyouPage = paymentPage.clickPlaceOrderButton();

		// Confirming successful order placing on Thanks Page
		String expectedSucessOrder = thankyouPage.getThankyouMessage();
		Assert.assertTrue(expectedSucessOrder.equals(succOrderText));

	}

	// String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData", dependsOnMethods = { "submitOrder" })
	public void productInOrderHistoryTest(HashMap<String, String> user) {
		ProductCatalogue productCatalogue = loginPage.appLogin(user.get("email"), user.get("password"));
		OrdersPage ordersPage = productCatalogue.jsMyOrderClick();
		boolean productExists = ordersPage.verifyProductInOrderList(user.get("productName"));
		Assert.assertTrue(productExists);
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(filePath);
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] { { "abc+2@jmail.com", "Angoli1@", "ADIDAS ORIGINAL" },
//				{ "abc+1@jmail.com", "Abc123@#", "ZARA COAT 3" } };
//	}
//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String, String> user1 = new HashMap<String, String>();
//		user1.put("email", "abc+2@jmail.com");
//		user1.put("password", "Angoli1@");
//		user1.put("productName", "ADIDAS ORIGINAL");
//		HashMap<String, String> user2 = new HashMap<String, String>();
//		user2.put("email", "abc+1@jmail.com");
//		user2.put("password", "Abc123@#");
//		user2.put("productName", "ZARA COAT 3");
//		return new Object[][] { { user1 }, { user2 } };
//	}

}

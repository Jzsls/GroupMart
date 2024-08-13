package groupmart.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import groupmart.TestComponents.BaseTest;
import groupmart.pageobjects.ProductCatalogue;

public class LoginRegressionTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "LoginErrorHandling", "LoginRegression" })
	public void incorrectLoginErrorValidation(HashMap<String, String> user) throws InterruptedException, IOException {

		loginPage.appLogin(user.get("invalidEmail"), user.get("invalidPassword"));
		String actualError = loginPage.getInvalidLoginError();
		Assert.assertEquals(actualError, envJson.path("loginPage").path("errorText").asText());

	}

	@Test(dataProvider = "getData", groups = { "LoginErrorHandling", "LoginRegression" })
	public void incorrectEmail(HashMap<String, String> user) throws InterruptedException, IOException {

		loginPage.appLogin(user.get("invalidEmail"), user.get("validPassword"));
		String actualError = loginPage.getInvalidLoginError();
		Assert.assertEquals(actualError, envJson.path("loginPage").path("errorText").asText());

	}

	@Test(dataProvider = "getData", groups = { "LoginErrorHandling", "LoginRegression" })
	public void incorrectPassword(HashMap<String, String> user) throws InterruptedException, IOException {

		loginPage.appLogin(user.get("validEmail"), user.get("invalidPassword"));
		String actualError = loginPage.getInvalidLoginError();
		Assert.assertEquals(actualError, envJson.path("loginPage").path("errorText").asText());

	}

	@Test(dataProvider = "getData", groups = { "LoginErrorHandling", "LoginRegression" })
	public void incompleteEmail(HashMap<String, String> user) throws InterruptedException, IOException {

		loginPage.appLogin(user.get("incompleteEmail"), user.get("validPassword"));
		String actualEmailInvalidityError = loginPage.getEmailInvalidityText();
		Assert.assertTrue(
				actualEmailInvalidityError.contains(envJson.path("loginPage").path("emailInvalidityText").asText()));

	}

	@Test(groups = { "LoginErrorHandling", "LoginRegression" })
	public void emptyEmailandPasswordField() throws InterruptedException, IOException {
		loginPage.appLogin("", "");
		String actualEmptyEmailError = loginPage.getEmptyEmailError();
		String actualEmptyPasswordError = loginPage.getEmptyPasswordError();
		Assert.assertTrue(
				actualEmptyEmailError.contains(envJson.path("loginPage").path("emptyEmailFieldError").asText()));
		Assert.assertTrue(
				actualEmptyPasswordError.contains(envJson.path("loginPage").path("emptyPasswordFieldError").asText()));

	}

	@Test(dataProvider = "getData", groups = { "LoginSuccess", "LoginRegression" })
	public void ValidLoginCredentials(HashMap<String, String> user) throws InterruptedException, IOException {

		ProductCatalogue productCatalogue = loginPage.appLogin(user.get("validEmail"), user.get("validPassword"));
		String actualProductCatalogueUrl = productCatalogue.getProductCatalogueUrlPath();
		Assert.assertTrue(
				actualProductCatalogueUrl.contains(envJson.path("productCataloguePage").path("urlPath").asText()));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getUserJsonData(USER_PROFILES_PATH);
		return new Object[][] { { data.get(2) } };
	}
}

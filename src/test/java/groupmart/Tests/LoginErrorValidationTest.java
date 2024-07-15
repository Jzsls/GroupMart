package groupmart.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import groupmart.TestComponents.BaseTest;

public class LoginErrorValidationTest extends BaseTest {

	@Test(groups = {"LoginErrorHandling"})
	public void incorrectLoginErrorValidation() throws InterruptedException, IOException {

		String email = "adc@jmail.com";
		String password = "Ayu223@#";
		String errorText = "Incorrect email or password.";
		loginPage.appLogin(email, password);
		String actualError = loginPage.getInvalidLoginError();
		Assert.assertEquals(actualError, errorText);

	}
	
	@Test(groups = {"LoginErrorHandling"})
	public void incorrectEmail() throws InterruptedException, IOException {

		String email = "abc@jmail.com";
		String password = "Abc123@#";
		String errorText = "Incorrect email or password.";
		loginPage.appLogin(email, password);
		String actualError = loginPage.getInvalidLoginError();
		Assert.assertEquals(actualError, errorText);

	}

}

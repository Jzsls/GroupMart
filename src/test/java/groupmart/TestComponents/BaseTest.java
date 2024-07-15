package groupmart.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import groupmart.pageobjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {

		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//groupmart//resources//GlobalData.properties");
		properties.load(fileInputStream);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: properties.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			// 1-Allow, 2-Block, 0-default
			options.setExperimentalOption("prefs", prefs);
			options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
			options.addArguments("disable-infobars");
			if (browserName.contains("headless")) {
				options.addArguments("--headless=new");
			}

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

		}

		else if (browserName.contains("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			if (browserName.contains("headless")) {
				options.addArguments("--headless=new");
			}
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(options);

		}

		else if (browserName.contains("edge")) {
			EdgeOptions options = new EdgeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("--headless=new");
			}
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);

		}

		driver.manage().window().maximize();
		// putting implicit wait on global level to avoid any sync problems
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().setSize(new Dimension(1440, 900));
		return driver;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "//Screenshots/" + testCaseName + ".png"));
		return System.getProperty("user.dir") + "//Screenshots/" + testCaseName + ".png";

	}

	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + filePath),
				StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	@BeforeMethod(alwaysRun = true)
	public void launchApp() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goTo();
		// return loginPage;
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() throws IOException {
		driver.quit();
	}

}

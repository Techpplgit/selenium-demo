package base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;

public class testBase {
	// public static IOSDriver<MobileElement> driver;

	public static WebDriver driver;

	@SuppressWarnings("deprecation")
	public void launchBrowser() throws Exception {
		try {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/resources/chromedriver");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			driver = new ChromeDriver(capabilities);

			driver.manage().window().maximize();
			Thread.sleep(2500);

			Thread.sleep(1500);

			driver.navigate().refresh();

		} catch (

		Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
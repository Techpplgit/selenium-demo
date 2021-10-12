package base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

public class testBase {
	// public static IOSDriver<MobileElement> driver;

	public static WebDriver driver;

	public void launchBrowser() throws Exception {
		try {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/resources/chromedriver");

//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--incognito");
//			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			driver = new ChromeDriver();

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			Thread.sleep(2500);

			Thread.sleep(1500);

		} catch (

		Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
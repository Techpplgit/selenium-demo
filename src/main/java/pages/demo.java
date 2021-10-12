package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.List;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import base.ExtentReportTest;

public class demo extends ExtentReportTest {

	String webURL = "https://ui.cogmento.com/";
	String userName = "test0123@mailinator.com";
	String password = "Test@123";
	String filePath = "/Users/tapan.khimani/Desktop/sample.pdf";
	String addNewContact = "https://ui.cogmento.com/contacts/new/";
	String saltStr = "";
	String contacts = "https://ui.cogmento.com/contacts";
	String addNewDoc = "https://ui.cogmento.com/documents/new";

	public void assertURLAndLogin() throws Exception {
		Thread.sleep(1500);

		driver.navigate().to(webURL);
		Thread.sleep(1500);

		String currentURL = driver.getCurrentUrl();
		Thread.sleep(1500);

		assertEquals(webURL, currentURL);

		Thread.sleep(1500);

		testStep("PASS", "Successfully Performed Assertion");
		testStep("INFO", "Actual URL : " + webURL + " Expected URL : " + currentURL);

		Thread.sleep(1500);

		WebElement userId = driver.findElement(By.cssSelector("input[placeholder='E-mail address']"));
		Thread.sleep(1500);
		userId.click();
		userId.sendKeys(userName);
		Thread.sleep(1500);

		WebElement pwd = driver.findElement(By.cssSelector("input[placeholder='Password']"));
		Thread.sleep(1500);
		pwd.click();
		pwd.sendKeys(password);
		Thread.sleep(1500);

		WebElement loginBtn = driver.findElement(By.cssSelector(".ui.fluid.large.blue.submit.button"));
		Thread.sleep(1500);
		loginBtn.click();

		Thread.sleep(2500);

		testStep("PASS", "Successfully Logged In");

	}

	public String randomString() throws Exception {

		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 7) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		saltStr = salt.toString();
		return saltStr;

	}

	public void addContact() throws Exception {
		Thread.sleep(1500);

		try {

			randomString();
			Thread.sleep(1500);

//			driver.navigate().refresh();
//			Thread.sleep(4500);

			WebElement firstName = driver.findElement(By.cssSelector("input[name='first_name']"));
			Thread.sleep(1500);
			firstName.sendKeys(saltStr);
			Thread.sleep(1500);

			randomString();
			Thread.sleep(1500);

			WebElement lastName = driver.findElement(By.cssSelector("input[name='last_name']"));
			Thread.sleep(1500);
			lastName.sendKeys(saltStr);
			Thread.sleep(1500);

			WebElement saveContact = driver.findElement(By.xpath("//button[normalize-space()='Save']"));
			Thread.sleep(1500);
			saveContact.click();
			Thread.sleep(1500);

		}

		catch (Exception e) {
			// TODO: handle exception

		}
	}

	public void windowHandlesAddContact() throws Exception {
		Thread.sleep(1500);

		((JavascriptExecutor) driver).executeScript("window.open()");
		Thread.sleep(1500);

//		Set<String> windowHandles = driver.getWindowHandles();
//		java.util.List<String> windowHandlesList = new ArrayList<>(windowHandles);

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1500);

		System.out.println("Current Window Handle: " + driver.getWindowHandle() + "\n");

		Thread.sleep(1500);

		for (int i = 1; i < 5; i++) {
			Thread.sleep(2500);
			driver.navigate().to(addNewContact);
			Thread.sleep(1500);

//			driver.navigate().refresh();
//			Thread.sleep(1500);

			addContact();
			Thread.sleep(1500);

			testStep("PASS", "Successfully Added Contact for : " + i);
			Thread.sleep(1500);

		}

	}

	public void readDynamicTableValues() throws Exception {
		Thread.sleep(1500);
		try {
			driver.navigate().to(contacts);
			Thread.sleep(1500);

			for (int i = 1; i < 5; i++) {
				String contactValue = "tbody tr:nth-child(" + i + ") td:nth-child(2) a:nth-child(1)";
				Thread.sleep(1500);

				String value = driver.findElement(By.cssSelector(contactValue)).getText();
				Thread.sleep(1500);

				System.out
						.println("Value of Contact is : " + driver.findElement(By.cssSelector(contactValue)).getText());
				Thread.sleep(1500);

				testStep("INFO", "Successfully Read Value from UI Using Dynamic Locators at : " + i + "" + value);
				Thread.sleep(1500);

			}
			testStep("PASS", "Successfully Read Value from UI Using Dynamic Locators");

			Thread.sleep(1500);

		} catch (Exception e) {
			// e.printStackTrace();
			// TODO: handle exception
		}
	}

	public void addDocument() throws Exception {
		Thread.sleep(1500);
		try {

			WebElement documentbtn = driver.findElement(By.cssSelector("div:nth-child(9)"));
			Thread.sleep(1500);

			Actions builder = new Actions(driver);
			Action mouseOverHome = builder.moveToElement(documentbtn).click().build();
			mouseOverHome.perform();

			Thread.sleep(1500);

			WebElement createBtn = driver.findElement(By.cssSelector(
					"body > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > a:nth-child(2) > button:nth-child(1)"));
			Thread.sleep(1500);
			createBtn.click();
			Thread.sleep(1500);

			for (int i = 1; i < 5; i++) {

				randomString();
				Thread.sleep(1500);

				WebElement folderName = driver.findElement(By.cssSelector("div[name='folder'] input[type='text']"));
				Thread.sleep(1500);
				folderName.click();
				Thread.sleep(1500);
				folderName.sendKeys(saltStr);

				WebElement uploadFile = driver.findElement(By.cssSelector("input[name='fileField']"));
				Thread.sleep(1500);

				File file = null;
				file = new File(filePath);
				uploadFile.sendKeys(file.getAbsolutePath());

				Thread.sleep(1500);

				WebElement saveBtn = driver.findElement(By.xpath("//button[normalize-space()='Save']"));
				Thread.sleep(1500);
				saveBtn.click();

				driver.navigate().to(addNewDoc);
				Thread.sleep(2500);

				testStep("PASS", "Successfully Added Document with File Upload");
				Thread.sleep(2500);

			}

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
		}
	}

	public void checkLinks() throws Exception {
		Thread.sleep(1500);

		try {

			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(0));
			Thread.sleep(2000);

			for (int i = 1; i < 5; i++) {
				Thread.sleep(1500);

				String aTag = "/html/body/div[1]/div/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div/div[" + i
						+ "]/div/div[2]/div[2]/div[1]/span/a";

				Thread.sleep(1500);

				driver.findElement(By.xpath(aTag)).click();
				Thread.sleep(4500);

				driver.navigate().back();
				Thread.sleep(2500);

				testStep("PASS", "Successfully Checked Links");
				Thread.sleep(2500);

			}

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
		}
	}

	public void uploadFile() throws Exception {

		driver.navigate().to(webURL);
		Thread.sleep(1500);

		WebElement uploadFile = driver.findElement(By.cssSelector("input#file_upload"));
		Thread.sleep(1500);
		// uploadFile.click();
		Thread.sleep(1500);

		File file = null;
		file = new File(filePath);
		System.out.println(filePath);
		System.out.println(file.getAbsolutePath());
		uploadFile.sendKeys(file.getAbsolutePath());

		Thread.sleep(3000);

		testStep("PASS", "File Uploaded Successfully");

	}

	public void downloadSampleResume() throws Exception {
		Thread.sleep(1500);

		// driver.navigate().to(downloadSampleResume);
		Thread.sleep(1500);

		WebElement downloadBtn = driver
				.findElement(By.cssSelector("a[class='fr mb10 sampleDwndLink'] span[class='font_medium fl']"));
		Thread.sleep(1500);
		downloadBtn.click();
		Thread.sleep(1500);

		WebElement emailId = driver.findElement(By.cssSelector("#bd_email"));
		Thread.sleep(1500);
		emailId.click();
		emailId.sendKeys("test@mailiantor.com");
		Thread.sleep(1500);

		WebElement contactNb = driver.findElement(By.cssSelector("#bd_mobile"));
		Thread.sleep(1500);
		contactNb.click();
		contactNb.sendKeys("9409664787");
		Thread.sleep(1500);

		WebElement years = driver.findElement(By.cssSelector("#bd_adv_workExp_year"));
		Thread.sleep(1500);
		years.click();
		Thread.sleep(1500);

		WebElement yearsOptn = driver.findElement(By.cssSelector("div[id='dd_bd_adv_workExp_year'] li[id=' 1']"));
		Thread.sleep(1500);
		yearsOptn.click();
		Thread.sleep(1500);

		WebElement submit = driver.findElement(By.cssSelector("div[class='rowR'] button[value='Login']"));
		Thread.sleep(1500);
		submit.click();
		Thread.sleep(1500);

		WebElement skip = driver.findElement(By
				.cssSelector("#shortRegistration > div.ltCntnt > form > section.secndScr.dspN > div.row.disclmr > a"));
		Thread.sleep(1500);
		skip.click();
		Thread.sleep(1500);

		testStep("PASS", "Successfully Downloaded Sample Resume");

	}

	public void windowHandles() throws Exception {
		// 1) Navigate to URL
		driver.navigate().to("http://www.w3schools.com/tags/tryit.asp?filename=tryhtml_link_target");
		driver.manage().window().maximize();

		Thread.sleep(1500);

		// 2) Get the current window's handle and write to the console window. It must
		// be first window handle.
		System.out.println("Current Window Handle: " + driver.getWindowHandle() + "\n");
		// Switch to iframeResult iframe because all elements located in this iframe
		driver.switchTo().frame("iframeResult");

		Thread.sleep(1500);

		// 3) Locate the link and click it
		WebElement visitLink = driver.findElement(By.linkText("Visit W3Schools.com!"));
		visitLink.click();

		Thread.sleep(1500);

		// 4) Get all window handles and hold them in a list.
		Set<String> windowHandles = driver.getWindowHandles();
		java.util.List<String> windowHandlesList = new ArrayList<>(windowHandles);
		// Set to List Conversion

		Thread.sleep(1500);

		// 5) Write to total window handle number to the console.
		System.out.println("Total window number: " + windowHandlesList.size() + "\n");

		Thread.sleep(1500);

		// 6) Switch to second window
		driver.switchTo().window(windowHandlesList.get(1));

		Thread.sleep(1500);

		// 7) Get the current window's handle and write to the console window. It must
		// be second window handle.
		System.out.println("Current Window Handle: " + driver.getWindowHandle() + "\n");

		Thread.sleep(1500);

		// 8) Check the upper left side text is "THE WORLD'S LARGEST WEB DEVELOPER SITE"
		// in second window
		WebElement expectedText = driver
				.findElement(By.cssSelector("#main > div:nth-child(1) > div > div.w3-col.l6.w3-center > p"));
		assertEquals("The language for building web pages", expectedText.getText());

		Thread.sleep(1500);

		// 9) Go back (Switch) to first window
		driver.switchTo().window(windowHandlesList.get(0));
		// 10) Get the current window's handle and write to the console window. It must
		// be first window handle.
		System.out.println("Current Window Handle: " + driver.getWindowHandle() + "\n");

		Thread.sleep(1500);

		testStep("PASS", "Successfully Handles Tabs");

	}

}
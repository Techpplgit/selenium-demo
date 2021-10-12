package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.testBase;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class testUtil extends testBase {

	// public String sheetname = "Sheet1";
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	public static final String dataProviderPath = (System.getProperty("user.dir")
			+ "/src/main/java/dataProvider/BVT - Android.xlsx");
	public static org.apache.poi.ss.usermodel.Workbook book;
	public static org.apache.poi.ss.usermodel.Sheet sheet;
	public static XSSFSheet ExcelSheet;

	// Read Data From File
	public static Object[] getTestData(String sheetName) throws InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(dataProviderPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {

			book = WorkbookFactory.create(file);

		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		Object[] requiredData = new Object[2];
		int lastNRowNumber = sheet.getLastRowNum();
		// int lastCellNumber = sheet.getRow(0).getLastCellNum();

		for (int i = 1; i < lastNRowNumber; i++) {
			Row row = sheet.getRow(i);
			if (row.getCell(2).toString().equalsIgnoreCase("yes")) {
				requiredData[0] = row.getCell(0).toString(); // device name
				requiredData[1] = row.getCell(1).toString(); // device ID
				break;
			}
		}
		return requiredData;

	}

	// Read Data From File
	public static Object[][] Data(String sheetName) throws InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(dataProviderPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {

			book = WorkbookFactory.create(file);

		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		int lastNRowNumber = sheet.getLastRowNum();
		// int lastCellNumber = sheet.getRow(0).getLastCellNum();

		for (int i = 0; i < lastNRowNumber; i++) {
			for (int k = 0; k < sheet.getRow(i).getLastCellNum(); k++) {

				try {
					if (!sheet.getRow(i + 1).getCell(k).equals("")) {
						data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					}

					System.out.println(data[i][k]);
				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return data;

	}

	// Read Data From File
//	public static List<DeviceInfo> getTestData2(String sheetName) throws InvalidFormatException {
//		FileInputStream file = null;
//		try {
//			file = new FileInputStream(dataProviderPath);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		try {
//
//			book = WorkbookFactory.create(file);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		sheet = book.getSheet(sheetName);
//
//		ArrayList<DeviceInfo> devices = new ArrayList<DeviceInfo>();
//
//		int lastNRowNumber = sheet.getLastRowNum();
//		// int lastCellNumber = sheet.getRow(0).getLastCellNum();
//
//		for (int i = 1; i < lastNRowNumber; i++) {
//			Row row = sheet.getRow(i);
//			String name = row.getCell(0).toString();
//			String id = row.getCell(1).toString();
//			boolean available = row.getCell(2).toString().equalsIgnoreCase("yes") ? true : false;
//			DeviceInfo info = new DeviceInfo(name, id, available);
//			devices.add(info);
//		}
//		List<DeviceInfo> filteredDevices = devices.stream().filter(new Predicate<DeviceInfo>() {
//			@Override
//			public boolean test(DeviceInfo device) {
//				return device.isAvailable;
//			}
//		}).collect(Collectors.toList());
//		return filteredDevices;
//	}

	// Read Data From File
	public static Object[][] getData(String sheetName) throws InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(dataProviderPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {

			book = WorkbookFactory.create(file);

		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		int lastNRowNumber = sheet.getLastRowNum();
		// int lastCellNumber = sheet.getRow(0).getLastCellNum();

		for (int i = 0; i < lastNRowNumber; i++) {
			for (int k = 0; k < sheet.getRow(i).getLastCellNum(); k++) {

				try {
					if (!sheet.getRow(i + 1).getCell(k).equals("")) {
						data[i][k] = sheet.getRow(i + 1).getCell(k).toString();

					}

					System.out.println(data[i][k]);
				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return data;

	}

	public static String numberAsString;

	public void nextclick() {
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Next:\"]")).click();

	}

	public void generateNumber() throws Exception {
		long randomNumber = (long) (Math.random() * 100000 + 3333300000L);
		System.out.println(randomNumber);
		numberAsString = Long.toString(randomNumber);

	}

	public void permission() {

		// MobileElement setpermission =
		// driver.findElement(MobileBy.AccessibilityId("SET PERMISSION"));
		//
		// if (setpermission.isDisplayed() == true) {
		// setpermission.click();
		// driver.findElement(MobileBy.AccessibilityId("Always Allow")).click();
		//
		// }

		if (!driver.findElements(MobileBy.AccessibilityId("SET PERMISSION")).isEmpty()) {
			System.out.println("Permission pop up display");
			// THEN CLICK ON THE SUBMIT BUTTON
			driver.findElement(MobileBy.AccessibilityId("SET PERMISSION")).click();
			driver.findElement(MobileBy.AccessibilityId("Always Allow")).click();

		} else {
			System.out.println("Permission pop up no display");
		}
	}

	public static void waittillElementvisible(AndroidDriver<AndroidElement> driver, By element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public static void waittillElementclickable(AndroidDriver<AndroidElement> driver, By element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waittillElementvisible(WebDriver driver, By element) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	
	
	
	
	public void sendEmail() throws InterruptedException {
		

		Thread.sleep(5000);
		// Create object of Property file
		Properties props = new Properties();

		// this will set host of server- you can change based on your
		// requirement
		props.put("mail.smtp.host", "smtp.gmail.com");

		// set the port of socket factory
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "465");

		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,

				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

						return new PasswordAuthentication("reports.eazdine@gmail.com", "EazDine@123");

					}

				});

		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress("reports.eazdine@gmail.com"));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("tkhimani@eazdine.com"));

			// Add the subject link
			message.setSubject("Report Test Run");

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();

			// Set the body of email
			messageBodyPart1.setText(
					"Please Find The Attached Report For The Last Run . Kindly Download The Attached Report And Then Open In Any Browser .");

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename = System.getProperty("user.dir") + "/extentreport.html";

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName("Test Automation Report.html");

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

			System.out.println("=====Email Sent=====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}
	}

}

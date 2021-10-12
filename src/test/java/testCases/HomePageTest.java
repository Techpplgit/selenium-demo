package testCases;

import org.testng.annotations.Test;
import base.ExtentReportTest;
import pages.demo;
import util.MyScreenRecorder;
import util.testUtil;

public class HomePageTest extends ExtentReportTest {

	demo demoObj = new demo();
	testUtil util = new testUtil();
	ExtentReportTest extentReports = new ExtentReportTest();

	@Test(priority = 1)
	public void uploadFile() throws Exception {
		MyScreenRecorder.startRecording("Sanity");
		launchBrowser();
		Thread.sleep(1500);

		demoObj.assertURLAndLogin();
		Thread.sleep(1500);

		demoObj.windowHandlesAddContact();
		Thread.sleep(1500);

		demoObj.readDynamicTableValues();
		Thread.sleep(1500);

		demoObj.addDocument();
		Thread.sleep(1500);

		demoObj.checkLinks();
		Thread.sleep(1500);

		MyScreenRecorder.stopRecording();

	}

}

package testCases;

import org.testng.annotations.Test;
import base.ExtentReportTest;
import pages.demo;
import util.MyScreenRecorder;
import util.testUtil;

public class HomePageTest extends ExtentReportTest {

	demo uploadObject = new demo();
	testUtil util = new testUtil();
	ExtentReportTest extentReports = new ExtentReportTest();

	@Test(priority = 1)
	public void uploadFile() throws Exception {
		MyScreenRecorder.startRecording("TC1");
		launchBrowser();
		Thread.sleep(1500);

		uploadObject.uploadFile();
		Thread.sleep(1500);
		MyScreenRecorder.stopRecording();

	}

	@Test(priority = 2)
	public void downloadSampleResume() throws Exception {
		MyScreenRecorder.startRecording("TC2");

		uploadObject.downloadSampleResume();
		Thread.sleep(1500);
		MyScreenRecorder.stopRecording();

	}

	@Test(priority = 3)
	public void handleFrames() throws Exception {

		MyScreenRecorder.startRecording("TC3");

		uploadObject.windowHandles();
		Thread.sleep(1500);

		MyScreenRecorder.stopRecording();

	}
}

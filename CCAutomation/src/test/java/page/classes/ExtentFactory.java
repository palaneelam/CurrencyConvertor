package page.classes;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {

	public static ExtentReports getInstance() {
		ExtentReports extent;
		String Path = "C:\\Users\\neela\\eclipse-workspace\\CCAutomation\\report-demo.html";
		extent = new ExtentReports(Path, false);
		extent
	     .addSystemInfo("Selenium Version", "2.46")
	     .addSystemInfo("Platform", "Windows");

		return extent;
	}
}
package hcyclewala.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReprterNG {
    public static ExtentReports getReportObject(){
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter report = new ExtentSparkReporter(path);
        report.config().setReportName("Web Automation Result");
        report.config().setDocumentTitle("Test Result");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(report);
        extent.setSystemInfo("Tester", "Husain Cyclewala");
        return extent;
    }

}

package com.hclTech.login;

import com.aventstack.extentreports. ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
public class ExtentManager {
 
private static ExtentReports extent;
 
public static ExtentReports getReportObject() {
 
if (extent == null) {
 
String path = System.getProperty("C:\\Users\\anuku.vamsi\\eclipse-workspace\\login\\report"+"");
 
ExtentSparkReporter reporter = new ExtentSparkReporter (path);
 
reporter.config().setReportName("Flipkart Automation Report");
 
reporter.config().setDocumentTitle("Test Results");
 
extent = new ExtentReports();
 
extent.attachReporter (reporter);
 
extent.setSystemInfo("Tester", "Vamsi");
 
}
 
return extent;
 
}
 
}

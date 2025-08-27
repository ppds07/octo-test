package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestNGReportScraper {

    public static void main(String[] args) {
        System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");

        WebDriver driver = new EdgeDriver();

        List<String[]> testSummaryData = new ArrayList<>();
        List<String[]> methodDetailsData = new ArrayList<>();

        try {
            File reportFile = new File("test-output/emailable-report.html");
            if (!reportFile.exists()) {
                System.err.println("Report file not found: " + reportFile.getAbsolutePath());
                return;
            }

            String reportUrl = reportFile.toURI().toString();
            driver.get(reportUrl);

            System.out.println("=== Test Summary ===");
            WebElement summaryTable = driver.findElements(By.tagName("table")).get(0);
            List<WebElement> rows = summaryTable.findElements(By.tagName("tr"));

            for (WebElement row : rows) {
                List<WebElement> cols = row.findElements(By.tagName("td"));
                if (cols.size() >= 6) {
                    String testName = cols.get(0).getText();
                    String passed = cols.get(1).getText();
                    String skipped = cols.get(2).getText();
                    String retried = cols.get(3).getText();
                    String failed = cols.get(4).getText();
                    String time = cols.get(5).getText();

                    System.out.printf("Test: %s | Passed: %s | Skipped: %s | Retried: %s | Failed: %s | Time: %s\n",
                            testName, passed, skipped, retried, failed, time);

                    testSummaryData.add(new String[]{testName, passed, skipped, retried, failed, time});
                }
            }

            System.out.println("\n=== Method Details ===");
            WebElement detailTable = driver.findElement(By.id("summary"));
            List<WebElement> detailRows = detailTable.findElements(By.tagName("tr"));

            for (WebElement row : detailRows) {
                List<WebElement> cols = row.findElements(By.tagName("td"));
                if (cols.size() >= 4) {
                    String className = cols.get(0).getText();
                    String methodName = cols.get(1).getText();
                    String startTime = cols.get(2).getText();
                    String duration = cols.get(3).getText();

                    System.out.printf("Class: %s | Method: %s | Start: %s | Duration: %s ms\n",
                            className, methodName, startTime, duration);

                    methodDetailsData.add(new String[]{className, methodName, startTime, duration});
                }
            }

            
            exportToCSV(testSummaryData, methodDetailsData);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void exportToCSV(List<String[]> summary, List<String[]> details) {
        try {
            File reportsDir = new File("Reports/CSV");
            if (!reportsDir.exists()) {
                reportsDir.mkdirs();
            }

            
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            File csvFile = new File(reportsDir, "test_report_" + timestamp + ".csv");

            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
                writer.println("Test Summary");
                writer.println("Test Name,# Passed,# Skipped,# Retried,# Failed,Time (ms)");
                for (String[] row : summary) {
                    writer.println(String.join(",", row));
                }

                writer.println();
                writer.println("Method Details");
                writer.println("Class Name,Method Name,Start Time,Duration (ms)");
                for (String[] row : details) {
                    writer.println(String.join(",", row));
                }

                System.out.println("CSV report generated at: " + csvFile.getAbsolutePath());
            }

        } catch (Exception e) {
            System.err.println("Failed to write CSV: " + e.getMessage());
        }
    }
}

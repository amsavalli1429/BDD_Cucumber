package org_UI;

import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Scenario;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {
    public static WebDriver driver;

    @BeforeAll
    public static void setUp() {

        driver = base.initializeDriver();
    }

    @AfterAll
    public static void tearDown() {

        base.quitDriver();
    }



    @AfterStep
    public void takeScreenshotAfterStep(Scenario scenario) {
        if (driver == null) return;

        try {
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            scenario.attach(base64Screenshot, "image/png", "Step Screenshot");

            // 2. Also save screenshot as a PNG file locally on disk
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String screenshotsDir = "src/test/resources/target/screenshots/";
            File dir = new File(screenshotsDir);
            if (!dir.exists()) dir.mkdirs();

            // Clean scenario name to avoid illegal characters in filename
            String safeScenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");

            // Add timestamp for unique filenames
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());

            // Build full path for the screenshot file
            String screenshotPath = screenshotsDir + safeScenarioName + "_" + timestamp + ".png";

            // Copy the screenshot file to your screenshots folder
            Files.copy(srcFile.toPath(), Paths.get(screenshotPath));

            System.out.println("Saved screenshot to: " + screenshotPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @AfterSuite
    public void renameReportFolder() {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File reportDir = new File("src/test/resources/cucumberreport/cucumber-reports");
            if (reportDir.exists()) {
                File newDir = new File("src/test/resources/cucumberreport/cucumber-reports_" + timestamp);
                boolean renamed = reportDir.renameTo(newDir);
                if (renamed) {
                    System.out.println("Report folder renamed to: " + newDir.getAbsolutePath());
                } else {
                    System.out.println("Failed to rename report folder");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

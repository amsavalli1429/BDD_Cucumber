package org_UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.time.Duration;

public class base {
    public static WebDriver driver;

    public static WebDriver initializeDriver() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static final String SCREENSHOTS_DIR = "src/test/resources/target/screenshots/";

    public static void takeScreenshot(WebDriver driver, String name) {
        try {
            // Create directory if not exists
            File dir = new File(SCREENSHOTS_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }


            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String safeName = name.replaceAll("[^a-zA-Z0-9]", "_");
            String filePath = SCREENSHOTS_DIR + safeName + "_" + timestamp + ".png";
            Files.copy(srcFile.toPath(), Paths.get(filePath));

            System.out.println("Screenshot saved: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cleanReportDirectory(String path) {
        File dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                file.delete();
            }
        }
    }
}

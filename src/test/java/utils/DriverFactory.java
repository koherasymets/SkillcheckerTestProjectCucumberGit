package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverFactory {

    public static WebDriver get() {
        String browser = ConfigurationReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigurationReader.get("headless"));
        boolean maximize = Boolean.parseBoolean(ConfigurationReader.get("maximize"));

        WebDriver driver;

        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless=new");
            }

            driver = new ChromeDriver(options);

            if (maximize) {
                driver.manage().window().maximize();
            }
        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        return driver;
    }

    public static WebDriverWait getWait(WebDriver driver) {
        long timeout = Long.parseLong(ConfigurationReader.get("timeout"));
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }
}
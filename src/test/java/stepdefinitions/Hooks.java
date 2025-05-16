package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.ConfigurationReader;
import utils.DriverFactory;
import utils.TestContext;

public class Hooks {

    public static TestContext context;

    @Before
    public void setUp() {
        context = new TestContext();

        context.driver = DriverFactory.get();
        context.wait = DriverFactory.getWait(context.driver);
        context.actions = new org.openqa.selenium.interactions.Actions(context.driver);
        context.js = (org.openqa.selenium.JavascriptExecutor) context.driver;

        String url = ConfigurationReader.get("url");
        context.driver.get(url);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) context.driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot on failure");
            } catch (Exception e) {
                System.out.println("Error while taking screenshot: " + e.getMessage());
            }
        }

        if (context.driver != null) {
            context.driver.quit();
        }
    }
}
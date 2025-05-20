package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigurationReader;
import utils.TestContext;

import java.time.Duration;

public class SkillMainPage {

    private final TestContext context;
    private final WebDriverWait wait;

    public SkillMainPage(TestContext context) {
        this.context = context;
        PageFactory.initElements(context.driver, this);
        this.wait = new WebDriverWait(context.driver, Duration.ofSeconds(10));
    }

    @FindBy(name = "email") // был "username"
    public WebElement usernameInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    public WebElement loginButton;

    public DashboardPage login() {
        return login(
                ConfigurationReader.get("userName"),
                ConfigurationReader.get("userPassword")
        );
    }

    public DashboardPage login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new DashboardPage(context);
    }

    public void loginWithoutRedirect(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void open() {
        context.driver.get(ConfigurationReader.get("url"));
    }
}
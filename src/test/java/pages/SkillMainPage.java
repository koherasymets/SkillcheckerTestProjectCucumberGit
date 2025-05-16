package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigurationReader;
import utils.TestContext;

public class SkillMainPage {

    private final TestContext context;

    public SkillMainPage(TestContext context) {
        this.context = context;
        PageFactory.initElements(context.driver, this);
    }

    @FindBy(name = "username")
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
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new DashboardPage(context);
    }
    public void loginWithoutRedirect(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
    public void open() {
        context.driver.get(ConfigurationReader.get("url"));
    }
}
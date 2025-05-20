package stepdefinitions;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import pages.SkillMainPage;
import utils.TestContext;

import static org.junit.Assert.assertTrue;

@Epic("Authentication")
@Feature("Login")
@Owner("Konstantin")
@Story("Negative login scenarios with invalid credentials")
@Severity(SeverityLevel.CRITICAL)
public class LoginNegativeSteps {

    TestContext context = Hooks.getContext();
    SkillMainPage skillMainPage = new SkillMainPage(context);

    @When("I enter username {string} and password {string}")
    @Step("Enter username: {0}, password: {1}")
    public void enter_username_and_password(String username, String password) {
        skillMainPage.loginWithoutRedirect(username, password);
    }

    @Then("I should see the error message {string}")
    @Step("Check for error message: {0}")
    public void should_see_error_message(String expectedText) {
        boolean messageFound = false;

        try {
            WebElement toast = context.wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@role='status']"))
            );
            messageFound = toast.getText().contains(expectedText);
        } catch (Exception e) {
            try {
                WebElement inputError = context.driver.findElement(
                        By.xpath("//p[contains(@class, 'text-destructive') and contains(text(), '" + expectedText + "')]")
                );
                messageFound = inputError.isDisplayed();
            } catch (Exception ignored) {
                messageFound = false;
            }
        }

        assertTrue("Expected error message not found: " + expectedText, messageFound);
    }
}
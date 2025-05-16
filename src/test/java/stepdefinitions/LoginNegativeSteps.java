package stepdefinitions;

import io.cucumber.java.en.*;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import pages.SkillMainPage;
import utils.TestContext;

import static org.junit.Assert.assertTrue;

public class LoginNegativeSteps {

    SkillMainPage skillMainPage = new SkillMainPage(Hooks.context);
    TestContext context = Hooks.context;

    @When("I enter username {string} and password {string}")
    @Step("Enter username {0} and password {1}")
    public void enter_username_and_password(String username, String password) {
        skillMainPage.loginWithoutRedirect(username, password);
    }

    @Then("I should see the error message {string}")
    @Step("Check error message: {0}")
    public void should_see_error_message(String expectedText) {
        boolean found = false;
        try {
            WebElement toast = context.wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//li[@role='status']"))
            );
            String toastText = toast.getText();
            System.out.println("TOAST TEXT: " + toastText);
            found = toastText.contains(expectedText);
        } catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
            try {
                WebElement inputError = context.driver.findElement(
                        By.xpath("//p[contains(@class, 'text-destructive') and contains(text(), '" + expectedText + "')]")
                );
                found = inputError.isDisplayed();
            } catch (org.openqa.selenium.NoSuchElementException ex) {
                found = false;
            }
        }
        assertTrue("Could not find the error message: " + expectedText, found);
    }
}
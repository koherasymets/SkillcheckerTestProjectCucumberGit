package stepdefinitions;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.DashboardPage;
import pages.SkillMainPage;
import utils.TestContext;

import static org.junit.Assert.assertTrue;

@Epic("Authentication")
@Feature("Login")
@Owner("Konstantin")
@Story("Positive login scenarios")
@Severity(SeverityLevel.BLOCKER)
public class LoginSteps {

    TestContext context = Hooks.getContext();
    SkillMainPage skillMainPage = new SkillMainPage(context);
    DashboardPage dashboardPage;

    @Given("I am on the login page")
    @Step("Open login page")
    public void open_login_page() {
        skillMainPage.open();
    }

    @When("I enter valid username and password")
    @Step("Enter valid username and password")
    public void enter_valid_credentials() {
        dashboardPage = skillMainPage.login("admin@example.com", "admin123");
    }

    @Then("I should see the dashboard")
    @Step("Verify that dashboard is visible")
    public void verify_dashboard_visible() {
        context.wait.until(ExpectedConditions.visibilityOf(dashboardPage.header));
        assertTrue("Dashboard header is not visible", dashboardPage.header.isDisplayed());
    }
}
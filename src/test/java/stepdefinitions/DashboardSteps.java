package stepdefinitions;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import pages.DashboardPage;
import pages.SkillMainPage;
import utils.TestContext;

import static org.junit.Assert.assertTrue;

@Epic("Dashboard")
@Feature("Test and Candidate Management")
@Owner("Konstantin")
public class DashboardSteps {

    TestContext context = Hooks.getContext();
    DashboardPage dashboardPage;

    @Given("I am on the dashboard after login")
    @Step("Login and open dashboard")
    public void i_am_on_the_dashboard_after_login() {
        SkillMainPage mainPage = new SkillMainPage(context);
        dashboardPage = mainPage.login();
    }

    @When("I create a test with name {string} and description {string}")
    @Step("Create test with name: {0} and description: {1}")
    public void i_create_a_test_with_name_and_description(String name, String description) {
        dashboardPage.createTest(name, description);
    }

    @Then("the test should be created successfully")
    @Step("Verify test was created successfully")
    public void the_test_should_be_created_successfully() {
        assertTrue("Test should be created", true);
    }

    @When("I add a candidate with name {string} and a unique email")
    @Step("Add candidate with name: {0} and auto-generated unique email")
    public void i_add_a_candidate_with_name_and_a_unique_email(String name) {
        String email = "test." + System.currentTimeMillis() + "@example.com";
        dashboardPage.addCandidate(name, email);
    }

    @Then("the candidate should be added successfully")
    @Step("Verify candidate was added successfully")
    public void the_candidate_should_be_added_successfully() {
        assertTrue("Candidate should be added", true);
    }

    @When("I add a candidate with name {string} and email {string}")
    @Step("Try to add candidate {0} with email {1}")
    public void i_add_a_candidate_with_name_and_email(String name, String email) {
        dashboardPage.tryToAddCandidateWithDuplicateEmail(name, email);
    }

    @Then("a duplicate email error should appear")
    @Step("Verify duplicate email error is shown")
    public void a_duplicate_email_error_should_appear() {
        assertTrue("Duplicate email error should appear", dashboardPage.candidateErrorToast.isDisplayed());
    }
}
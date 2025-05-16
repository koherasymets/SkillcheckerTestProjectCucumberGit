package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.TestContext;

public class DashboardPage {

    private final TestContext context;

    public DashboardPage(TestContext context) {
        this.context = context;
        PageFactory.initElements(context.driver, this);
    }

    @FindBy(xpath = "//h1[contains(text(),'Панель управления')]")
    public WebElement header;

    @FindBy(xpath = "//button[.//div[contains(text(),'Создать тест')]]")
    public WebElement createTestButton;

    @FindBy(xpath = "//div[@role='dialog']")
    public WebElement testModal;

    @FindBy(xpath = "//label[contains(text(),'Название теста')]/following-sibling::input")
    public WebElement testNameInput;

    @FindBy(xpath = "//label[contains(text(),'Описание')]/following-sibling::textarea")
    public WebElement descriptionTextArea;

    @FindBy(xpath = "//button[@type='submit' and text()='Создать тест']")
    public WebElement modalSubmitButton;

    @FindBy(xpath = "//button[.//div[contains(text(),'Добавить кандидата')]]")
    public WebElement addCandidateButton;

    @FindBy(xpath = "//div[@role='dialog']")
    public WebElement candidateModal;

    @FindBy(xpath = "//label[contains(text(),'Имя')]/following-sibling::input")
    public WebElement candidateNameInput;

    @FindBy(xpath = "//label[contains(text(),'Email')]/following-sibling::input")
    public WebElement candidateEmailInput;

    @FindBy(xpath = "//button[@type='submit' and text()='Добавить кандидата']")
    public WebElement submitCandidateButton;

    @FindBy(xpath = "//li[@role='status']//div[contains(text(),'Candidate added successfully')]")
    public WebElement candidateSuccessToast;

    @FindBy(xpath = "//li[@role='status']//div[contains(text(),'Failed to add candidate')]")
    public WebElement candidateErrorToast;

    public void createTest(String name, String description) {
        context.wait.until(ExpectedConditions.elementToBeClickable(createTestButton)).click();
        context.wait.until(driver -> testModal.isDisplayed());

        testNameInput.sendKeys(name);
        descriptionTextArea.sendKeys(description);
        modalSubmitButton.click();

        context.wait.until(driver -> {
            try {
                return !testModal.isDisplayed();
            } catch (Exception e) {
                return true;
            }
        });
    }

    public void addCandidate(String name, String email) {
        context.wait.until(ExpectedConditions.elementToBeClickable(addCandidateButton)).click();
        context.wait.until(driver -> candidateModal.isDisplayed());

        candidateNameInput.sendKeys(name);
        candidateEmailInput.sendKeys(email);
        submitCandidateButton.click();

        context.wait.until(ExpectedConditions.visibilityOf(candidateSuccessToast));
        context.wait.until(ExpectedConditions.invisibilityOf(candidateSuccessToast));
    }

    public void tryToAddCandidateWithDuplicateEmail(String name, String email) {
        context.wait.until(ExpectedConditions.elementToBeClickable(addCandidateButton)).click();
        context.wait.until(driver -> candidateModal.isDisplayed());

        candidateNameInput.sendKeys(name);
        candidateEmailInput.sendKeys(email);
        submitCandidateButton.click();

        context.wait.until(ExpectedConditions.visibilityOf(candidateErrorToast));
    }
}
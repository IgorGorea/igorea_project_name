package ui.pages;

import cfg.CustomParams;
import com.github.javafaker.Faker;
import context.ObjectKeys;
import context.ScenarioContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utililities.WaitUtilities;

import static org.hamcrest.MatcherAssert.assertThat;

public abstract class BasePage {
    protected ScenarioContext scenarioContext = ScenarioContext.getScenarioInstance();
    protected CustomParams customParams = new CustomParams();
    protected WaitUtilities waitUtilities = new WaitUtilities();
    protected final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected final Faker faker = new Faker();

    @FindBy(id = "email")
    public WebElement email;
    @FindBy(id = "firstName")
    public WebElement firstName;
    @FindBy(id = "lastName")
    public WebElement lastName;
    @FindBy(id = "password")
    public WebElement password;
    @FindBy(id = "submit")
    public WebElement submitButton;
    @FindBy(id = "cancel")
    public WebElement cancelButton;
    @FindBy(id = "logout")
    public WebElement logoutButton;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void sendText(WebElement element, String text){
        customParams.sendKeysWithParam(element, text);
    }

    public void sendFirstName(String fName) {
        customParams.sendKeysWithParam(firstName, fName);
    }

    public void sendLastName(String lName) {
        customParams.sendKeysWithParam(lastName, lName);
    }


    public void submit() {
        customParams.pressTheButtonWithParam(submitButton, 5);
    }

    public void cancel() {
        customParams.pressTheButtonWithParam(cancelButton, 5);
    }

    public boolean logoutIsPresent() {
        return logoutButton.isDisplayed();
    }

    public void logout() {
        customParams.pressTheButtonWithParam(logoutButton, 5);
    }

    public void openPage(WebDriver driver, String url) {
        driver.navigate().to(url);
        logger.info("The " + url + " has been opened");
    }

    public void assertThatPageIsOpened(String url) {
        assertThat("The page is not on Login Page", assertIsOnPage(scenarioContext.getData(ObjectKeys.WEB_DRIVER), url));
    }

    public String gettingRandomEmail() {
        return faker.bothify("????##@gmail.com");
    }


    public boolean assertIsOnPage(WebDriver driver, String url) {
        logger.debug(driver.getCurrentUrl());
        return driver.getCurrentUrl().equals(url);
    }


    public void submitValidCredentials() {
        sendValidFName();
        sendValidLName();
        sendValidEm();
        sendValidPass();
        submit();
    }

    public void sendValidEm() {
        String eml = gettingRandomEmail();
        sendText(email, eml);
        scenarioContext.setData(ObjectKeys.USER_EMAIL, email);
    }

    public void sendValidPass() {
        String pass = faker.number().digits(10);
        sendText(password,pass);
        scenarioContext.setData(ObjectKeys.USER_PASS, password);
    }

    public void sendValidFName() {
        String fName = faker.name().firstName();
        sendText(firstName,fName);
        scenarioContext.setData(ObjectKeys.FIRST_NAME, fName);
    }

    public void sendValidLName() {
        String lName = faker.name().lastName();
        sendText(lastName,lName);
        scenarioContext.setData(ObjectKeys.LAST_NAME, lName);
    }
}

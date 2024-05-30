package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class SignUpPage extends BasePage {
    //It is possible to this way (with type of parameter):
    @FindBy(xpath = "//button[@id='signup']")
    public WebElement signUpButton;
    //Or this way, directly:
    @FindBy(id = "error")
    public WebElement errorMessage;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public String signUpIsPresent() {
        return signUpButton.getText();
    }

    public String errorMessageIsPresent() {
        waitUtilities.waitToBeDisplayed(errorMessage);
        return errorMessage.getText();
    }

    public void introduceCredentials(Map<String, String> regMap, String eml) {
        if (regMap.get("Email").equals("random")) {
            sendText(email, eml);
        } else {
            sendText(email, regMap.get("Email"));
        }
        sendText(firstName, regMap.get("FirstName"));
        sendText(lastName, regMap.get("LastName"));
        sendText(password, regMap.get("Password"));
    }

    public void submitWithSignUpCredentials(Map<String, String> regMap) {
        introduceCredentials(regMap, gettingRandomEmail());
        submit();
    }

    public void cancelWithSignUpCredentials(Map<String, String> regMap) {
        introduceCredentials(regMap, gettingRandomEmail());
        cancel();
    }

}


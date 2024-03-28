package ui.pages;

import ui.actions.DriverActions;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class SignUpPage extends DriverActions {
    //TODO optimize locator names
    @FindBy(xpath = "//button[@id='signup']")
    public static WebElement signUpButton;

    @FindBy(id = "firstName")
    public static WebElement firstName;

    @FindBy(id = "lastName")
    public static WebElement lastName;

    //It is possible to @FindBy(xpath = "//input[@id='email']")
    @FindBy(xpath = "//input[@id='email']")
    public static WebElement email;

    @FindBy(id = "password")
    public static WebElement password;

    @FindBy(id = "submit")
    public static WebElement submitButton;

    @FindBy(id = "cancel")
    public static WebElement cancelButton;

    @FindBy(id = "logout")
    public static WebElement logoutButton;

    @FindBy(id = "error")
    public static WebElement errorMessage;


//    todo read about PageFactory class
    public SignUpPage(){
        PageFactory.initElements(abstractDriver, this);
    }

    public void press_signUp_page_button() {
        abstractDriver.findElement(By.xpath(String.valueOf(signUpButton))).click();
    }
    public void enter_signUp_firstName_data(String fName) {
        firstName.sendKeys(fName);
    }
    //TODO exchange _ with upperCase, and to read about naming convention
    public void enterSignUpLastNameData(String lName) {
        lastName.sendKeys(lName);
    }
    public void enterSignUpEmailData(String email) {
        SignUpPage.email.sendKeys(email);
    }
    public void enterSignUpPasswordData(String password) {
        SignUpPage.password.sendKeys(password);
    }
    //TODO extract
    public void signUpSubmit() {
        submitButton.click();
    }
    public void press_signUp_cancel_button() {
        cancelButton.click();
    }
    public static String logout_button_is_present() {
           return logoutButton.getText();
    }
    public static String signUp_button_is_present() {
        return signUpButton.getText();
    }
    public static String error_message_is_present() {
        Awaitility.await().atMost(5, TimeUnit.SECONDS).until(() -> errorMessage.isDisplayed());
        return errorMessage.getText();
    }

}


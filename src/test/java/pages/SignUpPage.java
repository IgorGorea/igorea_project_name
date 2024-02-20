package pages;

import actions.DriverActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends DriverActions {
    @FindBy(xpath = "//*[@id=\"signup\"]")
    public static WebElement signUp_page_button_xpath;

    @FindBy(xpath = "//*[@id=\"firstName\"]")
    public static WebElement signUp_firstName_xpath;

    @FindBy(xpath = "//*[@id=\"lastName\"]")
    public static WebElement signUp_lastName_xpath;

    @FindBy(xpath = "//*[@id=\"email\"]")
    public static WebElement signUp_email_xpath;

    @FindBy(xpath = "//*[@id=\"password\"]")
    public static WebElement signUp_password_xpath;

    @FindBy(xpath = "//*[@id=\"submit\"]")
    public static WebElement signUp_submit_button_xpath;

    @FindBy(xpath = "//*[@id=\"cancel\"]")
    public static WebElement signUp_cancel_button_xpath;

    @FindBy(xpath = "//*[@id=\"logout\"]")
    public static WebElement logout_button_xpath;

//
//    @FindBy(xpath = "//*[@id=\"usr\"]")
//    public static WebElement usernameTextArea;
//    public static void sendkeys_username() {
//        usernameTextArea.sendKeys("usr1");
//    }
//    @FindBy(xpath = "//*[@id=\"pwd\"]")
//    public static WebElement login_text_xpath;
//    public static void sendkeys_login() {
//        driver.findElement(By.xpath(String.valueOf(login_text_xpath))).sendKeys("pwd1");
//    }
//    @FindBy(xpath = "//*[@id=\"second_form\"]/input")
//    public static WebElement login_button_xpath;
//    public static void click_login_button(){
//        driver.findElement(By.xpath(login_button_xpath)).click();
//    }
//    @FindBy(xpath = "//*[@id=\"NewRegistration\"]")
//    public static WebElement new_registration_button_xpath;
//    public static void click_new_registration_button(){
//        driver.findElement(By.xpath(new_registration_button_xpath)).click();
//    }
//    todo read about PageFactory class
    public SignUpPage(){
        PageFactory.initElements(abstractDriver, this);
    }

    //TODO to remove static from below Methods definitions and to change the calling to it
    public void press_signUp_page_button() {
        abstractDriver.findElement(By.xpath(String.valueOf(signUp_page_button_xpath))).click();
    }
    public void enter_signUp_firstName_data(String fName) {
        signUp_firstName_xpath.sendKeys(fName);
    }
    public void enter_signUp_lastName_data(String lName) {
        signUp_lastName_xpath.sendKeys(lName);
    }
    public void enter_signUp_email_data(String email) {
        signUp_email_xpath.sendKeys(email);
    }
    public void enter_signUp_password_data(String password) {
        signUp_password_xpath.sendKeys(password);
    }
    public void press_signUp_submit_button() {
        signUp_submit_button_xpath.click();
    }
    public void press_signUp_cancel_button() {
        signUp_cancel_button_xpath.click();
    }
    public void press_logout_button() {
            logout_button_xpath.getAccessibleName();
    }

}


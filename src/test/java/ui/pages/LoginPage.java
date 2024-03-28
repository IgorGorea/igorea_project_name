package ui.pages;

import ui.actions.DriverActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//TODO read about abstract class, about PageObject pattern, about encapsulation
//TODO to understand what is the difference when logger is in every class or only in one class
//TODO annotation
//TODO read about reverse waiter
public class LoginPage extends DriverActions {

    public LoginPage(){
        PageFactory.initElements(abstractDriver, this);
    }

    @FindBy(xpath = "//*[@id=\"email\"]")
    public static WebElement loginPage_email_xpath;
    @FindBy(xpath = "//*[@id=\"password\"]")
    public static WebElement loginPage_password_xpath;
    @FindBy(xpath = "//*[@id=\"submit\"]")
    public static WebElement login_submit_button_xpath;
    @FindBy(xpath = "//*[@id=\"logout\"]")
    public static WebElement logout_button_xpath;


    public void enter_loginPage_email_data(String email) {
        loginPage_email_xpath.sendKeys(email);
    }
    public void enter_loginPage_password_data(String password) {
        loginPage_password_xpath.sendKeys(password);
    }
    public void press_loginPage_submit_button() {
        login_submit_button_xpath.click();
    }
    public static String logout_button_is_present() {
        return logout_button_xpath.getText();
    }

}

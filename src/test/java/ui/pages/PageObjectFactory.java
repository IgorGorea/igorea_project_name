package ui.pages;

import org.openqa.selenium.WebDriver;

public class PageObjectFactory {
    private WebDriver driver;
    private LoginPage loginPage;
    private SignUpPage signUpPage;

    public PageObjectFactory(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }

    public SignUpPage getSignUpPage() {
        return (signUpPage == null) ? signUpPage = new SignUpPage(driver) : signUpPage;
    }
}


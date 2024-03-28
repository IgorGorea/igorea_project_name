package ui.actions;

import com.github.javafaker.Faker;
import ui.pages.LoginPage;

import java.util.Map;

public class LoginStepsActions {
    public LoginPage loginPage = new LoginPage();
    private Faker faker = new Faker();
    public void introducingLoginCredentials(Map<String,String> regMap){
        loginPage.enter_loginPage_email_data(regMap.get("Email"));
        loginPage.enter_loginPage_password_data(regMap.get("Password"));
    }

}

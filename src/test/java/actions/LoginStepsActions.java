package actions;

import pages.SignUpPage;

import java.util.Map;


public class LoginStepsActions {

    public SignUpPage signUpPage = new SignUpPage();

    public void registerUser(Map<String,String> regMap){
        signUpPage.enter_signUp_firstName_data(regMap.get("FirstName"));
        signUpPage.enter_signUp_lastName_data(regMap.get("LastName"));
        signUpPage.enter_signUp_email_data(regMap.get("Email"));
        signUpPage.enter_signUp_password_data(regMap.get("Password"));
        signUpPage.press_signUp_submit_button();
    }
}

package ui.actions;

import com.github.javafaker.Faker;
import ui.pages.SignUpPage;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SignUpStepsActions {

    public SignUpPage signUpPage = new SignUpPage();
    private final Faker faker = new Faker();
    protected static final Logger logger = LogManager.getLogger();

    public void introducingCredentials(Map<String,String> regMap){
        if(regMap.get("Email").equals("random")){
            signUpPage.enterSignUpEmailData(gettingRandomEmail());
        } else {
            signUpPage.enterSignUpEmailData(regMap.get("Email"));
        }
        signUpPage.enter_signUp_firstName_data(regMap.get("FirstName"));
        signUpPage.enterSignUpLastNameData(regMap.get("LastName"));
        signUpPage.enterSignUpPasswordData(regMap.get("Password"));
    }
    public String gettingRandomEmail(){
        return faker.bothify("????##@gmail.com");
    }

    public void introducingRandomCredentials(){
        String fname = faker.name().firstName();
        signUpPage.enter_signUp_firstName_data(fname);
        logger.info("First Name: " + fname);
        String lname = faker.name().lastName();
        signUpPage.enterSignUpLastNameData(lname);
        logger.info("Last Name: " + lname);
        String email = gettingRandomEmail();
        signUpPage.enterSignUpEmailData(email);
        logger.info("Email: " + email);
        String password = faker.number().digits(10);
        signUpPage.enterSignUpPasswordData(password);
        logger.info("Password: " + password);
    }


}

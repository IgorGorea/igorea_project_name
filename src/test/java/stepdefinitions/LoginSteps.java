package stepdefinitions;


import actions.DriverActions;
import actions.LoginStepsActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.SignUpPage;

import java.util.Map;


//TODO how works extends and OOP principles
//TODO extend DriverActions when Before will be repaired
public class LoginSteps extends DriverActions {

    private final LoginStepsActions loginStepsActions = new LoginStepsActions();

    @Given("home page is open in chrome {string}")
    public void homePageIsOpenInChrome(String url) {
        System.out.println("I'm in step");
        openPage(url);
    }

    @When("user registers with the following credential:")
    public void userRegistersWithTheFollowingCredential(Map<String, String> credTable){
        loginStepsActions.registerUser(credTable);
//        signUpPage.enter_signUp_firstName_data(dt.get("FirstName"));
    }

    @Then("the user is successfully created")
    public void theUserIsSuccessfullyCreated() {
        abstractDriver.get("https://thinking-tester-contact-list.herokuapp.com/contactList");
        Assert.assertTrue(press_logout_button() = "Logout");
    }
}

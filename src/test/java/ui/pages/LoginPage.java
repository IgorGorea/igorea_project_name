package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.context.ObjectKeys;

public class LoginPage extends BasePage {
    @FindBy(id = "add-contact")
    public WebElement addContact;
    @FindBy(xpath = "//*[@id=\"myTable\"]/tr[1]/td[2]")
    public WebElement firstContactName;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void addContact() {
        addContact.click();
    }

    public void submitLoginCredentials() {
        enterEmail((String) scenarioContext.getData(ObjectKeys.USER_EMAIL));
        enterPassword((String) scenarioContext.getData(ObjectKeys.USER_PASS));
        submit();
    }

    public String firstContactName() {
        return firstContactName.getText();
    }

}

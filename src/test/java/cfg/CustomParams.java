package cfg;

import io.cucumber.java.DataTableType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import utililities.ConfigReader;
import utililities.WaitUtilities;

public class CustomParams {
    protected final Logger logger = LogManager.getLogger(CustomParams.class);
    private final WaitUtilities waitUtilities = new WaitUtilities();
    private final ConfigReader configReader = new ConfigReader();
    private final int DEFAULT_TIMEOUT = Integer.parseInt(configReader.getProperty("defaultAwaitTime"));

    @DataTableType(replaceWithEmptyString = "<empty>")
    public String stringType(String cell) {
        return cell;
    }

    public void sendKeysWithParam(WebElement element, String text) {
        sendKeysWithParam(element,text,DEFAULT_TIMEOUT);
    }

    public void sendKeysWithParam(WebElement element, String text, int timeoutInSeconds) {
        waitUtilities.waitToBeDisplayed(element,timeoutInSeconds);
        element.sendKeys(text);
        if (element.getAccessibleName() != null) {
            logger.info("Sending key: " + text + " to element: " + element.getAccessibleName());
        } else {
            logger.info("Sending key: " + text + " to element with no accessible name");
        }
    }

    public void pressTheButtonWithParam(WebElement element, int timeoutInSeconds){
        waitUtilities.waitToBeDisplayed(element,timeoutInSeconds);
        element.click();
    }
}

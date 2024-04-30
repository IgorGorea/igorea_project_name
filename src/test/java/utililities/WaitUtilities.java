package utililities;


import org.awaitility.Awaitility;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class WaitUtilities {
    private final ConfigReader configReader = new ConfigReader();
    private final int DEFAULT_TIMEOUT = Integer.parseInt(configReader.getProperty("defaultAwaitTime"));

    public void waitToBeDisplayed(WebElement element) {
        try {
            Awaitility.await().atMost(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .until(element::isDisplayed);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element was not displayed within the timeout period", e);
        }
    }

    public void waitToBeDisplayed(WebElement element, int timeoutInSeconds) {
        try {
            Awaitility.await().ignoreExceptions()
                    .atMost(timeoutInSeconds, TimeUnit.SECONDS)
                    .until(element::isDisplayed);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element was not displayed within the timeout period", e);
        }
    }

    public void waitToBeDisplayed(WebElement element, int timeoutInSeconds, int pollIntervalInSecs) {
        try {
            Awaitility.await().ignoreExceptions()
                    .pollInterval(pollIntervalInSecs, TimeUnit.SECONDS)
                    .atMost(timeoutInSeconds, TimeUnit.SECONDS)
                    .until(element::isDisplayed);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element was not displayed within the timeout period", e);
        }
    }
}

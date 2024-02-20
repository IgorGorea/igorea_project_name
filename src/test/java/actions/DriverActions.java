package actions;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
//TODO read more about lombok
@Getter
public abstract class DriverActions {
    protected static WebDriver abstractDriver;
    public static void setWebDriver(WebDriver webDriver) {
        abstractDriver = webDriver;
    }
    public void openPage(String url){
        abstractDriver.navigate().to(url);
    }
}

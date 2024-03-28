package ui.cfg;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import ui.actions.DriverActions;
import io.github.bonigarcia.wdm.WebDriverManager;
import ui.context.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;


public class BrowserDriver extends DriverActions {

    public static WebDriver driver;

    public void setUp(){
        logger.debug("SetUp method is called");
//        WebDriverManager.chromedriver().clearDriverCache().setup();
        String browserType = configReader.getProperty("browserType");
        String chromeBro = configReader.getProperty("chrome");
        String firefoxBro = configReader.getProperty("firefox");
        String edgeBro = configReader.getProperty("edge");
        switch (browserType){
            case "firefox":
                System.setProperty("webdriver.chrome.driver", firefoxBro);
                break;
            case "edge":
                System.setProperty("webdriver.chrome.driver", edgeBro);
                break;
            default:
                if (!browserType.equals("chrome")){
                logger.info("Browser type is " + browserType + ", so it is set to default one: Chrome");}
                System.setProperty("webdriver.chrome.driver", chromeBro);
        }
        driver = browserType.equalsIgnoreCase("chrome") ? new ChromeDriver() :
                 browserType.equalsIgnoreCase("firefox") ? new FirefoxDriver() :
                 browserType.equalsIgnoreCase("MSEdge") ? new EdgeDriver() :
                 new ChromeDriver();
        logger.debug("Browser Type is:" + browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        setWebDriver(driver);
    }

    public void tearDown(){
//        if (abstractDriver != null) {
//            abstractDriver.close();
//        }
        if (abstractDriver != null) {
            abstractDriver.quit();
        }
//        String verificationErrorString = verificationErrors.toString();
//        if (!"".equals(verificationErrorString)) {
//            fail(verificationErrorString);
//        }
    }
}
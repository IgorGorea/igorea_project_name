package ui.cfg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ui.utils.ConfigReader;

public class BrowserDriver {
    private static final String BROWSER_TYPE = new ConfigReader().getProperty("browserType").trim().toLowerCase();
    private static WebDriver driver;
    protected static final Logger logger = LogManager.getLogger(BrowserDriver.class);


    private static WebDriver setUp() {
        logger.debug("SetUp method is called");
        switch (BROWSER_TYPE) {
            case "chrome":
                driver = getChromeDriver();
                break;
            case "firefox":
                driver = getFirefoxDriver();
                break;
            case "edge":
                driver = getEdgeDriver();
                break;
            default:
                logger.warn("Browser type is " + BROWSER_TYPE + ", so it is set to default one: Chrome");
                driver = getChromeDriver();
        }
//        driver = BROWSER_TYPE.equalsIgnoreCase("chrome") ? new ChromeDriver() :
//                BROWSER_TYPE.equalsIgnoreCase("firefox") ? new FirefoxDriver() :
//                        BROWSER_TYPE.equalsIgnoreCase("MSEdge") ? new EdgeDriver() :
//                                new ChromeDriver();
        logger.debug("Browser Type is:" + BROWSER_TYPE);
        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            logger.debug("Driver was initialized");
            return setUp();
        }
        return driver;
    }
    private static WebDriver getFirefoxDriver(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        return new FirefoxDriver(options);
    }
    private static WebDriver getEdgeDriver(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        return new EdgeDriver(options);
    }
    private static WebDriver getChromeDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        return new ChromeDriver(options);
    }
    public static void clearBrowserCache(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }
    public static void refreshBrowser(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static void tearDown() {
//        if (driver != null) {
//            driver.close();
//        }
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }



}
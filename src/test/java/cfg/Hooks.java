package cfg;

import api.actions.ApiActions;
import context.ObjectKeys;
import context.ScenarioContext;
import io.cucumber.java.*;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.WebDriver;
import utililities.ConfigReader;

import java.time.Duration;


public class Hooks {
    ApiActions apiActions = new ApiActions();
    ScreenshotUtils screenshotUtils = new ScreenshotUtils();
    ScenarioContext scenarioContext = ScenarioContext.getScenarioInstance();

    @BeforeAll
    public static void beforeAll() {
        ConfigReader configReader = new ConfigReader();
        ScreenshotUtils.deleteOldScreenshotsDirectories(configReader);

    }

    @Before("@UI")
    public void beforeUI(Scenario scenario) {
        screenshotUtils.setStepName(scenario.getName());

        ThreadContext.put("screensPath", screenshotUtils.gettingScreensPath());
        ThreadContext.put("scenarioName", scenario.getName().trim().replaceAll(" ", "_"));
        Configurator.reconfigure();

        WebDriver driver = BrowserDriver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        String basePageUrl = scenarioContext.getConfigReader().getProperty("base.page");
        driver.navigate().to(basePageUrl);

        scenarioContext.setData(ObjectKeys.WEB_DRIVER, driver);

    }


    @AfterStep("@UI")
    public void afterStep(Scenario scenario) {
        boolean isNegativeScenario = screenshotUtils.isNegativeScenario(scenario);
        screenshotUtils.captureScreen(scenario.getName(), isNegativeScenario, scenario);
    }

    @After("@API")
    public void afterAPI() {
        apiActions.deleteUserByToken();
    }

    @After("@UI")
    public void afterUI() {
        BrowserDriver.clearBrowserCache((WebDriver) scenarioContext.getData(ObjectKeys.WEB_DRIVER));
    }

    @AfterAll
    public static void afterAll() {
        BrowserDriver.tearDown();
    }
}

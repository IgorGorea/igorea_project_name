package cfg;

import api.actions.ApiActions;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import context.ObjectKeys;
import context.ScenarioContext;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import utililities.ConfigReader;

import java.time.Duration;


public class Hooks {
    ApiActions apiActions = new ApiActions();
    ScreenshotCfg screenshotCfg = new ScreenshotCfg();
    ScenarioContext scenarioContext = ScenarioContext.getScenarioInstance();

    @BeforeAll
    public static void beforeAll() {
        ConfigReader configReader = new ConfigReader();
        ScreenshotCfg.deleteOldScreenshotsDirectories(configReader);

    }

    @Before("@UI")
    public void beforeUI(Scenario scenario) {
        screenshotCfg.setStepName(scenario.getName());

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator jc = new JoranConfigurator();
        jc.setContext(context);
        context.reset();
        context.putProperty("scenarioName", scenario.getName());
        context.putProperty("logDir", screenshotCfg.gettingScreensPath());
        context.putProperty("logDate", screenshotCfg.getCurrentDateTime("yyyy-MM-dd"));
        try {
            jc.doConfigure("src/test/resources/logback.xml");
        } catch (JoranException e) {
            throw new RuntimeException(e);
        }

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
        boolean isNegativeScenario = screenshotCfg.isNegativeScenario(scenario);
        screenshotCfg.captureScreen(scenario.getName(), isNegativeScenario, scenario);
    }

    @After("@API")
    public void afterAPI() {
        apiActions.deleteUserByToken();
    }

    @After("@UI")
    public void afterUI() {
        BrowserDriver.clearBrowserCache(scenarioContext.getData(ObjectKeys.WEB_DRIVER));
    }

    @AfterAll
    public static void afterAll() {
        BrowserDriver.tearDown();
    }
}

package ui.cfg;

import io.cucumber.java.*;
import ui.actions.DriverActions;

public class Hooks extends BrowserDriver {

    @BeforeAll
    public static void beforeAll(){
        DriverActions.
        deleteOldScreenshotsDirectories();
    }
    @Before("@UI")
    public void before(Scenario scenario){
        stepName = scenario.getName();
        setUp();
    }
    @AfterStep("@UI")
    public void afterStep(Scenario scenario) {
        boolean isNegativeScenario = isNegativeScenario(scenario);
        captureScreen(scenario.getName(), isNegativeScenario);
    }
    @After("@UI")
    public void after(){
        tearDown();
    }

}

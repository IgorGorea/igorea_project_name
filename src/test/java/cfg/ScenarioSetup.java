package cfg;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class ScenarioSetup extends BrowserDriver {
    @Before
    public void before(Scenario scenario){
        System.out.println("Before was called");
        setUp();
    }
    @After
    public void after(){
        tearDown();
    }
}

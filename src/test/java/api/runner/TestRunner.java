package api.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "./src/test/resources/features/api",
        glue = {"api/stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-report","json:cucumber.json"},
        tags = "@Run"
)
public class TestRunner {


}

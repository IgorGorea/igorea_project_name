package ui.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "./src/test/resources/features/ui",
        glue = {"ui/stepdefinitions", "ui/cfg", "ui/context"},
        plugin = {"pretty", "html:target/cucumber-html-report","json:cucumber.json"},
        tags = "@Run"
)
public class TestRunner {


}

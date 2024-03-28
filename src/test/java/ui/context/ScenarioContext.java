package ui.context;

import io.cucumber.java.DataTableType;
import ui.actions.DriverActions;

import java.util.Map;


public class ScenarioContext extends DriverActions {
    protected ConfigReader configReader;
    @DataTableType(replaceWithEmptyString = "<empty>")
    public String stringType(String cell) {
        return cell;
    }

//    private Map<String,String> scenarioData;
//    private ScenarioContext(){
//
//    }
//    public static Map<String,Object> getScenarioData() {
//        return Map<String,Object>;
//    }
}

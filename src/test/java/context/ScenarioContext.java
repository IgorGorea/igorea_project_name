package context;

import utililities.ConfigReader;

import java.util.HashMap;
import java.util.Map;


public class ScenarioContext {
    ConfigReader configReader;
    private static ScenarioContext instance;
    private Map<ObjectKeys, Object> scenarioData;

    public ScenarioContext() {
        this.scenarioData = new HashMap<>();
    }

    public static ScenarioContext getScenarioInstance() {
        if (instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }

    public ConfigReader getConfigReader() {
        if (configReader == null)
            configReader = new ConfigReader();
        return configReader;
    }

    public void setData(ObjectKeys key, Object value) {
        scenarioData.put(key, value);
    }

    public <T> T getData(ObjectKeys key) {
        return (T) scenarioData.get(key);
    }

    public void resetContext() {
        scenarioData.clear();
    }
}

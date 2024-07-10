package ui.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Map;

import static org.hamcrest.Matchers.is;

public class DataBaseSteps {
    protected final Logger logger = LoggerFactory.getLogger(DataBaseSteps.class);

    private ResultSet resultSet;

    @Given("the database is initialized")
    public void theDatabaseIsInitialized() {
        // Sugar step: Database is initialized in @Before method
    }

    @When("the admin queries for the user with id {int}")
    public void theAdminQueriesForTheUserWithId(int id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        Statement stmt = connection.createStatement();
        String querySQL = "SELECT * FROM users WHERE id = " + id;
        resultSet = stmt.executeQuery(querySQL);
    }

    @Then("the user credentials should be:")
    public void theUserNameShouldBe(Map<String, String> credTable) throws SQLException {
        if (resultSet.next()) {
            String actualName = resultSet.getString("name");
            String actualEmail = resultSet.getString("email");
            logger.info("The actual name is {} and actual email is {}", actualName, actualEmail);
            MatcherAssert.assertThat(credTable.get("name"), is(actualName));
            MatcherAssert.assertThat(credTable.get("email"), is(actualEmail));
        }
    }
}

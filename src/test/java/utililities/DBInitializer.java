package utililities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {
    protected final Logger logger = LoggerFactory.getLogger(DBInitializer.class);


    public void initialize() {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
             Statement stmt = connection.createStatement()) {

            // Create table
            String createTableSQL = "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(255), email VARCHAR(255))";
            stmt.execute(createTableSQL);

            // Insert test data
            String insertDataSQL = "INSERT INTO users (id, name, email) VALUES (1, 'John Doe', 'john.doe@example.com')";
            stmt.execute(insertDataSQL);

        } catch (SQLException e) {
            logger.error("SQL initialization error occurred: ", e);
        }
    }

    public void dBTearDown() {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
             Statement stmt = connection.createStatement()) {

            // Drop table
            String dropTableSQL = "DROP TABLE IF EXISTS users";
            stmt.execute(dropTableSQL);

        } catch (SQLException e) {
            logger.error("SQL tear down error occurred: ", e);
        }
    }
}
#@DB @Run
Feature: Verify database content

  Scenario: Verify that a user exists in the database
    Given the database is initialized
    When the admin queries for the user with id 1
    Then the user credentials should be:
      | name  | John Doe             |
      | email | john.doe@example.com |


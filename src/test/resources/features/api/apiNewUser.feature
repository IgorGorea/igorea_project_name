#@API @Smoke @Run
Feature: API Tests


  Scenario: Create New User
    Given the server is up
    When admin creates a new user
    And admin has created a new user
    Then the POST response status code should be 201
    And the response body contains "token"
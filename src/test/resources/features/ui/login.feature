#@SmokeTest @UI @Run
Feature: Login of new user

  Scenario: Successful log in
    Given user is created with valid credentials
    And user pressed the logout button
    When user submits created credentials
    Then user is logged in
Feature: New User View

  @SmokeTest
  Scenario: Validate user is able to view registration page
    Given user navigates to the login page
    When user clicks on New User Registration button
    Then user is able to view the Registration page
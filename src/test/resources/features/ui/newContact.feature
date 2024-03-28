Feature: New Contact


  Scenario: Validate user is able to view registration page
    Given user navigates to the login page
    When user clicks on New User Registration button
    Then user is able to view the Registration page

  @SmokeTest
  Scenario: Successful log in
    Given the Contact List page is opened with the following credentials:
      | Email    | DrAcula@test.com |
      | Password | Password123      |
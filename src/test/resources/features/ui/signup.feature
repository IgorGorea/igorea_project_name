@NotCyrnats-001 @UI @SmokeTest
Feature: Log in functionality

  @Run
  Scenario: Successful sign up
    Given sign up page is opened in chrome
    When user registers with the valid credentials
    Then the user is successfully created

  @Run
  Scenario: Successful cancel of registration
    Given sign up page is opened in chrome
    When user cancels with the following credentials:
      | FirstName | Doctor             |
      | LastName  | Acula              |
      | Email     | DrAcula10@test.com |
      | Password  | Password123        |
    Then the main page is opened

  @Negative @Run
  Scenario Outline: Failed sign up
    Given sign up page is opened in chrome
    When user registers with the following credentials:
      | FirstName | <firstname> |
      | LastName  | <lastname>  |
      | Email     | <email>     |
      | Password  | <password>  |
    Then the error on sign up page appeared:
      | ErrorMessage | <error> |
    Examples:
      | firstname | lastname | email   | password | error                                                                                                                                             |
      | <empty>   | sba      | random  | Abs1234  | firstName: Path `firstName` is required.                                                                                                          |
      | abs       | <empty>  | random  | Abs1234  | lastName: Path `lastName` is required.                                                                                                            |
      | abs       | sba      | <empty> | Abs1234  | email: Email is invalid                                                                                                                           |
      | abs       | sba      | random  | <empty>  | password: Path `password` is required.                                                                                                            |
      | <empty>   | <empty>  | <empty> | <empty>  | firstName: Path `firstName` is required., lastName: Path `lastName` is required., email: Email is invalid, password: Path `password` is required. |

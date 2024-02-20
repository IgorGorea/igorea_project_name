@Example-001
Feature: Log in functionality

  @SmokeTest @Run
  Scenario: Successful sign up
    Given home page is open in chrome 'https://thinking-tester-contact-list.herokuapp.com/addUser'
    When user registers with the following credential:
      | FirstName | Doctor           |
      | LastName  | Acula            |
      | Email     | DrAcula@test.com |
      | Password  | Password123      |
    Then the user is successfully created


  @SmokeTest
  Scenario: Successful log in
    Given test step
    When user enters the following credential:
      | Username         | Password    |
      | DrAcula@test.com | Password123 |
    And the log-in button is clicked
    Then the feed page is displayed



#  Scenario Outline: Check the log in is successful with valid credentials
#    Given Facebook homepage is displayed
#    When user enters the following credentials:
#      | Username | <username> |
#      | Password | <password> |
#    And the log-in button is clicked
#    Then the feed page is displayed
#    Examples:
#      | username | password   |
#      | mg12345  | Password12 |
@API @UI @Run
Feature: Login and New Contact

  Scenario: Create New Contact at New User
    Given the server is up
    And admin has created a new user
    When user creates a new contact through POST request using data:
      | firstName     | John          |
      | lastName      | Doe           |
      | birthdate     | 1970-01-01    |
      | email         | jdoe@fake.com |
      | phone         | 8005555555    |
      | street1       | 1 Main St.    |
      | street2       | Apartment A   |
      | city          | Anytown       |
      | stateProvince | KS            |
      | postalCode    | 12345         |
      | country       | USA           |
    And user submits created credentials on login page
    Then user is logged in
    And there is created contact with 'John Doe'

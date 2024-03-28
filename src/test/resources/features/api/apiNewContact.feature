@API @Smoke
Feature: API Tests

  Scenario: Create New Contact
    Given check the server is up
    When user sends a POST request to contacts with parameters in body:
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
    Then the POST response status code should be 201
    And the response body contains "_id"

  Scenario: Verify API response to contact list
    Given check the server is up
    When user sends a GET request to contact list
    Then the GET contact list response status code should be 200
    And the contact list contains "firstName" in all response bodies

  Scenario: Delete New Contact
    Given check the server is up
    When user sends a DELETE request to contact
    Then the DEL response status code should be 200
    And the response body contains "Contact deleted"


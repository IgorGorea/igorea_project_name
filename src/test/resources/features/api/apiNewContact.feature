#@API @Smoke @Run
Feature: API Tests for New Contact

  Background:
    Given the server is up
    And admin has created a new user

    #The purpose of this test is to check that new contact is created with field "_id"
  Scenario: Create New Contact
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
    Then the POST response status code should be 201
    And the response body contains "_id"


  Scenario: Verify API response to contact list
    When user sends a GET request to contact list
    Then the GET contact list response status code should be 200
    And the contact list contains "firstName" in all response bodies


  Scenario: Delete New Contact
    And user creates a contact with valid parameters
    When user sends a DELETE request to contact
    Then the DEL response status code should be 200
    And the response body contains "Contact deleted"



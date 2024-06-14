Feature: Register user

  Scenario: Register a new user on the webpage
    Given I am on the register page
    When I sign up with correct credentials and store it as user_A
    Then verify that the registration was successful

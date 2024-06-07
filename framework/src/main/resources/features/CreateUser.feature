Feature: Create user

  Scenario: Create user
    When create a new user of status A and store it as user_A -> 201
    Then verify that user user_A exists
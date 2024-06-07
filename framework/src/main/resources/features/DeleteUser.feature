Feature: Delete user

  Scenario: Delete user
    Given a new user of status A and store it as user_A -> 201
    When delete user user_A -> 204
    Then verify that user user_A does not exist
Feature: Delete user

  Scenario: Delete user
    Given a new user of status A and store it as user_A -> CREATED
    When delete user user_A -> NO_CONTENT
    Then verify that user user_A does not exist
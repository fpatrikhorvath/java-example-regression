Feature: Create book

  Scenario: Create book for user
    Given a new user of status A and store it as user_A -> 201
    When create a new book for user user_A and store it as book_A -> 201
    Then verify that book book_A exist

  Scenario: Create book for deleted user
    Given a new user of status A and store it as user_A -> 201
    And delete user user_A -> 204
    When create a new book for user user_A and store it as book_A -> 404
    Then the response has USER_NOT_FOUND error
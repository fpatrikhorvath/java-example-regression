Feature: Delete book

  Scenario: Delete book
    Given a new user of status A and store it as user_A -> 201
    Given a new book for user user_A and store it as book_A -> 201
    When delete book book_A for user user_A -> 204
    Then verify that book book_A exist

  @bug
  Scenario: Delete user with book
    Given a new user of status A and store it as user_A -> 201
    Given a new book for user user_A and store it as book_A -> 201
    When delete user user_A -> 204
    Then verify that book book_A exist

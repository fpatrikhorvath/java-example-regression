Feature: Delete book

  Scenario: Delete book
    Given a new user of status A and store it as user_A -> CREATED
    Given a new book for user user_A and store it as book_A -> CREATED
    When delete book book_A for user user_A -> NO_CONTENT
    Then verify that book book_A exist

  @bug
  Scenario: Delete user with book
    Given a new user of status A and store it as user_A -> CREATED
    Given a new book for user user_A and store it as book_A -> CREATED
    When delete user user_A -> NO_CONTENT
    Then verify that book book_A exist

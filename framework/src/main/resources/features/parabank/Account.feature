Feature: Open new account

  @bug
  Scenario: Open a new account
    Given I am on the register page
    Given I sign up with correct credentials and store it as user_A
    When I open a new account with type of checking and store it as account_A
    Then account_A account is created
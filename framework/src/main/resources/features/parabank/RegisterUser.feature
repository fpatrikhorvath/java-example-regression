Feature: Register user on the webpage

  Scenario: Register a new user
    Given I am on the register page
    When I sign up with correct credentials and store it as user_A
    Then verify that the user user_A is registered

  Scenario Outline: Register a new user without mandatory values
    Given I am on the register page
    When I sign up without <missing_parameter> and store it as user_A
    Then verify that the user is not registered

    Examples:
      | missing_parameter |
      | username          |
      | password          |
      | city              |

  Scenario: Log out after the registration
    Given I am on the register page
    Given I sign up with correct credentials and store it as user_A
    When I log out
    Then verify that the user is not logged in

  Scenario Outline: Register a new user without unnecessary values
    Given I am on the register page
    When I sign up without <missing_parameter> and store it as user_A
    Then verify that the user user_A is registered

    Examples:
      | missing_parameter |
      | phone             |
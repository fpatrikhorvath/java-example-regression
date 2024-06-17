Feature: Log in the user on the webpage

  Scenario: Log in after the registration
    Given I am on the register page
    Given I sign up with correct credentials and store it as user_A
    Given I log out
    When I log in as user_A
    Then verify that the user is logged in

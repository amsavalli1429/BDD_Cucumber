Feature: Create Customer Account

  Background:
    Given the user is on the homepage with internet access

  Scenario: Create new user with unique email
    When the user clicks "Create Account"
    And the user enters personal information:
      | First Name | Last Name |
      | John       | Doe       |
    And the user enters unique sign-in information
    And the user clicks on the "Create" button
    Then the account should be created successfully
    And the user clicks the "Sign Out" button


  Scenario: Sign In with Valid Credentials
    Given I am on the Sign In page
    When When I enter login credentials
    And I click on the "Sign In" button
    And I should be redirected to the dashboard

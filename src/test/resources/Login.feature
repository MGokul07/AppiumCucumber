@LoginScenarios
Feature: Login scenarios

  Scenario: Login with valid credentials
    When I enter username as "standard_user"
    And I enter password as "secret_sauce"
    And I login
    
  Scenario: Login with invalid credentials
    When I enter username as "standard_user"
    And I enter password as "secre123"
    And I login
    And login should fail with an error "Username a nd password do not match any user in this service."
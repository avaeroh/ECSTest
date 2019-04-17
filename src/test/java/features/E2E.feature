Feature: E2E Tech test for ECS Digital

  Background:
    Given I go to the start page

  @Test
  Scenario: End to end testing of the Docker page
    Given I click on render the challenge
    When I save each row of the challenge as an array
    And I calculate the equilibrium index of the arrays
    And I submit the answers
    Then I have completed the test
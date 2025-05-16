Feature: Dashboard actions

  Background:
    Given I am on the dashboard after login

  Scenario: Create a new test
    When I create a test with name "Autotest: Java Test" and description "Test description via PageObject"
    Then the test should be created successfully

  Scenario: Add a new candidate
    When I add a candidate with name "Ivan Testovich" and a unique email
    Then the candidate should be added successfully

  Scenario: Add a candidate with an existing email
    When I add a candidate with name "Duplicate Name" and email "test.1747158932261@example.com"
    Then a duplicate email error should appear
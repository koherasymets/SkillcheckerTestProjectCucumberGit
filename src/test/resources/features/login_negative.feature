@negative
Feature: Login - negative scenarios

  Background:
    Given I am on the login page

  Scenario Outline: Unsuccessful login attempts
    When I enter username "<username>" and password "<password>"
    Then I should see the error message "<error_message>"

    Examples:
      | username          | password     | error_message                  |
      | admin@example.com | wrongpass123 | Ошибка входа                   |
      |                   | admin123     | Некорректная электронная почта |
      | admin@example.com |              | Пароль обязателен              |
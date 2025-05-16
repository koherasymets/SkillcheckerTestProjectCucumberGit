Feature: Negative login scenarios

  Scenario: Incorrect password
    When I enter username "admin@example.com" and password "wrongpass123"
    Then I should see the error message "Ошибка входа"

  Scenario: Empty username
    When I enter username "" and password "admin123"
    Then I should see the error message "Некорректная электронная почта"

  Scenario: Empty password
    When I enter username "admin@example.com" and password ""
    Then I should see the error message "Пароль обязателен"
Feature: Проверка отображения кнопки 'Установить приложение'

  Background:
    Given The Wink main page is open

  @hooks
  @close
  Scenario:
    When Click on random element from films list
    Then Check setup application button is displayed



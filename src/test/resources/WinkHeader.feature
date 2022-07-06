Feature: Проверка заголовков страниц

  Background:
    Given The Wink main page is open

  @hooks
    @close
  Scenario Outline:
    When Click on item from popup menu "<itemXpath>"
    Then Check header is displayed "<headerXpath>"
    Examples:
      | itemXpath                                              | headerXpath                   |
      | //a[contains(@class, 'item') and @href='/books']       | //h1[.='Аудиокниги']          |
      | //a[contains(@class, 'item') and @href='/services']    | //h1[.='Подписки и сервисы']  |
      | //a[contains(@class, 'item') and @href='/collections'] | //h1[.='Все подборки']        |
      | //a[contains(@class, 'item') and @href='/promocode']   | //h1[.='Активация промокода'] |



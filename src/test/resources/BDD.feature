Feature: Проверка добавления товара в корзину

  Background:
    Given User Authorized

  Scenario Outline:
    When click on product by name '<name>'
    And click add to cart
    Then check sum and remove button '<summa>'
    Examples:
      |name |                    summa|
      |Sauce Labs Bike Light  |  $9.99|
      |Sauce Labs Bolt T-Shirt| $15.99|






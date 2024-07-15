@tag
Feature: Purchase the order from groupmart Website
  
  
  Background:
  Given The groupmart app is launched

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given The user logged in with <email> and <password>
    When The user add the <product> in the cart
    And with the <product> in the cart user checkout to the payment page 
    And The user provide the details and click on the place order button
    Then "THANKYOU FOR THE ORDER." text is displayed on the thank you page

    Examples: 
      | email           | password   | product         |
      | abc+2@jmail.com | Angoli1@   | ADIDAS ORIGINAL |
      | abc+1@jmail.com | Abc123@#   |  ZARA COAT 3    |

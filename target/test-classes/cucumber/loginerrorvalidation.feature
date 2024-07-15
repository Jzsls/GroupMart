
@tag
Feature: Login page error validations
  
 Background:
  Given The groupmart app is launched
  
  @IncorrectEmailAndPassword
  Scenario Outline: Entering incorrect email and password
    Given The user is on the Login webpage
    When The user logged in with <email> and <password> 
    Then "Incorrect email or password." error is displayed on the screen

    Examples: 
      | email           | password |
      | adc@jmail.com   | Ayu223@# |  
      
  @IncorrectEmailAndCorrectPassword
  Scenario Outline: Entering incorrect email and correct password
    Given The user is on the Login webpage
    When The user logged in with <email> and <password>
    Then "Incorrect email or password." error is displayed on the screen

    Examples: 
      | email           | password |
      | adc@jmail.com   | Abc123@# |  
      

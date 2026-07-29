@tag
Feature:Purchase the order from Ecommerce Website

 Background:
 Given I land on Ecommerce Page
 
 @Regression
  Scenario Outline: Positive Test of Submitting the order
 
 Given  Logged in with username  <name> and password <password>
 When I add product <productName> to Cart
 And Checkout <productName> to Cart
 Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
 
 Examples:
|name                   | password     | productName|
| sivamani354@gmail.com | Sivamani354@ | ZARA COAT 3 |
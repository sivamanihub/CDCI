@tag
Feature:Purchase Error Validation


 @ErrorValidation 
 Scenario Outline: Positive Test of Submitting the order
 Given I land on Ecommerce Page
 When  Logged in with username  <name> and password <password>
 Then  "Incorrect email or password." message is dispalyed
 
 Examples:
|name                   | password     | 
| sivamani354@gmai.com | Sivamani354@ |
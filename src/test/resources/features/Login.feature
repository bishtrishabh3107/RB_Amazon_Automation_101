Feature: Login to the amazon web-application
  This feature verifies all combinations of login to the application

  @Regression
  Scenario: 01 Login with a valid username and password from properties file
    Given Launch the--Amazon Shopping--from--prop file
    When Enter valid username and password from prop file--Amazon Shopping
    Then Login and Validate successful login--Amazon Shopping
    And Logout--Amazon Shopping

  @Regression
  Scenario: 02 Login with a valid username and password from datatable
    Given Launch the--Amazon Shopping--from--prop file
    When Enter valid username and password from datatable
      |UserName|Password|
      |9690806397|Adv@1234|
    Then Login and Validate successful login--Amazon Shopping
    And Logout--Amazon Shopping

  @Regression
  Scenario Outline: 03 Login with a valid-invalid username and password from examples
    Given Launch the--Amazon Shopping--from-https://www.amazon.com
    When Enter valid Username as "<username>" and Password as "<password>".
    Then Login and Validate successful login--Amazon Shopping
    And Logout--Amazon Shopping
    Examples:
      |username|password|
      |9690806397|Adv@1234|
      |patchtest@gmail.com|TestPassword@123|
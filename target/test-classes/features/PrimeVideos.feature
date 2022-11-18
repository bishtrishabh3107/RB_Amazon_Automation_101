Feature: Login to Prime Videos and check for prime video subscription
  This feature verifies login to to prime videos and check for prime subscription

  @Regression
  Scenario: 01 Login with a valid username and password from properties file
    Given Launch the--Amazon Videos--from--prop file
    When Enter valid username and password from prop file--Amazon Videos
    Then Login and Validate successful login--Amazon Videos
    And Logout--Amazon Videos
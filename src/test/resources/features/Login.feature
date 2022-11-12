Feature: Login to the amazon web-application
  This feature verifies all combinations of login to the application

  Scenario: 01 Login with a valid username and password from properties file
    Given Launch the url from prop file
    When Enter valid username and password from prop file
    Then Login and Validate successful login
Feature: Search item with matching name
  This feature verifies that we get top 5 results with the matching names for the item searched.

  @Regression
  Scenario: 01 Search
    Given Launch the url from--prop file
    When Enter valid username and password from prop file
    Then Login and Validate successful login
    And Logout

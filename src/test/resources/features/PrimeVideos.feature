Feature: Login to Prime Videos and check for prime video subscription
  This feature verifies login to to prime videos and check for prime subscription

  @Regression
  Scenario: 01 Login with a valid username and password from properties file
    Given Launch the--Amazon Videos--from--prop file
    When Enter valid username and password from prop file--Amazon Videos
    Then Login and Validate successful login--Amazon Videos
    And Logout--Amazon Videos

  @test
  Scenario: 02 Select Categories--Amazon Originals--Search for TV series
    Given Launch the--Amazon Videos--from--prop file
    When Enter valid username and password from prop file--Amazon Videos
    Then Login and Validate successful login--Amazon Videos
    Given Select Categories--Amazon Originals--Get--All TvSeries
    And Categories--Amazon Originals--Validate--The Family Man
    Given Select Categories--Amazon Originals--Get--All Movies
    And Categories--Amazon Originals--Validate--Coming 2 America
    And Logout--Amazon Videos


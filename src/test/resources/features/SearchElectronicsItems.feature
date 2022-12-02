Feature: Search item with matching name
  This feature verifies that we get top results with the matching names for the item searched.

  @Regression
  Scenario: 01 Search-- Washing Machine 7kg -- Get Results -- First Page - BestSeller
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--washing machine 7kg
    Then Get Electronics Item from result--FirstPage
    Then Get Electronics Item from result--BestSeller

  @Regression
  Scenario: 02 Search-- Air Purifier -- Get Results -- Sponsored - NonSponsored - AmazonChoice
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--AirPurifier For Home
    Then Get Electronics Item from result--Sponsored
    Then Get Electronics Item from result--NonSponsored
    Then Get Electronics Item from result--AmazonChoice

  @Regression
  Scenario: 02 Search -- Air Purifier-LED TV 55 inches full hd  -- Get Results -- FirstPage - BestSeller - Sponsored - NonSponsored - AmazonChoice
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--Action Camera
    Then Get Electronics Item from result--FirstPage
    Then Get Electronics Item from result--BestSeller
    Then Get Electronics Item from result--Sponsored
    Then Get Electronics Item from result--NonSponsored
    Then Get Electronics Item from result--AmazonChoice
    When Search item on header--LED TV 55 inches full hd
    Then Get Electronics Item from result--FirstPage
    Then Get Electronics Item from result--BestSeller
    Then Get Electronics Item from result--Sponsored
    Then Get Electronics Item from result--NonSponsored
    Then Get Electronics Item from result--AmazonChoice

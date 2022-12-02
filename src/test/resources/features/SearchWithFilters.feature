Feature: Search item with filters
  This feature verifies that we get search results based on filters.

  @Regression
  Scenario: 01 Search-- Rucksack Bags -- Search With Filters
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--Rucksack Bags
    And Enter filters
      |CustomerReviews |MinPrice|MaxPrice|Discount|Brands|
      |       4        |   700  |  2500  | 50%    |Trawoc|
    Then Validate filters results
      |CustomerReviews |MinPrice|MaxPrice|Discount|Brands|
      |       4        |   700  |  2500  | 50%    |Trawoc|
Feature: Search item with filters when products display type id GRID.
  This feature verifies that we get search results based on filters when display type id GRID.

  @Regression @Sanity
  Scenario: 01 Search-- Rucksack Bags -- Search With Filters -- Display Type GRID
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--Rucksack Bags
    And Enter filters
      |CustomerReviews |MinPrice|MaxPrice|Discount|Brands|
      |       4        |   700  |  2500  | 50%    |Trawoc|
    Then Validate filters results--grid
      |CustomerReviews |MinPrice|MaxPrice|Discount|Brands|
      |       4        |   700  |  2500  | 50    |Trawoc|

  @Regression
  Scenario: 02 Search-- Cricket Bats -- Search With Filters -- Display Type GRID
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--Cricket Bats
    And Enter filters
      |CustomerReviews |MinPrice|MaxPrice|Discount|Brands|
      |       3        |   600 |  2500  | 25%    |Strauss|
    Then Validate filters results--grid
      |CustomerReviews |MinPrice|MaxPrice|Discount|Brands|
      |       3        |  600 |  2500  | 25    |Strauss|

  @Regression
  Scenario: 03 Search-- Toothpaste -- Search With Filters -- Display Type GRID
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--Toothpaste
    And Enter filters
      |CustomerReviews |MinPrice|MaxPrice|Discount|Brands |
      |       3        |   100 |  1000  | 10%    |Colgate|
    Then Validate filters results--grid
      |CustomerReviews |MinPrice|MaxPrice|Discount|Brands|
      |       3        |   100 |  1000  | 10    |Colgate|

  @Regression @Sanity
  Scenario: 04 Search-- Bedsheets -- Search With Filters -- Display Type GRID
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--Bedsheets
    And Enter filters
      |CustomerReviews |MinPrice|MaxPrice|Discount|Brands        |
      |       4        |   100 |  1500   | 70%    |RajasthaniKart|
    Then Validate filters results--grid
      |CustomerReviews |MinPrice|MaxPrice|Discount|Brands        |
      |       4        |   100  |  1500   | 70    |RajasthaniKart|
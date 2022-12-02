Feature: Search item with filters when products display type id LIST.
  This feature verifies that we get search results based on filters when display type id GRID.

  @Regression
  Scenario: 01 Search-- Mobile Phone -- Search With Filters -- Display Type LIST
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--Mobile Phone
    And Enter filters
      |CustomerReviews |MinPrice  |MaxPrice  |Discount|Brands|
      |       4        |   10000  |  100000  | 10%    |Apple |
    Then Validate filters results--list
      |CustomerReviews |MinPrice  |MaxPrice  |Discount|Brands|
      |       4        |   10000  |  100000  | 10     |Apple |

  @Regression
  Scenario: 02 Search-- Foldable Bicycle -- Search With Filters -- Display Type LIST
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--Foldable Bicycle
    And Enter filters
      |CustomerReviews |MinPrice |MaxPrice |Discount|Brands        |
      |       4        |   5000  |  20000  | 50%    |Urban Terrain |
    Then Validate filters results--list
      |CustomerReviews |MinPrice |MaxPrice |Discount|Brands        |
      |       4        |   5000  |  20000  | 50     |Urban Terrain |

  @Regression @Test
  Scenario: 03 Search-- smart tv 55+ inch -- Search With Filters -- Display Type LIST
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--smart tv 55+ inch
    And Enter filters
      |CustomerReviews |MinPrice |MaxPrice |Discount|Brands |
      |       4        |   10000 |  80000  | 35%    |Samsung|
    Then Validate filters results--list
      |CustomerReviews |MinPrice |MaxPrice |Discount|Brands  |
      |       4        |   10000 |  80000  | 35     |Samsung |
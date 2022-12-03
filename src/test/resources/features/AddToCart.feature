Feature: Search item with filter and then Add the first product to cart.
  This feature verifies that we get add the correct product to cart.

  @Regression
  Scenario: 01 Search-- Laptop -- Search With Filters -- Display Type LIST
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--laptop under 50000
    And Enter filters
      |CustomerReviews |MinPrice  |MaxPrice  |Discount|Brands|
      |       4        |   30000  |  50000  | 35%    |Lenovo|
    Then Validate filters results--list
      |CustomerReviews |MinPrice  |MaxPrice  |Discount|Brands|
      |       4        |   30000  |  50000  | 35     |Lenovo|
    And Add maximum bought product to cart
    Then Validate the cart

  @Regression @Test
  Scenario: 01 Search-- Refrigirator double door -- Search With Filters -- Display Type LIST
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--Refrigirator double door
    And Enter filters
      |CustomerReviews |MinPrice  |MaxPrice  |Discount|Brands|
      |       4        |   30000  |  100000  | 10%    |LG|
    Then Validate filters results--list
      |CustomerReviews |MinPrice  |MaxPrice  |Discount|Brands|
      |       4        |   30000  |  100000  | 10     |LG|
    And Add maximum bought product to cart
    Then Validate the cart

  @Regression @Test
  Scenario: 01 Search-- Alexa -- Search With Filters -- Display Type LIST
    Given Launch the--Amazon Shopping without SignIn--from--prop file
    When Search item on header--Alexa
    And Enter filters
      |CustomerReviews |MinPrice  |MaxPrice  |Discount|Brands|
      |       4        |   1000  |  10000  | 10%    |Amazon|
    Then Validate filters results--list
      |CustomerReviews |MinPrice  |MaxPrice  |Discount|Brands|
      |       4        |   1000  |  10000  | 10     |Amazon|
    And Add maximum bought product to cart
    Then Validate the cart
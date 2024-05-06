Feature: Add To Bag

  Scenario: Adding a product to the Bag
    Given the user is on "men" product page
    When the user selects "Crest T-Shirt" product
    And the user selects size "m"
    And the user add the product to bag
    When the user selects another product
    And the user selects size "l"
    And the user add the product to bag
    And the user selects another product
    And the user selects size "s"
    And the user add the product to bag
    Then the user removes a product
    Then the user verifies total amount

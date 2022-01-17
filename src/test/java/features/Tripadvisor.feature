Feature: Search Tripadvisor

  Background:

    Given user navigates to https://www.tripadvisor.com/
    And selects find Restaurants

  Scenario Outline: Detail of the first restaurant search result

    Given User search restaurant <type>
    When filters by <meal>
    Then User views restaurant information

    Examples:
      |type|meal|
      |Pizza|Delivery    |
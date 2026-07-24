Feature: District.in User Journey Automation

  # ============================================
  # TC1 - Sports Activities
  # ============================================
  Scenario: TC1 - Display weekend sports activities sorted by lowest price
    Given the user is on the district.in home page
    When the user selects the city
    And the user navigates to Events section
    And the user applies the weekend sports filter
    Then the sports events should be displayed with lowest price on top
    And each event should show name and price

  # ============================================
  # TC2 - Movie Languages
  # ============================================
  Scenario: TC2 - Extract all movie languages available on the site
    Given the user is on the district.in home page
    When the user navigates to Movies section
    And the user opens the Language filter
    Then all available movie languages should be extracted
    And the list should not be empty

  # ============================================
  # TC3 - Invalid Sign-In
  # ============================================
  Scenario: TC3 - Sign in with invalid mobile number shows an error
    Given the user is on the district.in home page
    When the user clicks the Sign In button
    And the user enters an invalid mobile number
    And the user clicks Continue
    Then an error message should be displayed
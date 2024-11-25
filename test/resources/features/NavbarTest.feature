Feature: Verify Main Page Functionalities
  As a user, I want to verify that all main functionalities on the 2NHaber website work as expected.

  @paralel
  Scenario: Verify main page and navigation functionalities
    Given the user navigates to the 2NHaber homepage
    Then clicks company logo should be redirected to the homepage
    And clicks the dropdown menu for each navbar link should be accessible
    Then toggles the dark-light mode button the page theme should switch accordingly
    Then clicks on the search button the search popup should be displayed
    Then clicks on the hamburger menu button the hamburger menu should be displayed



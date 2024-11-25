Feature: 2NTECH Form Submission

  @paralel
  Scenario:  Successfully submitting the form for Test Engineer position
    Given navigate to the 2NTECH HR page
    When fill out the first step of the form with valid information
    And select "position" from the position
    And submit the form
    Then should see a success message confirming the form submission

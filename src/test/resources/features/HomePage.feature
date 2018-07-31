@web
Feature:Home Page

  Scenario:Home Page - Latest news
    Given User is on valtech home page
    And Click on accept cookies if it displays



  Scenario:Home Page - Navigate to sections
    Given User is on valtech home page
    And Click on accept cookies if it displays
    And User clicks on top menu
    When User click on Work page
    Then Work page should be displyed with heading "Work"
    When User click on Serivcs page
    Then Services page should be displyed with heading "Services"
    When User click on About page
    Then About page should be displyed with heading "About"


  Scenario:Home Page - Navigate to sections
    Given User is on valtech home page
    And Click on accept cookies if it displays
    And User clicks on top menu
    When User Click on Contactus Page
    Then User should be on Contactus page
    And Verify the offices count


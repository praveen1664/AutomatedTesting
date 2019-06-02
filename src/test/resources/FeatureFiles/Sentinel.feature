Feature: Validate the values in Sentinel application and validate sum logic for page TCO sum and TCO sums.

  @tag1
  Scenario: Verify that there are no null values in table in every page.
    Given Browser is launched
    When Navigate to the sentinel application
    When Enter login credentials and click on login button
    Then Select business segment and assets group from toggle menu
    When Move to the Whiteboard tab
    Then Move through all the pages and validate that there no null values
    Then Logout from the application
  	Then Close browser
   
   
   @tag2
  	Scenario: Verify the Page TCO and total TCO sums
    Given Browser is launched
    When Navigate to the sentinel application
    When Enter login credentials and click on login button
    Then Select business segment and assets group from toggle menu
    When Move to the Whiteboard tab
    Then verify the page TCO sum of first page
    When Move through all the pages and validate TCO sums
    Then Logout from the application
  	Then Close browser

	@tag3
	Scenario: validate the Tech Padu Graph
		Given Browser is launched
    When Navigate to the sentinel application
    When Enter login credentials and click on login button
    Then Select business segment and assets group from toggle menu
    Then Validate Tech Padu Graph
    Then Logout from the application
  	Then Close browser
    
 @tag4
 Scenario: validate the Booked Value Table details
    Given Browser is launched
    When Navigate to the sentinel application
    When Enter login credentials and click on login button
    Then Select business segment and assets group from toggle menu
    Then Validate Booked Value Table
    Then Logout from the application
  	Then Close browser
#Author: vikram_j@optum.com

Feature: System test (Virtual Visits - MyUHC portal)
  I want to use this template for my feature file

  @Scenario1
  Scenario: Virtual visits service is present and estimated cost displays amount on the plan, under Common Services and Costs
    Given Browser is Launched
		When Navigate to MyUHC URL in browser
    And Enter login credentials and click on sign in button
    And Answer the security question and click submit button
   	Then User should navigate to the My UHC portal Home page
   	When Click on virtual visits link, under Common Services and Costs
		Then Should be navigated to virtual visits section, under Common Services and Costs
		Then Logout of MyUHC portal
		Then close the browser
		
		Scenario: Verify that table is without null values, verify page and total Tco sums
    Given Browser is launched
    When Navigate to the sentinel application
    When Enter login credentials and click on login button
    When Move to the Whiteboard tab
    Then verify the page TCO sum of first page
    Then Move through all the pages and validate TCO sums
    Then Logout from the application
  	Then Close browser


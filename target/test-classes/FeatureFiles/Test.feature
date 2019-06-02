#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: This is a test feature
  I want to use this template for my feature file

  @tag1
  Scenario: Title of your scenario first scenario
    Given open the browser
    Then enter your mailid
    
  Scenario Outline: Title of your scenario second scenario
 
 		Given enter your "<email>"
    
    Examples:
    |email|
    |vikram_j|
    |kkapil|

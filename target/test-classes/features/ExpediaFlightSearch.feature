# Application Name: Expedia
#           Module: Flight Search
#           Author: Abhishek Prajapati
#            Email: prajapatiabhishek1996@gmail.com
#############################################################################################



Feature: Expedia Flight Search
  	This feature file will test the different functionalities of the Expedia Flight Search Module.

		Background: 
		    Given User is on Expedia Homepage
		    
		Scenario: Expedia Return Flight Search Functionality with correct data.
				Given User is able to see the Expedia HomePage
				When User clicks on the Flights tab button
				And Enters the required details in the form fields
						|    From   | Pune (PNQ-Lohegaon)                    |
						|     To    | Mumbai (BOM-Chhatrapati Shivaji Intl.) |
						| Departing | 30/04/2020                             |
						| Returning | 30/04/2020                             |
						| Travellers| 2                                      |
						
				And Clicks on Search button
				Then User must be landed on the results page
			
		 	
				

 
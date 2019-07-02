# Week4Test_Coffee

Overview
Magic Leap Coffee App Assignment
The idea behind this assignment is to deliver a simple two-screen application that covers some basic 
Android App development principles. We are not interested in how fast you can complete the assignment, but rather the quality, 
structure and design decisions applied during the development of the app. Equally important is the ability to 
meet the app requirements and follow the design mock-ups provided below.

Instructions

For this assignment, we are going to be building a Magic Leap Coffee app! The idea is very straightforward; we will begin by creating a screen which will make a network request to ​Endpoint A. ​Calling this endpoint will return a list of coffee objects in JSON format. Each coffee object will contain information about a type of coffee (e.g. description, image, id, name). We will then present this information in a list following the design mock-up available below. The second part of this assignment is also very simple; when a user taps on a list item, it will then take the user to a second screen which contains more information regarding the selected coffee item. To capture additional information for the selected coffee, you will be making a network request to Endpoint B ​and presenting the information following the mock-up below.
Endpoints
Endpoint A (GET) - Retrieve coffee list
https://demo6983184.mockable.io/coffees
Headers: Accept - application/json
Endpoint B (GET) - Retrieve additional information for a particular coffee
https://demo6983184.mockable.io/coffees/{coffee_id_goes_here}
Headers: Accept - application/json
Basic Requirements

Your app must be built using Android Studio
Target Android 6.0.1 (API 23) and up
Must be able to run in the emulator
Must follow the general design provided in mock-ups
May use any third-party libraries
Include a README file with a summary of any decisions (design or technical) not listed here
Make app offline usable through any means other than network caching

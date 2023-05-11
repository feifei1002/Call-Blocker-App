**Mobile Development 2022/23 Portfolio**
# Requirements

Student ID: 21081270

_Complete the information above and then enumerate your functional and non functional requirements below.__


#### Functional Requirements
- The users must be able to navigate to different fragments using Bottom Navigation Bar.
- When the user accept the permission to read the Call Log, the Home Page should display a list of phone numbers with name, phone number, date and call type.
- When the user click on the name field of a phone number in Home Fragment, the user should be direct to the Search Fragment.
- When the user click on the name field of the phone number in Home Fragment, the number must appear on the SearchView in the Search Fragment.
- If there is phone number being shown in the SearchView, when the user click on the Report button, the phone number must appear on the phone number field in the Report form.
- If there is phone number being shown in the SearchView, when the user click on the Block button, a dialog must appear.
- The dialog must contains the phone number that the user want to block.
- If the user click on "Yes" button in the dialog, the phone number must be add to the block list shown in the Block Fragment.
- If the user click on "Cancel" button in the dialog, the user should stay in the Search Fragment.
- If the user want to block a number twice, a Toast message should appear that say "Sorry you already blocked this phone number".
- The Block Fragment should shows the phone number that the user has blocked.
- When the user close down the application and reopen the application again, the list must still display data in both Block and Report List Fragments.
- When the user click on back button on the phone, the application should not close.
- When the user enter a phone number in the search bar and submit the query, the fragment must display the phone number, phone type, phone region, country and the carrier.
- When the user navigate to the Search Fragment using the Bottom Navigation Bar, the search bar should be blank.
- When the user submit a report form, the new report should be added to the Report list in Report List Fragment.
- When the user click on the Report button in Report List fragment, they should be redirect to the Report form.


#### Non-functional Requirements
- Font style and size across the app should be consistent.
- Font style, size and colour should be easy for user to read.
- Background colour of the app should be consistent.
- When an user change the phone to dark mode, the app should be display in dark mode theme as well.
- When an user launch the app for the first time, the app should ask for permissions for reading Call Log and Call State.
- The Bottom Navigation Bar should be consistent throughout the application no matter what action the application is performing.
- The icons used in the application should be easy for users to recognise and understand.
- The same phone number should not appear on the Block List twice.
- The application should be usable in both portrait and landscape mode.
- All the UI should have content description.
- For every phone number in the list being displayed in the Home Fragment, there should be name, phone number, date and the call type being shown.
- If there is no name being assign to the phone number, the name field should show 'Unknown'.

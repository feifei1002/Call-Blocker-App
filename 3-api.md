**Mobile Development 2022/23 Portfolio**
# API description

Student ID: 21081270

_Complete the information above and then write your 300-word API description here.__

One of the main functions of my application is to be able to search for a phone number and get information about it. Instead of creating my own database and storing billions of phone numbers, I have chosen to use the Veriphone API, which is a free API that has a database available for phone number lookup. This has saved me a lot of time, as I only need to implement the API through Volley in the application, and the phone number that the user wants to search would be replaced in the URL together with an API key that I have registered to access the data of the phone number. Therefore, the parts of my application that used the Room database are now only used to store the Report form that the user submits and a list of phone numbers that the user blocks. Since the data for this would be much smaller since not all the phone numbers would be blocked or reported.

Another feature that I have included in my application is a dialog when a user wants to block a phone number. According to Android Studio documentation, a dialog allows the user to make a decision, and an action must be taken before they can proceed (Android Developers, n.d). In the application, the user could have blocked the phone number directly when they clicked on the Block button. However, it is better to double check with the user before they make the decision to block the phone number so that they have a chance to change their minds if necessary. As in real-life practise, blocking a phone number means that the user will not receive any phone calls, messages, or voice mail from the phone number that they blocked.


#### References
Android Developers. [no date]. Dialogs. Available at: https://developer.android.com/develop/ui/views/components/dialogs [Accessed: 11 May 2023].

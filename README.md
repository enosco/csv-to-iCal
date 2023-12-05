# csv-to-iCal
File Input: Tab-delineated text file retrived from Rowan University's Section Tally site  
User Input: Any number of course registration numbers (CRN); 'q' to quit  
Output: iCalender (.ics) file with events corresponding to the requested courses  

Notes:
Text file must be Tab-delineated with all course information on the same line; there is an awk program that can help with this  

The constructor in the Course class currently takes information from specific columns:  
[1] - CRN  
[4] - Course title  
[6] - Meeting Day  
[7] - Start time  
[8] - End time  
[9] - Building  
[10] - Room num

Any file with differing columns with produce an invalid Course object, leading to garbage output. 

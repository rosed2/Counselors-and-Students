# Counselors-and-Students
This is a program that allows students to easily interact and communicate with their counselors.

This program creates the counselors' accounts from the text file "CounselorSchedules.txt". To customize the counselors, 
create a file with the same title or edit the file and list their information. The format is (do not include the numbers):
1. First Name
2. Last Name
3. Password
4. First Period
5. Second Period
6. Third Period
7. Fourth Period
8. Fifth Period
9. Sixth Period
10. Seventh Period
11. Eighth Period

Repeat this format starting on the very next line for the next counselor's information.

The program creates the students' accounts from the text files "Counselor[0]Students.txt", "Counselor[1]Students.txt", and
"Counselor[2]Students.txt". The number of these files should correspond with how many counselors there are. Each counselor 
gets their own file of students. The first counselor's information you created in "CounselorSchedule.txt" corresponds to 
the students in the file "Counselor[0]Students.txt" and so forth. You can create any number of counselors and students.
The titles of the text files that contain the students' information should be of the format "Counselor[]Students.txt"
where there is some number between the square brackets. The number between the square brackets should be one less than
the place the corresponding counselor is in "CounselorSchedules.txt". So the first counselor's (in "CounselorSchedules.txt")
students are in "Counselor[0]Students.txt", the second counselor's students are in "Counselor[1]Students.txt" and so forth.
The students' information in the text files should be the same format as the counselors' information in "CounselorSchedules.txt".  

Each user has a user name and password that they use to sign in. Their user name is their first name and their last 
name together without a space in between and with the first letter of each capitalized. For example, if their name was "John Doe", their 
user name would be "JohnDoe". Their password was set in the text files "Counselor[]Students.txt".

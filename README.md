# cs465-assignment1-kryzstofkudlak

This project is written in Java as a Gradle project. I used the LOUD VM version 20.04 
(https://lcseesystems.wvu.edu/services/loud) and created my project using the default 
installation of Eclipse along with the accompanying default JDK (16) and Gradle 
version (7.4).

To import my project into Eclipse, first open Eclipse and select **File > Import…**

Next, select **Gradle > Existing Gradle Project** from the popout menu. Then click the 
**Next >** button.

On the next page, select **Browse…** next to the project root directory input space, and 
find my project on your machine. 

Finally, click **Finish**. 

The main method of my program exists in **Main.java** which can be found in the src/main/java 
package kryzstofkudlak-assignment1. A full run of the program is executed by running 
this file as a Java application. 

The program first prompts the user to specify which test case they would like to run. If the 
user enters anything other than 1, 2, or 3, the program will throw an exception and crash. 
This is intentional as the target user is expected to be competent enough to give appropriate 
input, and it was not required that I implement this feature in the first place. Please just 
give it friendly input. 

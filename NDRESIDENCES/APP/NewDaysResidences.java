// Package declaration for the main application
package NDRESIDENCES.APP;

// Importing necessary classes from the project
import NDRESIDENCES.LOGINSYSTEM.SignupSignin;
import NDRESIDENCES.LOGINSYSTEM.UserPass;

// Main class representing the entry point of the application
public class NewDaysResidences {
    // Main method where the application starts execution
    public static void main(String[] args) {
        // Creating an instance of the UserPass class to manage user credentials
        UserPass userPass = new UserPass();

        // Creating an instance of the SignupSignin class to handle user signup and signin
        new SignupSignin(userPass);
    }
}
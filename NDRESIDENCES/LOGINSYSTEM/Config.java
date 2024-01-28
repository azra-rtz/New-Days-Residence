// Package declaration for the login system
package NDRESIDENCES.LOGINSYSTEM;

// Import statements
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

// Abstract class representing configuration settings
public abstract class Config {
    
    // HashMap to store login information (username and password)
    protected HashMap<String, String> loginInfo;
    
    // ObjectMapper for converting JSON data to Java objects and vice versa
    protected ObjectMapper objectMapper;
    
    // Filepath for storing user information in JSON format
    protected String filepath = "NDRESIDENCES\\ASSETS\\USERS\\users.json";
    
    // Constructor (if any) could be added here for initializing objects
    
    // Any additional methods or variables related to configuration can be added below
}
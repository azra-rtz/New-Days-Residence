// Package declaration for the login system
package NDRESIDENCES.LOGINSYSTEM;

// Jackson library imports for JSON handling
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

// Java I/O and Collections imports
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

// Class representing user credentials management, extends Config
public class UserPass extends Config {

    // Constructor for UserPass class
    public UserPass() {
        // Initialize loginInfo and objectMapper
        this.loginInfo = new HashMap<>();
        this.objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        // Load user credentials from JSON file
        loadFromJson();
    }

    // Protected method to get the login information
    protected HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }

    // Method to register a new user with a username and password
    public void registerUser(String username, String password) {
        // Add user credentials to the loginInfo map and save to JSON
        loginInfo.put(username, password);
        saveToJson();
    }

    // Private method to load user credentials from a JSON file
    private void loadFromJson() {
        try {
            File file = new File(filepath);

            // Check if the JSON file exists and is not a directory
            if (file.exists() && !file.isDirectory()) {
                // Read the JSON file into the loginInfo map using Jackson
                loginInfo = objectMapper.readValue(file, new TypeReference<HashMap<String, String>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Private method to save user credentials to a JSON file
    private void saveToJson() {
        try {
            File file = new File(filepath);
            
            // Write the loginInfo map to the JSON file using Jackson
            objectMapper.writeValue(file, loginInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
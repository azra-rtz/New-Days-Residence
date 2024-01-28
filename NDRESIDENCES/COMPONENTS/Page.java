package NDRESIDENCES.COMPONENTS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import NDRESIDENCES.BACKEND.RentSystem;

// Page class extending RoundedPanel
public class Page extends RoundedPanel {
    // Components
    protected RentSystem renting = new RentSystem();
    protected String filepath = "NDRESIDENCES\\ASSETS\\USERS\\" + getStringFromFile() + ".json";
    protected String user = getStringFromFile();

    // Constructor
    public Page(int radius) {
        super(radius);
    }

    // Getter method for file path
    public String getFilePath() {
        return this.filepath;
    }

    // Save content to a file
    public void saveStringToFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("NDRESIDENCES\\ASSETS\\USERS\\CurrentUser.txt"))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read content from a file
    public String getStringFromFile() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("NDRESIDENCES\\ASSETS\\USERS\\CurrentUser.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}

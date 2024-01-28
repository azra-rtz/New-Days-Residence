package NDRESIDENCES.BACKEND;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Apartment implements Rental {

    // Fields to store information about the apartment
    private String roomNumber;
    private int capacity;
    private int floor;
    private double price;

    // Fields to track rental status and tenant information
    private boolean isAvailable;
    private String tenantName;
    private String contactNumber;
    private String currentAddress;

    // List to store transaction history
    private ArrayList<Map<String, String>> transactionHistory;

    // Constructor to initialize the apartment with basic details
    public Apartment(String roomNumber, int capacity, int floor, double price) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.floor = floor;
        this.price = price;

        // Default values for rental status and tenant information
        this.isAvailable = true;
        this.tenantName = null;
        this.contactNumber = null;
        this.currentAddress = null;

        // Initialize an empty list for transaction history
        this.transactionHistory = new ArrayList<>();
    }

    // Method to format apartment details for display or logging
    public HashMap<String, Object> Format() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("roomNumber", this.roomNumber);
        map.put("capacity", this.capacity);
        map.put("floor", this.floor);
        map.put("price", this.price);
        map.put("isAvailable", this.isAvailable);
        map.put("tenantName", this.tenantName);
        map.put("contactNumber", this.contactNumber);
        map.put("currentAddress", this.currentAddress);
        map.put("transactionHistory", this.transactionHistory);
        return map;
    }

    // Implementing the 'rent' method from the Rental interface
    @Override
    public void rent(String tenantName, String contactNumber, String currentAddress) {
        if (this.getIsAvailable()) {
            // Update apartment details and set it as not available
            this.setIsAvailable(false);
            this.setTenantName(tenantName);
            this.setContactNumber(contactNumber);
            this.setCurrentAddress(currentAddress);

            // Record the rent transaction in the history
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            HashMap<String, String> transaction = new HashMap<>();
            transaction.put("action", "Rent");
            transaction.put("tenant_name", tenantName);
            transaction.put("contact_number", contactNumber);
            transaction.put("current_address", currentAddress);
            transaction.put("date", dateFormat.format(new Date()));
            transaction.put("time", timeFormat.format(new Date()));
            this.addTransaction(transaction);

            System.out.println("The apartment has been successfully rented.");
        } else {
            System.out.println("The apartment is currently inhabited.");
        }
    }

    // Implementing the 'leave' method from the Rental interface
    @Override
    public void leave() {
        if (!this.getIsAvailable()) {
            // Update apartment details and set it as available
            this.setIsAvailable(true);
            this.setTenantName(null);
            this.setContactNumber(null);
            this.setCurrentAddress(null);

            // Record the leave transaction in the history
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            HashMap<String, String> transaction = new HashMap<>();
            transaction.put("action", "Leave");
            transaction.put("tenant_name", this.getTenantName());
            transaction.put("contact_number", this.getContactNumber());
            transaction.put("current_address", this.getCurrentAddress());
            transaction.put("date", dateFormat.format(new Date()));
            transaction.put("time", timeFormat.format(new Date()));
            this.addTransaction(transaction);

            System.out.println("The apartment has been vacated successfully.");
        } else {
            System.out.println("The apartment is unoccupied.");
        }
    }

    // Getter methods for tenant information
    @Override
    public String getTenantName() {
        return this.tenantName;
    }

    @Override
    public String getContactNumber() {
        return this.contactNumber;
    }

    @Override
    public String getCurrentAddress() {
        return this.currentAddress;
    }

    // Getter methods for apartment details
    public String getRoomNumber() {
        return this.roomNumber;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    // Getter method for transaction history
    public List<Map<String, String>> getTransaction() {
        return this.transactionHistory;
    }

    // Setter methods for updating apartment details
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    // Method to add a transaction to the history
    public void addTransaction(HashMap<String, String> transaction) {
        transactionHistory.add(transaction);
    }

    // Setter method to set the entire transaction history
    public void setTransactionHistory(ArrayList<Map<String, String>> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}

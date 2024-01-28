package NDRESIDENCES.BACKEND;

// Rental interface outlines the basic methods for managing apartment rentals
public interface Rental {

    // Method to initiate the rental of an apartment
    void rent(String tenantName, String contactNumber, String currentAddress);

    // Method to handle a tenant leaving and vacating the apartment
    void leave();

    // Getter method to retrieve the tenant's name
    String getTenantName();

    // Getter method to retrieve the tenant's contact number
    String getContactNumber();

    // Getter method to retrieve the tenant's current address
    String getCurrentAddress();
}
package NDRESIDENCES.BACKEND;

import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.*;

// RentSystem class manages the overall rental system, including apartments, transactions, and data persistence
public class RentSystem {
    private HashMap<String, Apartment> apartments;

    // Constructor initializes the RentSystem with an empty map of apartments
    public RentSystem() {
        this.apartments = new HashMap<>();
    }

    // Method to add an apartment to the system
    public void addApartment(Apartment apartment) {
        this.apartments.put(apartment.getRoomNumber(), apartment);
    }

    // Method to delete an apartment from the system by room number
    public boolean deleteApartment(String roomNumber) {
        Apartment apartment = this.apartments.get(roomNumber);
        
        if (apartment != null) {
            this.apartments.remove(roomNumber);
            return true;
        } else {
            return false;
        }
    }

    // Method to initiate the rental of an apartment
    public boolean rentApartment(String roomNumber, String tenantName, String contactNumber, String currentAddress) {
        Apartment apartment = apartments.get(roomNumber);

        if (apartment != null && apartment.getIsAvailable()) {
            // Update apartment details and record rent transaction
            apartment.setIsAvailable(false);
            apartment.setTenantName(tenantName);
            apartment.setContactNumber(contactNumber);
            apartment.setCurrentAddress(currentAddress);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            HashMap<String, String> transaction = new HashMap<>();
            transaction.put("action", "Rent");
            transaction.put("tenant_name", tenantName);
            transaction.put("contact_number", contactNumber);
            transaction.put("current_address", currentAddress);
            transaction.put("date", dateFormat.format(new Date()));
            transaction.put("time", timeFormat.format(new Date()));

            apartment.addTransaction(transaction);
            return true;
        } else {
            return false;
        }
    }

    // Method to handle a tenant leaving and vacating the apartment
    public boolean leaveApartment(String roomNumber, String tenantName, String contactNumber, String currentAddress) {
        Apartment apartment = this.apartments.get(roomNumber);

        if (apartment.getIsAvailable() == false) {
            // Update apartment details and record leave transaction
            apartment.setIsAvailable(true);
            apartment.setTenantName(null);
            apartment.setContactNumber(null);
            apartment.setCurrentAddress(null);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            HashMap<String, String> transaction = new HashMap<>();
            transaction.put("action", "Leave");
            transaction.put("tenant_name", tenantName);
            transaction.put("contact_number", contactNumber);
            transaction.put("current_address", currentAddress);
            transaction.put("date", dateFormat.format(new Date()));
            transaction.put("time", timeFormat.format(new Date()));

            apartment.addTransaction(transaction);
            return true;
        } else {
            return false;
        }
    }

    // Method to save apartment data to a JSON file
    public void saveToJson(String filename) {
        Map<String, Map<String, Object>> data = new HashMap<>();
        
        for (Map.Entry<String, Apartment> entry : this.apartments.entrySet()) {
            String roomNumber = entry.getKey();
            Apartment apartment = entry.getValue();
            data.put(roomNumber, apartment.Format());
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load apartment data from a JSON file
    @SuppressWarnings("unchecked")
    public void loadFromJson(String filename) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Map<String, Object>> data = objectMapper.readValue(new File(filename), new TypeReference<Map<String, Map<String, Object>>>(){});
    
            this.apartments = new HashMap<>();
    
            for (Map.Entry<String, Map<String, Object>> entry : data.entrySet()) {
                String unitNumber = entry.getKey();
                Map<String, Object> apartmentData = entry.getValue();
    
                Apartment apartment = new Apartment(
                        unitNumber,
                        (int) apartmentData.get("capacity"),
                        (int) apartmentData.get("floor"),
                        (double) apartmentData.get("price")
                );
    
                apartment.setIsAvailable((boolean) apartmentData.get("isAvailable"));
                apartment.setTenantName((String) apartmentData.get("tenantName"));
                apartment.setContactNumber((String) apartmentData.get("contactNumber"));
                apartment.setCurrentAddress((String) apartmentData.get("currentAddress"));
                apartment.setTransactionHistory((ArrayList<Map<String, String>>) apartmentData.get("transactionHistory"));
    
                this.apartments.put(unitNumber, apartment);
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   

    // Method to view all transactions across all apartments
    public List<Map<String, String>> viewTransaction() {
        List<Map<String, String>> allTransactions = new ArrayList<>();
    
        for (Map.Entry<String, Apartment> entry : apartments.entrySet()) {
            Apartment apt = entry.getValue();
    
            List<Map<String, String>> transactions = apt.getTransaction();
    
            for (Map<String, String> transaction : transactions) {
                transaction.put("room_number", apt.getRoomNumber());
            }
            allTransactions.addAll(transactions);
        }
        return allTransactions;
    }

    // Method to view details of all apartments
    public List<HashMap<String, Object>> viewApartments() {
        List<HashMap<String, Object>> apartmentList = new ArrayList<>();
        for (Map.Entry<String, Apartment> entry : this.apartments.entrySet()) {
            Apartment apt = entry.getValue();
    
            HashMap<String, Object> apartmentDetails = apt.Format();
            apartmentList.add(apartmentDetails);
        }
        return apartmentList;
    }    
}
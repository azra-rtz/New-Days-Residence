package NDRESIDENCES.BACKEND;

import java.util.*;

// PremiumApt class extends Apartment, representing a premium apartment type
public class PremiumApt extends Apartment {

    // Constructor for PremiumApt, inheriting from Apartment with default capacity and price
    public PremiumApt(String roomNumber, int floor) {
        super(roomNumber, 6, floor, 60000);
    }

    // Override Format method to provide specific formatting for PremiumApt
    @Override
    public HashMap<String, Object> Format() {
        // Delegates formatting to the Format method of the superclass (Apartment)
        return super.Format();
    }
}
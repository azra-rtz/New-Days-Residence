package NDRESIDENCES.BACKEND;

import java.util.*;

// BasicApt class extends Apartment, representing a basic apartment type
public class BasicApt extends Apartment {

    // Constructor for BasicApt, inheriting from Apartment with default capacity and price
    public BasicApt(String roomNumber, int floor) {
        super(roomNumber, 2, floor, 20000);
    }

    // Override Format method to provide specific formatting for BasicApt
    @Override
    public HashMap<String, Object> Format() {
        // Delegates formatting to the Format method of the superclass (Apartment)
        return super.Format();
    }
}
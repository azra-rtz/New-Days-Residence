package NDRESIDENCES.BACKEND;

import java.util.*;

// ClassicApt class extends Apartment, representing a classic apartment type
public class ClassicApt extends Apartment {

    // Constructor for ClassicApt, inheriting from Apartment with default capacity and price
    public ClassicApt(String roomNumber, int floor) {
        super(roomNumber, 4, floor, 40000);
    }

    // Override Format method to provide specific formatting for ClassicApt
    @Override
    public HashMap<String, Object> Format() {
        // Delegates formatting to the Format method of the superclass (Apartment)
        return super.Format();
    }
}
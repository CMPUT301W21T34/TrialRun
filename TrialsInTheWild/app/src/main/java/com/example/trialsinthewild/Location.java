package com.example.trialsinthewild;

import com.google.firebase.firestore.GeoPoint;

/**
 * Precise location related to GPS - most likely replace later with actual android GPS entity
 */
public class Location extends GeoPoint {
    private double longitude;
    private double lattitude;

    public Location(double lattitude, double longitude) {
        super(lattitude, longitude);
        // constructor
    }
}

package com.example.trialsinthewild;

import com.google.firebase.firestore.GeoPoint;

/**
 * Region - precise location + description and range around that point
 */
public class Region {
    private GeoPoint location;
    private String description;
    private double range;
    private int region_id;

    public Region(GeoPoint location, String description, double range, int region_id) {
        // constructor
        this.location=location;
        this.description=description;
        this.range=range;
        this.region_id=region_id;
    }

    public boolean locationInRange(Location loc) {
        return false;
    }

    public int getRegionId() {
        return region_id;
    }
}

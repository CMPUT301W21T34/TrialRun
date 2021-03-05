package com.example.trialsinthewild;

/**
 * Region - precise location + description and range around that point
 */
public class Region {
    private Location location;
    private String description;
    private double range;

    public Region() {
        // constructor
    }

    public boolean locationInRange(Location loc) {
        return false;
    }
}

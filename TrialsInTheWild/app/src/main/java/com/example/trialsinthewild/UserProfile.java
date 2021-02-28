package com.example.trialsinthewild;

/*
    User profile
    US 04.01.01
    As an owner or experimenter, I want a profile with a UNIQUE username and my contact information.
    US 04.02.01
    As an owner or experimenter, I want to edit the contact information in my profile.
    US 04.03.01
    As an owner or experimenter, I want to retrieve and show the profile of a presented username.
 */

public class UserProfile {
    private String contact_info;
    private String username;

    public UserProfile() {
        // constructor
    }

    public String getContactInfo() {
        return contact_info;
    }

    public void setContactInfo(String contact_info) {
        this.contact_info = contact_info;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        // Need to make sure the username is UNIQUE before changing it
        // Maybe make this a boolean and return false if name cannot be changed.
        this.username = username;
    }
}

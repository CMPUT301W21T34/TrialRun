package com.example.trialsinthewild;

import java.util.ArrayList;

    /*
        User profile
        US 04.01.01
        As an owner or experimenter, I want a profile with a UNIQUE username and my contact information.
        US 04.02.01
        As an owner or experimenter, I want to edit the contact information in my profile.
        US 04.03.01
        As an owner or experimenter, I want to retrieve and show the profile of a presented username.

        - For user profiles - do not use login/registration. Make a default ID at the start and associate a username with it.
          The professor is not concerned about the ability to retrieve a useraccount - we only have to store their ID
          locally on the device - if they clear the cache then they won't be able to get the account back and that's okay.
        - User ID's should be global.
        - Generate an ID and let them fill in the username - save the ID
        - Subscription is just device specific.
        - Subscription just means you are paying attention to and can easily access experiments
            it doesn't grant you special status for extra details about an experiment.

        - You can participate in an experiment without subscribing to it
            If they add a trial to an experiment you should probably subscribe them automatically.

        - Experimenters who add trials to an experiment should be warned that geolocation is being recorded and submitted.
            I think this also means that the Trials had to have geo location data in them to begin with.

        - Owners can subscribe to their own trials.


        I am confused about the language of 'experimenter' and 'owner' - it seems like everyone is a 'user' and they
        can edit their own experiments (which they are an owner of) and they can participate in other experiments, which
        they are 'experimenters' in. I'm not sure if we need different classes for each, it's not like an account can only
        be one or the other.
     */

public class User {

    private String contact_info;
    private String username;
    private int userId;
    private ArrayList<Integer> subscribed_experiments;        // These can be stored locally - pretty much just a watchlist
    private ArrayList<Integer> owned_experiments;

    public User() {

    }
}

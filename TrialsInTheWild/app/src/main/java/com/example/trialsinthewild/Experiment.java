package com.example.trialsinthewild;

import java.util.ArrayList;
import java.util.Date;

/**
 * This is a JavaDoc comment, it will appear next to the Entity that's after it. Use it to describe what the function/class does
 * 'Experiment' will show this entire comment.
 */
public class Experiment {
    /*
        US 06.01.01
        As an owner, I want to specify a Geo-location is required or not for trials.
                - Experiments with geo-location enforced means all trials must have a geolocation entry. / Experiments can force all trials to have a location
                - Only trials have a specific location.
                - Experiments do not need to have a 'region'
                - Region can be a textual description of where the trials should be done. Can also be a geolocation+radius (or both)
                - When it is not enforced, assume that geolocations can be added and will be collected and shown for the trial regardless
        US 06.02.01
        As an experimenter, I want to add Geo-location to experimental trials that need it.
            - Experimenters can manually set their geo-location/edit where the trial was after the fact. (Seems wrong but whatever)
        US 06.03.01
        As an experimenter, I want to be warned about geo-location trials.
        US 06.04.01
        As an experimenter, I want to see a map of geo-locations of a geo-location enabled experiment.
            - Both owner and experimenter can see the map of trials.
            - Individual dots should represent a single trial on the map



        US 01.01.01
        As an owner, I want to publish an experiment with a description, a region, and a minimum number of trials.
            - Experiments should only be 1 type of test/allow one type of trial.
        US 01.02.01
        As an owner, I want to unpublish an experiment.
        US 01.03.01
        As an owner, I want to end an experiment. This leaves the results available and public but does not allow new results to be added.
        US 01.04.01
        As an owner or experimenter, I want to subscribe to an experiment to participate in it.
            - Subscription just means you are paying attention to and can easily access experiments it doesn't grant you special status for
              extra details about an experiment.
        US 01.05.01
        As an experimenter, I want to be able to execute trials for an experiment and upload them to the experiment.
        US 01.04.01
        As an owner, I want to ignore certain experimenters results.
            - When it comes to ignore certain experimenters results - you ignore ALL of their trails, maybe make a blacklist per experiment.
        US 01.05.01
        As an owner or experimenter, I want to observe statistics (quartiles, median, mean, stdev) about a current trials.
        US 01.06.01
        As an owner or experimenter, I want to see histograms of the results of trials.
        US 01.07.01
        As an owner or experimenter, I want to see plots of the results of trials over time.

     */

    public final static int TYPE_BINOMIAL = 0;
    public final static int TYPE_COUNT = 1;
    public final static int TYPE_NON_NEGATIVE = 2;
    public final static int TYPE_MEASUREMENT = 3;

    public final static int STATUS_CLOSED = 0;
    public final static int STATUS_OPEN = 1;


    private int experiment_id;
    private int owner_id;
    private int status;
    private boolean published;
    private String description;
    private Date date;
    private Location region;
    private int minimum_trials;
    private int type;
    // How do we handle discussions (Questions/Answers?)
    private ArrayList<Message> questionList;

    public Experiment(int experiment_id, int owner_id, String description, Location region, int minimum_trials, int type) {
        this.experiment_id=experiment_id;
        this.owner_id=owner_id;
        this.description=description;
        this.region=region;
        this.minimum_trials=minimum_trials;

        this.date = new Date();
        this.published=false;
        this.status=0; // STATUS_ENDED, STATUS_OPEN
        this.type = type;
    }

    /**
     * Gets the experiment Id of Experiment object
     * @return experiment_id
     */
    public int getExperiment_id() {
        return experiment_id;
    }

    /**
     * Gets the id of the owner who started the experiment
     * @return int owner_id
     */
    public int getOwnerId() {
        return owner_id;
    }

    /**
     * Change who owns the experiment, owner_id references a User
     * @param owner_id
     */
    public void setOwnerId(int owner_id) {
        this.owner_id = owner_id;
    }

    /**
     * Get status of the experiment
     * @return OPEN or ENDED
     */
    public int getStatus() {
        return status;
    }

    /**
     * Set the status of the experiment
     * @param status set to either OPEN or ENDED
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Get description of the experiment
     * @return String containing the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description of the experiment
     * @param description sets description of the experiment to this string
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the date the experiment was originally made
     * @return Date object
     */
    public Date getDate() {
        return date;
    }


    /**
     * Sets the date the experiment was created
     * @param date 'Date' object to set the date to
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Get the 'Region' object of the current experiment
     * @return 'Region' object
     */
    public Location getRegion() {
        return region;
    }

    /**
     * Sets the 'Region' of the current experiment
     * @param region 'Region' object, contains precise gps location, range around it, and a description
     */
    public void setRegion(Location region) {
        this.region = region;
    }

    /**
     * Gets the minimum number of required trials to submit results to an experiment
     * @return int - number of trials required
     */
    public int getMinimumTrials() {
        return minimum_trials;
    }

    /**
     * Sets the minimum number of required trials to be able to submit results to an experiment
     * @param minimum_trials int - number required to submit
     */
    public void setMinimumTrials(int minimum_trials) {
        this.minimum_trials = minimum_trials;
    }

    /**
     * Is the current trial currently published and available for participation/viewable?
     * @return true - yes, false - no
     */
    public boolean isPublished() {
        return published;
    }

    /**
     * Update whether or not the experiment is published
     * @param published true - yes, false - no
     */
    public void setPublished(boolean published) {
        this.published = published;
    }

    /**
     * Get the trial type of current experiment (Binomial trial, counting trial, measurement, non-negative)
     * @return type of experiment
     */
    public int getType() {
        return type;
    }

    /**
     * Set the trial type of current experiment (Binomial trial, counting trial, measurement, non-negative)
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }
}

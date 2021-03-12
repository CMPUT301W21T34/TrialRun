package com.example.trialsinthewild;

import java.util.Date;

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

/**
 * This is a JavaDoc comment, it will appear next to the Entity that's after it. Use it to describe what the function/class does
 * 'Experiment' will show this entire comment.
 */
public class Experiment {

    final public static int TYPE_BINOMIAL = 0;
    final public static int TYPE_COUNT = 1;
    final public static int TYPE_NON_NEGATIVE = 2;
    final public static int TYPE_MEASUREMENT = 3;

    final public static int STATUS_ENDED = 0;
    final public static int STATUS_OPEN = 1;

    private int experiment_id;
    private int owner_id;
    private int status;
    private boolean published;
    private String description;
    private Date date;
    private Region region;
    private int minimum_trials;
    private int type;
    // How do we handle discussions (Questions/Answers?)

    public Experiment(int experiment_id, int owner_id, String description, Region region, int minimum_trials, int type) {
        this.experiment_id=experiment_id;
        this.owner_id=owner_id;
        this.description=description;
        this.region=region;
        this.minimum_trials=minimum_trials;

        this.date = new Date();
        this.published=false;
        this.status=0; // STATUS_ENDED, STATUS_OPEN, STATUS
        this.type = type;
    }

    /**
     * Description of what the below function does
     * @return Explanation of what this method returns
     */
    public int getExperiment_id() {
        return experiment_id;
    }

    /*public void setExperiment_id(int experiment_id) {
        this.experiment_id = experiment_id;
    }*/

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public int getMinimum_trials() {
        return minimum_trials;
    }

    public void setMinimum_trials(int minimum_trials) {
        this.minimum_trials = minimum_trials;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

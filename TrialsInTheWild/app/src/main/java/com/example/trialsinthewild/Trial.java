package com.example.trialsinthewild;

import com.google.firebase.firestore.GeoPoint;

/**
 * Represents a single Trial with a single outcome for a specific experiment
 */
public class Trial {
    /*
        Make subclasses for BinomialTrial, CountTrial, NonNegativeTrial, MeasurementTrial?

        Binomial trial outcomes = 0 or 1
        CountTrial = increase counter by 1
        NonNegativeTrial = a number between 0 and N
        MeasurementTrial = a value which needs to be float - unsure if it's ugly to handle all outcomes/results as a float to accommodate this
     */

    private GeoPoint loc;           // If required
    private double outcome;         // outcome of experiment, should work for all types
    private int experiment_id;      // id of experiment it's being run for - if we know this we implicitly know the type it should be
    private int experimenter_id;    // id of the experimenter running it
    private int trial_id;           // id of the trial itself

    public Trial(double outcome, int experiment_id, int experimenter_id, int trial_id) {
        this.loc = null;
        this.outcome = outcome;
        this.experiment_id=experiment_id;
        this.experimenter_id=experimenter_id;
        this.trial_id=trial_id;
    }

    public Trial(double outcome, int experiment_id, int experimenter_id, int trial_id, GeoPoint loc) {
        this.loc = loc;
        this.outcome = outcome;
        this.experiment_id=experiment_id;
        this.experimenter_id=experimenter_id;
        this.trial_id=trial_id;
    }

    public int getExperimentId() {
        return experiment_id;
    }

    public void setExperimentId(int experiment_id) {
        this.experiment_id = experiment_id;
    }

    public void setLocation(GeoPoint loc) {
        this.loc=loc;
    }

    public void setOutcome(double outcome) {
        this.outcome=outcome;
    }

    public int getExperimenterId() {
        return experimenter_id;
    }

    public void setExperimenterId(int experimenter_id) {
        this.experimenter_id = experimenter_id;
    }

    public boolean hasLocation() {
        return loc!=null;
    }

    public GeoPoint getLocation() {
        return loc;
    }

    public int getTrialId() {
        return trial_id;
    }
}

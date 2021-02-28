package com.example.trialsinthewild;

public class Trial {
    /*
        Make subclasses for BinomialTrial, CountTrial, NonNegativeTrial, MeasurementTrial?

        Binomial trial outcomes = 0 or 1
        CountTrial = increase counter by 1
        NonNegativeTrial = a number between 0 and N
        MeasurementTrial = a value which needs to be float - unsure if it's ugly to handle all outcomes/results as a float to accommodate this
     */


    private Location loc; // If required
    private double outcome; //
    private Experiment.Type type; //

    public Trial(double result, Experiment.Type type) {
        this.loc = null;
        this.outcome = outcome;
        this.type = type;
    }

    public Trial(double result, Experiment.Type type, Location loc) {
        this.loc = loc;
        this.outcome = outcome;
        this.type = type;
    }
}

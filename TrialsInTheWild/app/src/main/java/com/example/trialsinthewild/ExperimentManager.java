package com.example.trialsinthewild;

import java.util.ArrayList;

public class ExperimentManager {
    private ArrayList<Experiment> experiments;
    private static ExperimentManager instance;

    private ExperimentManager() {
        // The single initialization of this class
    }

    public ExperimentManager getInstance() {
        if(instance == null) {
            instance = new ExperimentManager();
        }
        return instance;
    }

    public Experiment GetExperiment(int experiment_id) {
        return experiments.get(experiment_id);
    }

    public void loadExperiment(int experiment_id) {
        // Load from database
    }

    public void addExperiment() {
        // add experiment to current manager + database?
    }

    public void publishExperiment() {

    }

    public void unpublishExperiment() {

    }

    public void submitTrialsForReview() {

    }

    public void uploadTrials() {

    }

    public void getTrialsForReview() {

    }

    public void declineReviewedTrial() {

    }
}

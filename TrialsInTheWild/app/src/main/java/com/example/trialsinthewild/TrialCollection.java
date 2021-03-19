package com.example.trialsinthewild;

import java.util.ArrayList;

/**
 * This class contains a set of trials that need to be grouped together
 * This may be for an experimenter submitting Trials to be reviewed by the owner
 * This may be for an experiment which contains all the reviewed and accepted trials
 */
public class TrialCollection {
    private int collection_id;
    private ArrayList<Integer> trials;

    public TrialCollection() {
        trials = new ArrayList<>();
    }

    public void loadTrial(int trial_id) {
        trials.add(trial_id);
    }
}

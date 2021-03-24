package com.example.trialsinthewild;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class TrialManager {
    private static TrialManager instance;
    private static FirebaseFirestore db;
    private static ArrayList<Trial> trials;
    private static ArrayList<TrialCollection> trialCollections;
    private static CollectionReference ref;

    private TrialManager() {
        db = FirebaseFirestore.getInstance();
        ref = db.collection("Trials");
        trials = new ArrayList<>();
        instance = this;

        loadTrials();
    }

    private void processTrialSnapshot(QueryDocumentSnapshot doc) {
        int trial_id = Integer.parseInt(doc.getId());
        Timestamp date = doc.getTimestamp("date");
        int experiment_id = ((Long)doc.get("experiment_id")).intValue();
        int experimenter_id = ((Long)doc.get("experimenter_id")).intValue();
        GeoPoint location = doc.getGeoPoint("location");
        double outcome = doc.getDouble("outcome");
        Trial trial = getTrial(trial_id);
        if(trial == null) {
            buildTrial(trial_id, experiment_id, experimenter_id, location, outcome);
        } else {
            updateTrial(trial, experiment_id, experimenter_id, location, outcome);
        }
    }

    private void updateTrial(Trial trial, int experiment_id, int experimenter_id, GeoPoint loc, double outcome) {
        // Honestly don't know when this would ever be used
        // TODO: updateTrial - Don't forget about me!
        trial.setExperimenterId(experimenter_id);
        trial.setExperimentId(experiment_id);
        trial.setLocation(loc);
        trial.setOutcome(outcome);
    }

    private void buildTrial(int trial_id, int experiment_id, int experimenter_id, GeoPoint loc, double outcome) {
        Trial trial = new Trial(outcome, experiment_id, experimenter_id, trial_id, loc);
        trials.add(trial);
    }

    private void loadTrials() {
        ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Log.d("Loading in Trial: ", String.valueOf(doc.getId()));
                    processTrialSnapshot(doc);
                }
            }
        });

        // Changes made to the database, update them somehow
        ref.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (QueryDocumentSnapshot doc : value) {
                    Log.d("TrialManager: ", "Loading in updated Trial: " + String.valueOf(doc.getId()));
                }
            }
        });
    }

    public static TrialManager getInstance() {
        if (instance == null) {
            instance = new TrialManager();
        }
        return instance;
    }

    public static Trial getTrial(int trial_id) {
        for(Trial t : trials) {
            if(t!=null && t.getTrialId()==trial_id)
                return t;
        }
        return null;
    }
}

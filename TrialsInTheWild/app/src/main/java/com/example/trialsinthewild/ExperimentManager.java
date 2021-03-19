package com.example.trialsinthewild;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class ExperimentManager {

    public interface OnExperimentDataChange {
        public void dataChanged();
    }

    private static ExperimentManager instance;
    private static FirebaseFirestore db;
    private static ArrayList<Experiment> experiments;
    private static CollectionReference ref;

    private ExperimentManager() {
        experiments = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        ref = db.collection("Experiments");
        instance = this;

        loadExperiments();
    }

    public static ExperimentManager getInstance() {
        if(instance != null) {
            return instance;
        }
        return new ExperimentManager();
    }

    public Experiment getExperiment(int experiment_id) {
        for(Experiment e : experiments) {
            if(e!=null && e.getExperimentId()==experiment_id)
                return e;
        }
        return null;
    }

    public void createExperiment(int experiment_id, int owner_id, String description, int region_id, int minimum_trials, int type) {
        Region region = RegionManager.getRegion(region_id);
        Experiment experiment = new Experiment(experiment_id, owner_id, description, region, minimum_trials, type);
        experiments.add(experiment);
    }

    public void publishExperiment() {

    }

    public void unpublishExperiment() {

    }

    public void uploadTrialsForReview() {

    }

    public void uploadTrials() {

    }

    public void getTrialsForReview() {

    }

    public void declineReviewedTrial() {

    }

    private void loadExperiments() {
        ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Log.d("Loading in experiment: ", String.valueOf(doc.getId()));

                    int experiment_id = ((Long) doc.getData().get("experiment_id")).intValue();
                    int owner_id = ((Long) doc.getData().get("owner_id")).intValue();
                    String description = (String) doc.getData().get("description");
                    int region_id = ((Long)  doc.getData().get("region_id")).intValue();
                    int minimum_trials = ((Long) doc.getData().get("minimum_trials")).intValue();
                    int type = ((Long)  doc.getData().get("type")).intValue();
                    String date = (String) doc.getData().get("date");
                    boolean published = (Boolean) doc.getData().get("published");
                    createExperiment(experiment_id, owner_id, description, region_id, minimum_trials, type);
                }
            }
        });

        // Changes made to database externally, update them somehow
        ref.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (QueryDocumentSnapshot doc : value) {
                    Log.d("Loading in experiment: ", String.valueOf(doc.getId()));


                    int experiment_id = ((Long) doc.getData().get("experiment_id")).intValue();
                    int owner_id = ((Long) doc.getData().get("owner_id")).intValue();
                    String description = (String) doc.getData().get("description");
                    int region_id = ((Long)  doc.getData().get("region_id")).intValue();
                    int minimum_trials = ((Long) doc.getData().get("minimum_trials")).intValue();
                    int type = ((Long)  doc.getData().get("type")).intValue();
                    String date = (String) doc.getData().get("date");
                    boolean published = (Boolean) doc.getData().get("published");
                    Experiment experiment = getExperiment(experiment_id);
                    // TODO: add region_id stuff
                    if(experiment==null) {
                        createExperiment(experiment_id, owner_id, description, region_id, minimum_trials, type);
                    }
                }
                // Update all listeners somehow?
            }
        });
    }
}

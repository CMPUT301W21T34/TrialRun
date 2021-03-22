package com.example.trialsinthewild;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
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
        if(instance == null) {
            instance = new ExperimentManager();
        }
        return instance;
    }

    public Experiment getExperiment(int experiment_id) {
        for(Experiment e : experiments) {
            if(e!=null && e.getExperimentId()==experiment_id)
                return e;
        }
        return null;
    }

    // TODO: Hope these work
    public void publishExperiment(int experiment_id) {
        Experiment exp = getExperiment(experiment_id);
        if(exp == null) {
            Log.d("ExperimentManager: ", "publishExperiment("+String.valueOf(experiment_id)+") does not exist!");
            return;
        }
        updateExperiment(experiment_id, "published", Boolean.TRUE);
    }

    public void unpublishExperiment(int experiment_id) {
        Experiment exp = getExperiment(experiment_id);
        if(exp == null) {
            Log.d("ExperimentManager: ", "publishExperiment("+String.valueOf(experiment_id)+") does not exist!");
            return;
        }
        updateExperiment(experiment_id, "published", Boolean.FALSE);
    }

    public void uploadTrialsForReview() {

    }

    public void uploadTrials() {

    }

    public void getTrialsForReview() {

    }

    public void declineReviewedTrial() {

    }

    public boolean updateExperiment(int experiment_id, String field, Object value) {
        // TODO: some stuff
        ref.document(String.valueOf(experiment_id)).update(field, value);
        return true;
    }

    private void processExperimentSnapshot(QueryDocumentSnapshot doc) {
        int experiment_id = ((Long) doc.getData().get("experiment_id")).intValue();
        int owner_id = ((Long) doc.getData().get("owner_id")).intValue();
        String description = (String) doc.getData().get("description");
        int region_id = ((Long)  doc.getData().get("region_id")).intValue();
        int minimum_trials = ((Long) doc.getData().get("minimum_trials")).intValue();
        int type = ((Long)  doc.getData().get("type")).intValue();
        Timestamp date = doc.getTimestamp("date");
        boolean published = (Boolean) doc.getData().get("published");

        Experiment exp = getExperiment(experiment_id);
        if(exp == null) {
            createExperiment(experiment_id, owner_id, description, region_id, minimum_trials, type, date, published);
        } else {
            updateExperiment(experiment_id, owner_id, description, region_id, minimum_trials, type, date, published);
        }
    }

    private void updateExperiment(int experiment_id, int owner_id, String description, int region_id, int minimum_trials, int type, Timestamp date, boolean published) {
        Region region = RegionManager.getRegion(region_id);
        Experiment experiment = getExperiment(experiment_id);

        experiment.setDescription(description);
        experiment.setRegion(region);
        experiment.setOwnerId(owner_id);
        experiment.setMinimumTrials(minimum_trials);
        experiment.setType(type);
        experiment.setDate(date);
        experiment.setPublished(published);
    }

    public void createExperiment(int experiment_id, int owner_id, String description, int region_id, int minimum_trials, int type, Timestamp date, boolean published) {
        Region region = RegionManager.getRegion(region_id);
        Experiment experiment = new Experiment(experiment_id, owner_id, description, region, minimum_trials, type, date, published);
        experiments.add(experiment);
    }

    private void loadExperiments() {
        ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Log.d("Loading in experiment: ", String.valueOf(doc.getId()));
                    processExperimentSnapshot(doc);
                }
            }
        });

        // Changes made to database externally, update them somehow
        ref.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (QueryDocumentSnapshot doc : value) {
                    Log.d("Updating experiment: ", String.valueOf(doc.getId()));
                    processExperimentSnapshot(doc);
                }
            }
        });
    }
}

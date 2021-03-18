package com.example.trialsinthewild;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ExperimentManager {

    boolean isinitialized = false;
    private FirebaseFirestore db;
    private ArrayList<Experiment> experiments;

    public ExperimentManager() {
        experiments = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        CollectionReference ref = db.collection("Experiments");
        ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                //int owner_id = ((Long) queryDocumentSnapshots.getDocuments().get(0).get("owner_id")).intValue();
                //Log.d("tag",String.valueOf(owner_id));
                //List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();

                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Log.d("Loading in experiment: ", String.valueOf(doc.getId()));

                    // Probably a better way to do this

                    int experiment_id = ((Long) doc.getData().get("experiment_id")).intValue();
                    int owner_id = ((Long) doc.getData().get("owner_id")).intValue();
                    String description = (String) doc.getData().get("description");
                    int region_id = ((Long)  doc.getData().get("region_id")).intValue();
                    int minimum_trials = ((Long) doc.getData().get("minimum_trials")).intValue();
                    int type = ((Long)  doc.getData().get("type")).intValue();
                    String date = (String) doc.getData().get("date");
                    boolean published = (Boolean) doc.getData().get("published");
                    Experiment experiment = new Experiment(experiment_id, owner_id, description, null, minimum_trials, type);
                    if (experiments == null){
                        Log.d("experiments is null","yea");
                    }
                    experiments.add(experiment);
                    Log.d("Adding experiment: ", experiment.getDescription());

                }
            }
        });

    }

    public Experiment GetExperiment(int index) {
        return experiments.get(index);
    }

    public void AddExperiment() {

    }

    public void PublishExperiment() {

    }

    public void UnpublishExperiment() {

    }

    public void UploadTrialsForReview() {

    }

    public void UploadTrials() {

    }

    public void GetTrialsForReview() {

    }

    public void DeclineReviewedTrial() {

    }
}

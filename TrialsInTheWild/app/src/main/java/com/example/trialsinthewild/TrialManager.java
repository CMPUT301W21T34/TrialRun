package com.example.trialsinthewild;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
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

    /*
    private GeoPoint loc;           // If required
    private double outcome;         // outcome of experiment, should work for all types
    private int type;               // type of experiment/trial - may be redundant
    private int experiment_id;      // id of experiment it's being run for - if we know this we implicitly know the type it should be
    private int experimenter_id;    // id of the experimenter running it
    private int trial_id;           // id of the trial itself
     */

    private void loadTrials() {
        ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Log.d("Loading in Trial: ", String.valueOf(doc.getId()));

                    int trial_id = Integer.parseInt(doc.getId());
                    int type = ((Long) doc.getData().get("contact_info")).intValue();
                    String username = (String) doc.getData().get("username");
                    createTrial();
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

    private void createTrial() {
        // newly created trial objects when first loaded
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

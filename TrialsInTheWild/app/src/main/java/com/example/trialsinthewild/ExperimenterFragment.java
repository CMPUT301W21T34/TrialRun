package com.example.trialsinthewild;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ExperimenterFragment extends Fragment {

    private FirebaseFirestore db;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = FirebaseFirestore.getInstance();
        final CollectionReference collectionReference = db.collection("Experiments");

        ArrayList<Experiment> list = new ArrayList<>();
        ExperimentList adapter = new ExperimentList(view.getContext(), list);
        ListView listView = view.findViewById(R.id.subscribed_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Item selected: ", String.valueOf(position));
                /* The below code was just an example of adding a new experiment to the database
                   I just used the onClickItem listener to trigger it.
                Experiment experiment = new Experiment(1, 0, "Description for experiment 1", null, 0, 2);

                HashMap<String, Object> data = new HashMap<>();
                data.put("experiment_id", experiment.getExperimentId());
                data.put("owner_id", experiment.getOwnerId());
                data.put("description", experiment.getDescription());
                data.put("region_id", experiment.getRegionId());
                data.put("minimum_trials", experiment.getMinimumTrials());
                data.put("type", experiment.getType());
                data.put("date", experiment.getDate().toString());
                data.put("published", experiment.isPublished());

                collectionReference
                        .document(String.valueOf(experiment.getExperimentId()))
                        .set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("Firebase: ", "Data has been added successfully!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("Firebase: ", "Data can not be added!");
                            }
                        });

                 */
            }
        });


        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                // Clear the city data list
                list.clear(); // removes everything in our listview
                for (QueryDocumentSnapshot doc : value) {
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

                    list.add(experiment);
                    Log.d("Adding experiment: ", experiment.getDescription());
                }
                adapter.notifyDataSetChanged(); // reflects change on our UI
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_experimenter, container, false);
    }
}

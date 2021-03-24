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
//https://www.youtube.com/watch?v=fGcMLu1GJEc&t=567s
//navigation class are modified from Coding in Flow
public class ExperimenterFragment extends Fragment implements ExperimentManager.OnExperimentDataChange {
    private View view;
    private FirebaseFirestore db;
    private ArrayList<Experiment> list;
    private ExperimentList adapter;

    @Override
    public void dataChanged() {
        // something
    }

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
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_experimenter, container, false);
        return view;
    }
}

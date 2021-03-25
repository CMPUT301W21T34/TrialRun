package com.example.trialsinthewild;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//https://www.youtube.com/watch?v=fGcMLu1GJEc&t=567s
//navigation class are modified from Coding in Flow
public class ExperimenterFragment extends Fragment {
    private View view;
    UserManager um;
    ExperimentManager em;

    Button bntList;
    EditText etSearchField;

    ListView lvSubscribed;
    ListView lvSearched;

    ExperimentList expSubscribedAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ViewCreated: ", "ExperimentFrag");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_experimenter, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        /* Putting the code in onResume so it updates Subscribed list after subscribing to an experiment and navigating back */
        UserManager um = UserManager.getInstance();
        ExperimentManager em = ExperimentManager.getInstance();

        Button bntList = view.findViewById(R.id.btn_search_exp);
        EditText etSearchField = view.findViewById(R.id.et_exp_keyword_search);

        ListView lvSubscribed = view.findViewById(R.id.lv_subscribed_experiments);
        ListView lvSearched = view.findViewById(R.id.lv_exp_search_results);

        ArrayList<Experiment> subscribed_list = new ArrayList<>();
        for (int i : um.getSubscribedExperiments()) {
            Experiment experiment = em.getExperiment(i);
            if(experiment != null) {
                subscribed_list.add(experiment);
            }
        }
        ExperimentList expSubscribedAdapter = new ExperimentList(view.getContext(), subscribed_list);
        lvSubscribed.setAdapter(expSubscribedAdapter);

        bntList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = etSearchField.getText().toString();
                ExperimentManager em = ExperimentManager.getInstance();
                ArrayList<Experiment> search_list = em.searchExperimentsByKeyword(keyword);
                ExperimentList expSearchAdapater = new ExperimentList(view.getContext(), search_list);
                lvSearched.setAdapter(expSearchAdapater);
            }
        });

        lvSubscribed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Item selected: ", String.valueOf(position));
                Experiment experiment = (Experiment) parent.getItemAtPosition(position);
                goToExperiment(experiment.getExperimentId());
            }
        });

        lvSearched.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Item selected: ", String.valueOf(position));
                Experiment experiment = (Experiment) parent.getItemAtPosition(position);
                goToExperiment(experiment.getExperimentId());
            }
        });
    }

    private void goToExperiment(int experiment_id) {
        Intent intent = new Intent(this.getContext(),ExperimentDetailsActivity.class);
        intent.putExtra("experiment_id", experiment_id);
        startActivity(intent);
    }
}

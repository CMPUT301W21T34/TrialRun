package com.example.trialsinthewild;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OwnerFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView experimentList = view.getRootView().findViewById(R.id.published_list);
        ArrayList<Experiment> experimentDataList = new ArrayList<>();
        ArrayAdapter<Experiment> experimentAdapter = new ExperimentListAdapter(getActivity(), experimentDataList);
        experimentList.setAdapter(experimentAdapter);
        experimentDataList.add(new Experiment(1, 1, "Experiment 1", null, 5, Experiment.TYPE_BINOMIAL));
        experimentDataList.add(new Experiment(2, 1, "Experiment 2", null, 5, Experiment.TYPE_BINOMIAL));
        experimentDataList.add(new Experiment(3, 3, "Experiment 3", null, 5, Experiment.TYPE_BINOMIAL));
        experimentAdapter.notifyDataSetChanged();

        ListView experimentList2 = view.getRootView().findViewById(R.id.unpublished_list);
        ArrayList<Experiment> experimentDataList2 = new ArrayList<>();
        ArrayAdapter<Experiment> experimentAdapter2 = new ExperimentListAdapter(getActivity(), experimentDataList2);
        experimentList2.setAdapter(experimentAdapter2);
        experimentDataList2.add(new Experiment(4, 2, "Experiment 4", null, 5, Experiment.TYPE_BINOMIAL));
        experimentDataList2.add(new Experiment(5, 6, "Experiment 5", null, 5, Experiment.TYPE_BINOMIAL));
        experimentDataList2.add(new Experiment(6, 1, "Experiment 6", null, 5, Experiment.TYPE_BINOMIAL));
        experimentAdapter2.notifyDataSetChanged();

        // Don't know why this doesn't work.
        experimentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view1, int position, long id) {
                Log.d("City clicked: ", "hello");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(
                R.layout.owner_interface, container, false);
    }
}
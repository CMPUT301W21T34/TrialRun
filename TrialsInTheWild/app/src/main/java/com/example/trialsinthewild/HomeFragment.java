package com.example.trialsinthewild;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

//https://www.youtube.com/watch?v=fGcMLu1GJEc&t=567s
//navigation class are modified from Coding in Flow
public class HomeFragment extends Fragment {
    private View view;
    private Button setup;
    private TextView name;
    private ListView subscribe;
    private ListView own;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        //https://www.cnblogs.com/codingblock/p/4808809.html
        //Author: CodingBlock
        view.findViewById(R.id.home_setUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new UserFragment()).commit();
            }
        });

        view.findViewById(R.id.home_createExperiment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new OwnerFragment()).commit();
            }
        });

        view.findViewById(R.id.home_findExperiments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new ExperimenterFragment()).commit();
            }
        });
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setup = view.findViewById(R.id.home_setUp);
        name = view.findViewById(R.id.home_userName);
        subscribe = view.findViewById(R.id.home_subscribed);
        own = view.findViewById(R.id.home_ownExperiments);
        setup.setVisibility(View.VISIBLE);

        UserManager um = UserManager.getInstance();
        User u = UserManager.getApplicationUser();
        ExperimentManager em = ExperimentManager.getInstance();

        name.setText(u.getUsername());
        if(!u.hasDefaultUsername()){
            setup.setVisibility(View.GONE);
        }

        ArrayList<Experiment> subscribed_list = new ArrayList<>();
        for (int i : um.getSubscribedExperiments()) {
            Experiment experiment = em.getExperiment(i);
            if(experiment != null) {
                subscribed_list.add(experiment);
            }
        }
        ExperimentList expSubscribedAdapter = new ExperimentList(view.getContext(), subscribed_list);
        subscribe.setAdapter(expSubscribedAdapter);

        ArrayList<Experiment> owned_list = em.getOwnedExperiments(u.getUserId());
        ExperimentList expOwnedAdapter = new ExperimentList(view.getContext(), owned_list);
        own.setAdapter(expOwnedAdapter);



    }
}

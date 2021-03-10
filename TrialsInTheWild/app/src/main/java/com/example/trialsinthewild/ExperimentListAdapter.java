package com.example.trialsinthewild;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ExperimentListAdapter extends ArrayAdapter<Experiment> {

    private ArrayList<Experiment> experiments;
    private Context context;

    public ExperimentListAdapter(Context context, ArrayList<Experiment> experiments){
        super(context,0, experiments);
        this.experiments = experiments;
        this.context = context;
    }

    @Override
    public int getCount() {
        return experiments.size();
    }

    @Nullable
    @Override
    public Experiment getItem(int position) {
        return experiments.get(position);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    //        return super.getView(position, convertView, parent);
        View view = convertView;

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.experiment_list_content, parent,false);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Test", "Okay");
            }
        });

        Experiment experiment = experiments.get(position);

        TextView experimentDescription = view.findViewById(R.id.descriptionText);
        TextView experimentUserName = view.findViewById(R.id.usernameText);
        TextView experimentStatus = view.findViewById(R.id.statusText);

        experimentDescription.setText(experiment.getDescription());
        experimentUserName.setText(String.valueOf(experiment.getOwnerId()));
        experimentStatus.setText(String.valueOf(experiment.getStatus()));

        return view;
    }
}
package com.example.trialsinthewild;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Something
 * pop a list view
 *
 */
public class ExperimentList extends ArrayAdapter<Experiment> {
    private ArrayList<Experiment> experiments;
    private Context context;

    public ExperimentList(Context context, ArrayList<Experiment> experiments) {
        super(context,0,experiments);
        this.experiments = experiments;
        this.context = context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            //load content of list
            view = LayoutInflater.from(context).inflate(R.layout.experiment_list_content,parent,false);
        }

        Experiment experiment = experiments.get(position);

        TextView expDes = view.findViewById(R.id.descriptionTextView);
        TextView expStatus = view.findViewById(R.id.statusTextView);
        TextView expName = view.findViewById(R.id.userNameTextView);

        expDes.setText(experiment.getDescription());
        expStatus.setText(experiment.getStatusStr());
        expName.setText(UserManager.getUser(experiment.getOwnerId()).getUsername());

        return view;
    }
}

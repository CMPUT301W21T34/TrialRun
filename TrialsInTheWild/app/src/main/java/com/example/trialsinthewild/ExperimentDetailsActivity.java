package com.example.trialsinthewild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExperimentDetailsActivity extends AppCompatActivity {

    private ColorStateList oldColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_details);

        Intent intent = getIntent();
        ExperimentManager em = ExperimentManager.getInstance();
        UserManager um = UserManager.getInstance();
        int experiment_id = intent.getIntExtra("experiment_id", -1);

        if(experiment_id == -1) {
            // problem did not send a valid experiment
            Log.d("ExpDetailsActivity: ", "No experiment id set in Intent");
        }

        Experiment experiment = em.getExperiment(experiment_id);
        if(experiment == null) {
            Log.d("ExpDetailsActivity: ", "Invalid experiment!");
        }

        TextView tvStatus = findViewById(R.id.tv_exp_status);
        tvStatus.setText(experiment.getStatusStr());
        if(experiment.getStatus() == Experiment.STATUS_OPEN) {
            tvStatus.setTextColor(getResources().getColor(R.color.design_default_color_secondary_variant));
        } else {
            tvStatus.setTextColor(getResources().getColor(R.color.design_default_color_error));
        }

        CheckBox cbSubscribed = findViewById(R.id.cb_subscribed_state);
        oldColor = cbSubscribed.getTextColors();
        if(um.isSubscribed(experiment_id)) {
            Log.d("Setting checked true", "yes");
            cbSubscribed.setChecked(true);
            cbSubscribed.setText("Subscribed!");
            cbSubscribed.setTextColor(getResources().getColor(R.color.design_default_color_secondary_variant));
        } else {
            Log.d("Setting checked true", "yes");
            cbSubscribed.setChecked(false);
            cbSubscribed.setText("Subscribe");
            cbSubscribed.setTextColor(oldColor);
        }

        cbSubscribed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    um.subscribeToExperiment(experiment_id);
                    cbSubscribed.setText("Subscribed!");
                    cbSubscribed.setTextColor(getResources().getColor(R.color.design_default_color_secondary_variant));
                } else {
                    um.unsubscribeToExperiment(experiment_id);
                    cbSubscribed.setText("Subscribe");
                    cbSubscribed.setTextColor(oldColor);
                }
            }
        });

        Button btnInteract = findViewById(R.id.btn_results_interact_experiment);
        btnInteract.setText(experiment.getStatus() == Experiment.STATUS_OPEN ? "Participate" : "View Results");

        btnInteract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(experiment.getStatus() == Experiment.STATUS_ENDED) {
                    // view results activity
                } else {
                    // add trials activity
                }
            }
        });

        // TODO: This is just for testing purposes to see how it will populate
        ListView lvExpDiscussion = findViewById(R.id.lv_exp_discussion);
        ArrayList<QAPair> question_list = new ArrayList<>();
        for(int i=0; i<10;++i) {
            QAPair qp = new QAPair("How long does this experiment (Experiment #" + String.valueOf(experiment_id) + ") take", "", experiment_id);
            question_list.add(qp);
        }
        QuestionAnswerList qaAdapter = new QuestionAnswerList(this, question_list);
        lvExpDiscussion.setAdapter(qaAdapter);

        TextView tvDescription = findViewById(R.id.tv_exp_description);
        TextView tvRegion = findViewById(R.id.tv_region_description);

        tvDescription.setText(experiment.getDescription());
        Region region = experiment.getRegion();
        if(region != null) {
            tvRegion.setText(region.getDescription());
        } else {
            tvRegion.setText("Anywhere");
        }
    }

}
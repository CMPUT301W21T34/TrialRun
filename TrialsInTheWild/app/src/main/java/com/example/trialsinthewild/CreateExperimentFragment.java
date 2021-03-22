package com.example.trialsinthewild;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CreateExperimentFragment extends DialogFragment {
    private EditText miniNoTrails;
    private EditText expDescription;
    private EditText locDescription;
    private Switch locationReqSwitch;
    private OnFragmentInteractionListener listener;

    public interface OnFragmentInteractionListener {
        void onOkPressed();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_create_experiment, null);
        expDescription = view.findViewById(R.id.expDescriptionEditText);//
        miniNoTrails = view.findViewById(R.id.miniNoTrailsEditText);//
        locationReqSwitch = view.findViewById(R.id.switchRegion);
        locDescription = view.findViewById(R.id.locationDescriptionEditText);
        locDescription.setEnabled(false);
        locationReqSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    locDescription.setEnabled(true);
                } else {
                    locDescription.setEnabled(false);
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add experiment")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String miniNo = miniNoTrails.getText().toString();
                        String description = expDescription.getText().toString();
                        // Create new experiment here!
                        listener.onOkPressed();
                    }}).create();
    }
}

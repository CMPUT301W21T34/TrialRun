package com.example.trialsinthewild;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
//https://www.youtube.com/watch?v=fGcMLu1GJEc&t=567s
//navigation class are modified from Coding in Flow
public class UserFragment extends Fragment {

    private CreateExperimentFragment.OnFragmentInteractionListener listener;
    private EditText contact;
    private TextView user_name;
    private Button change_info;
    private FirebaseFirestore firedb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_user, container, false);


    }

    public interface OnFragmentInteractionListener {
        void onOkPressed(MainActivity newProfile);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateExperimentFragment.OnFragmentInteractionListener) {
            listener = (CreateExperimentFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_create_experiment, null);
        User user_p = UserManager.getApplicationUser();
        contact = view.findViewById(R.id.user_contact);
        change_info = view.findViewById(R.id.edit_contact_info);
        user_name = view.findViewById(R.id.userName);
        firedb = FirebaseFirestore.getInstance();

        //https://firebase.google.com/docs/firestore/query-data/get-data
        DocumentReference docRef = firedb.collection("Users").document(String.valueOf(user_p));
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        String con_info=document.getString("contact_info");
                        String u_name = document.getString("user_name");
                        contact.setText(con_info);
                        user_name.setText(u_name);
                    }

                }
            }
        });



        //https://www.youtube.com/watch?v=TBr_5QH1EvQ
        DocumentReference note = firedb.collection("Users").document(String.valueOf(user_p));
        Map<String, Object> temp = new HashMap<>();
        change_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_info = contact.getText().toString();

                note.update("contact_info", new_info);

            }
        });
    }

}
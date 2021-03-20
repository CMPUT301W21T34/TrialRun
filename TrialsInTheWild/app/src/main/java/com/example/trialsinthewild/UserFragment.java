package com.example.trialsinthewild;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.HashMap;
import java.util.Map;
//https://www.youtube.com/watch?v=fGcMLu1GJEc&t=567s
//navigation class are modified from Coding in Flow
public class UserFragment extends Fragment {

    private CreateExperimentFragment.OnFragmentInteractionListener listener;
    private EditText contact;
    private TextView user_name;
    private Button change_info;
    private Button refresh;
    private View view;
    private FirebaseFirestore firedb;
    private DocumentReference collectionReference;
    public UserFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);

    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_user, null);
        User user_id = UserManager.getApplicationUser();
        contact = view.findViewById(R.id.user_contact);
        change_info = view.findViewById(R.id.edit_contact_info);
        user_name = (TextView) view.findViewById(R.id.userName);
        refresh = view.findViewById(R.id.refresh_info);
        firedb = FirebaseFirestore.getInstance();

        collectionReference = firedb.collection("Users").document("7");
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectionReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {

                            String con_info = documentSnapshot.getString("contact_info");
                            String u_name = documentSnapshot.getString("user_name");
                            contact.setText(con_info);
                            user_name.setText(u_name);

//                            if (!task.isSuccessful()) {
//                                Log.e("firebase", "Error getting data", task.getException());
//                            } else {
//                                Log.d("firebase", String.valueOf(task.getResult().getData()));
//                            }
                        }
                    }
                });
            }
        });


//        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                change_info.setText("");
//                user_name.setText("");
//                for (QueryDocumentSnapshot doc:value){
//                    String name = (String) doc.getData().get("user_name");
//                    String contact_info = (String) doc.getData().get("contact_info");
//                    contact.setText(contact_info);
//                    user_name.setText(name);
//                }
//                contact.not
//            }
//        });
//
//
//        //https://firebase.google.com/docs/firestore/query-data/get-data
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//
//                        String con_info=document.getString("contact_info");
//                        String u_name = document.getString("user_name");
//                        contact.setText(con_info);
//                        user_name.setText(u_name);
//                    }
//
//                }
//            }
//        });

        change_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_Info = change_info.getText().toString();
                change_info.setText(new_Info);
                collectionReference.update("contact_info", new_Info);

            }
        });

    }


//
//    public interface OnFragmentInteractionListener {
//        void onOkPressed(MainActivity newProfile);
//    }
//
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof CreateExperimentFragment.OnFragmentInteractionListener) {
//            listener = (CreateExperimentFragment.OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    public void onCreateDialog(@Nullable Bundle savedInstanceState) {
//
//        User user_p = UserManager.getApplicationUser();
//        //https://www.youtube.com/watch?v=TBr_5QH1EvQ
//        DocumentReference note = firedb.collection("Users").document(String.valueOf(user_p));
//        Map<String, Object> temp = new HashMap<>();
//        change_info.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String new_info = contact.getText().toString();
//
//                note.update("contact_info", new_info);
//
//            }
//        });
//    }

}
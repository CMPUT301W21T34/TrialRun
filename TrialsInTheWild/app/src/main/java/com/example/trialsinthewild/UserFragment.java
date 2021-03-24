package com.example.trialsinthewild;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.firebase.firestore.DocumentReference;

import com.google.firebase.firestore.FirebaseFirestore;


//https://www.youtube.com/watch?v=fGcMLu1GJEc&t=567s
//navigation class are modified from Coding in Flow
public class UserFragment extends Fragment {

    private EditText contact;
    private TextView user_name;
    private Button change_info;
    private View view;

    Button confirm_username;
    TextView set_username_prompt;
    EditText set_username;

    public UserFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        return view;

    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        confirm_username = view.findViewById(R.id.confirmUsernameButton);
        set_username_prompt = view.findViewById(R.id.setUsernamePromptTextView);
        set_username = view.findViewById(R.id.setUsernameEditText);

        contact = view.findViewById(R.id.user_contact);
        change_info = view.findViewById(R.id.edit_contact_info);
        change_info.setVisibility(View.GONE);
        user_name = (TextView) view.findViewById(R.id.userName);

        UserManager um = UserManager.getInstance();
        User u = um.getApplicationUser();

        contact.setText(u.getContactInfo());
        user_name.setText(u.getUsername());

        contact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (u.getContactInfo().equals(contact.getText().toString())) {
                    change_info.setVisibility(View.GONE);
                } else {
                    change_info.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(u.hasDefaultUsername()) {
            confirm_username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String new_username =set_username.getText().toString();
                    if(um.updateUser("username", new_username)) {
                        confirm_username.setVisibility(View.GONE);
                        set_username.setVisibility(View.GONE);
                        set_username_prompt.setVisibility(View.GONE);
                        user_name.setText(new_username);
                    }
                }
            });

            set_username.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    UserManager um2 = UserManager.getInstance();
                    Log.d("TextField: ", set_username.getText().toString());
                    String new_username = set_username.getText().toString();
                    if(um2.userNameExists(new_username)) {
                        set_username_prompt.setText("Username already exists!");
                        confirm_username.setEnabled(false);
                        confirm_username.setBackgroundColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_disabled));
                    } else if (!um2.isValidUsername(new_username)) {
                        // TODO: this ugly fix up later
                        set_username_prompt.setText("Invalid username, check length and special characters!");
                        confirm_username.setEnabled(false);
                        confirm_username.setBackgroundColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_disabled));
                    } else {
                        if(!set_username_prompt.getText().toString().equals("Please set up username below")) {
                            set_username_prompt.setText("Please set up username below");
                        }
                        confirm_username.setEnabled(true);
                        confirm_username.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary));
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        } else {
            confirm_username.setVisibility(View.GONE);
            set_username.setVisibility(View.GONE);
            set_username_prompt.setVisibility(View.GONE);
        }



        /*collectionReference = firedb.collection("Users").document("7");
        //https://www.youtube.com/watch?v=di5qmolrFVs modified from Coding in Flow
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {

                    String con_info = documentSnapshot.getString("contact_info");
                    String u_name = documentSnapshot.getString("user_name");
                    contact.setText(con_info);
                    user_name.setText(u_name);

                }
            }
        });*/


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
                String new_Info = contact.getText().toString();
                UserManager um = UserManager.getInstance();
                if(um.updateUser("contact_info", new_Info)) {
                    contact.setText(new_Info);
                    change_info.setVisibility(View.GONE);
                }
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
package com.example.trialsinthewild;

import android.content.Intent;
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
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

//https://www.youtube.com/watch?v=fGcMLu1GJEc&t=567s
//navigation class are modified from Coding in Flow
public class SearchFragment extends Fragment {
    private View view;
    private EditText enter_name;
    private Button search_commit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        enter_name = view.findViewById(R.id.search_user);
        search_commit = view.findViewById(R.id.search_commit);

        search_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManager userM = UserManager.getInstance();
                String enter = enter_name.getText().toString();
                if (!enter.isEmpty()){
                    if(userM.userNameExists(enter)){
                        Intent jump = new Intent(view.getContext(),SearchShow.class);
                        jump.putExtra("userName",enter);
                        startActivity(jump);
                    }else{
                        Log.d("User name not exist",NULL);
                    }
                }else{
                    Log.d("Nothing entered",NULL);
                }
            }
        });

    }
}

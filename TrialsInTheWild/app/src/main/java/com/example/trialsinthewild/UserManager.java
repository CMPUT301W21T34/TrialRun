package com.example.trialsinthewild;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class UserManager {
    private static UserManager instance;
    private static FirebaseFirestore db;
    private static ArrayList<User> users;
    private static CollectionReference ref;

    private UserManager() {
        db = FirebaseFirestore.getInstance();
        ref = db.collection("Users");
        users = new ArrayList<>();
        instance = this;

        loadUsers();
    }

    private void loadUsers() {
        ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Log.d("Loading in user: ", String.valueOf(doc.getId()));

                    int user_id = Integer.parseInt(doc.getId());
                    String contact_info = (String) doc.getData().get("contact_info");
                    String username = (String) doc.getData().get("username");
                    createUser(user_id, contact_info, username);
                }
            }
        });

        // Changes made to the database, update them somehow
        ref.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (QueryDocumentSnapshot doc : value) {
                    Log.d("UserManager: ", "Loading in updated Region: " + String.valueOf(doc.getId()));
                }
            }
        });
    }

    private void createUser(int user_id, String contact_info, String username) {
        User user = new User(user_id, username, contact_info);
        users.add(user);
    }

    public static UserManager getInstance() {
        if(instance != null) {
            return instance;
        }
        return new UserManager();
    }

    public static User getUser(int user_id) {
        for(User u : users) {
            if(u!=null && u.getUserId()==user_id)
                return u;
        }
        return null;
    }
}

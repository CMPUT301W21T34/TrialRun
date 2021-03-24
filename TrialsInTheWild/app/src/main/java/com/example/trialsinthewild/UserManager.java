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
    public static int MAX_USERNAME_LENGTH = 20;     // We can set these later
    public static int MIN_USERNAME_LENGTH = 1;      // Unsure if needed
    public static int APP_USER_NOT_SET = -1;

    private static UserManager instance = null;
    private FirebaseFirestore db;
    private static ArrayList<User> users;
    private CollectionReference ref;

    private static int app_user_id = APP_USER_NOT_SET;

    // local variable here for subscribed experiments
    private ArrayList<Integer> subscribed_experiments;

    private UserManager() {
        db = FirebaseFirestore.getInstance();
        ref = db.collection("Users");
        users = new ArrayList<>();
        instance = this;

        loadUsers();
        loadLocalData();
    }

    private void loadLocalData() {
        // loading the stuff from the user phone
        // subscribed experiments
        // the users id
        subscribed_experiments = new ArrayList<>();
        // load from shared preferences.
    }

    private void loadUsers() {
        ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Log.d("Loading in user: ", String.valueOf(doc.getId()));
                    processUserSnapshot(doc);
                }
            }
        });

        // Changes made to the database
        // NOTE: When ANY change is made to a document, the entire document is sent back to us every single time.
        //       This means if user_id=1 changes contact_info, every single user that exists is reloaded
        ref.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (QueryDocumentSnapshot doc : value) {
                    Log.d("UserManager: ", "Loading in updated Region: " + String.valueOf(doc.getId()));
                    processUserSnapshot(doc);
                }
            }
        });
    }

    private void processUserSnapshot(QueryDocumentSnapshot doc) {
        int user_id = Integer.parseInt(doc.getId());
        String contact_info = (String) doc.getData().get("contact_info");
        String username = (String) doc.getData().get("username");

        User user = getUser(user_id);
        if( user == null) {
            createUser(user_id, contact_info, username);
        } else {
            updateUser(user, contact_info, username);
        }
    }

    /**
     * For use when building a new user from disc or creating new user for the first time
     * @param user_id
     * @param contact_info
     * @param username
     */
    private void createUser(int user_id, String contact_info, String username) {
        User user = new User(user_id, username, contact_info);
        users.add(user);
    }

    private void updateUser(User user, String contact_info, String username) {
        user.setContactInfo(contact_info);
        user.setUsername(username);
    }

    public boolean userNameExists(String username) {
        for(User u : users) {
            if(u.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether a given username is valid. Since only the application user
     * can edit their own profile, we assume their profile for all checks
     * @param username
     *          String - username to check against
     * @return
     *          Boolean - True if valid, False if invalid
     */
    public static boolean isValidUsername(String username) {
        /* Username cannot be;
         *  1). Empty
         *  2). Bigger than MAX_USERNAME_LENGTH
         *  3). Smaller than MIN_USERNAME_LENGTH
         *  4). The same as default username
         *  5). Must only contain letters, numbers, and underscores
         */

        if(username.isEmpty()) {
            return false;
        }

        if(username.length() > MAX_USERNAME_LENGTH) {
            return false;
        }

        if(username.length() < MIN_USERNAME_LENGTH) {
            return false;
        }

        User u = getApplicationUser();
        if(u != null && u.getDefaultUsername().equals(username)) {
            return false;
        }

        if(username.matches("^.*[^a-zA-Z0-9_].*$")) {
            return false;
        }

        return true;
    }

    public static User getApplicationUser() {
        if(app_user_id < 0) {
            // This should never happen I hope
            Log.d("UserManager: ", "Application User not set!");
            return null;
        }
        return getUser(app_user_id);
    }

    public static void setApplicationUser(int user_id) {
        app_user_id = user_id;
    }

    public static UserManager getInstance() {
        if(instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public static User getUser(int user_id) {
        for(User u : users) {
            if(u!=null && u.getUserId()==user_id)
                return u;
        }
        return null;
    }

    public static User getUserByName(String username) {
        for(User u : users) {
            if(u!=null && u.getUsername().equals(username))
                return u;
        }
        return null;
    }

    public boolean updateUser(String field, Object value) {
        if(field.equals("username")) {
            User u = getApplicationUser();
            if(u == null) {
                return false;
            }
            if(userNameExists(value.toString())) {
                return false;
            }
            if(u.hasDefaultUsername()) {
                if(u.getDefaultUsername().equals(value.toString())) {
                    return false;
                }
            }
        }
        ref.document(String.valueOf(app_user_id)).update(field, value);
        return true;
    }

    public ArrayList<Integer> getSubscribedExperiments() {
        // TODO: testing purposes, remove later and implement properly
        ArrayList<Integer> fake_list = new ArrayList<>();
        fake_list.add(0);
        fake_list.add(1);
        return fake_list;
        //return subscribed_experiments;
    }
}

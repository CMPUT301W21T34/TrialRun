package com.example.trialsinthewild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
//https://www.youtube.com/watch?v=fGcMLu1GJEc&t=567s
//navigation class are modified from Coding in Flow
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CreateExperimentFragment.OnFragmentInteractionListener {
    private DrawerLayout drawer;
    private EditText contact;
    private TextView user_name;
    private Button change_info;
    private FirebaseFirestore firedb;
    private int u_id;
    private int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Activity Launched
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firedb = FirebaseFirestore.getInstance();

        // Get all our managers for the databases set up / handling the data
        ExperimentManager em = ExperimentManager.getInstance();

        // setApplicationContext must be done to get local data
        UserManager.setApplicationContext(getApplicationContext());
        UserManager um = UserManager.getInstance();
        RegionManager rm = RegionManager.getInstance();
        TrialManager tm = TrialManager.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ExperimentsFragment()).commit();
            navigationView.setCheckedItem(R.id.experiments);
        }

        //

        /* ******************************************************************************
            This is a crappy test of how to create a user_id and store it locally. If they don't
            Have a user_id, get a unique ID from the database and update it locally and in
            the database. This entire section needs to go into helper class
         ********************************************************************************/

        // TODO: PUT THIS ALL IN USER MANAGER
        // TODO: This is a to do list thing
        // users that don't exist have id of -1 I guess?
        /*final int NEW_USER = -1;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        int user_id = preferences.getInt("UserId", NEW_USER);
        Log.d("user_id: ", String.valueOf(user_id));
        if (user_id == NEW_USER) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            final CollectionReference collectionReference = db.collection("Users");
           collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
               @Override
               public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                   int next_id = queryDocumentSnapshots.size();
                   HashMap<String, Object> data =  new HashMap<>();

                   User user = new User(next_id, "username"+String.valueOf(next_id), "");

                   data.put("user_id", user.getUserId());
                   data.put("username", user.getUsername());
                   data.put("contact_info", user.getContactInfo());

                   collectionReference.document(String.valueOf(user.getUserId())).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           Log.d("UserDatabase: ", "Successfully added new user to database");

                           // Sets a user id in devices shared preferences
                           editor.putInt("UserId", next_id);
                           editor.commit();
                           UserManager.setApplicationUser(next_id);
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           // This means we could not connect to database, close the program with error?
                           Log.d("UserDatabase: ", "Failed to add new user to database");
                       }
                   });

               }
           });
        } else {
            UserManager.setApplicationUser(user_id);
        }*/
        /* ******************************************************************************
            End of the user id stuff
        *********************************************************************************/

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.owner:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OwnerFragment()).commit();
                break;
            case R.id.experimenter:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ExperimenterFragment()).commit();
                break;
            case R.id.experiments:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ExperimentsFragment()).commit();
                break;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserFragment()).commit();
                break;
            case R.id.user_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }

    }
    @Override
    protected void onStart() {
        // Activity Started (Happens after onRestart)
        super.onStart();
    }

    @Override
    protected void onRestart() {
        // Happens after onStop is called and user navigates back to the activity
        super.onRestart();
    }

    @Override
    protected void onResume() {
        // Happens after onPause and user returns to the activity (Returns from fragment)
        super.onResume();
    }

    @Override
    protected void onPause() {
        // Another activity comes to the foreground (A Fragment/Dialogue)
        super.onPause();
    }

    @Override
    protected void onStop() {
        // The activity is no longer visible
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // Activity is finishing or the device destroys the activty to free up memory
        super.onDestroy();
    }

    @Override
    public void onOkPressed() {
        //Log.d("ok pressed",newExp.getDescription());
    }
}
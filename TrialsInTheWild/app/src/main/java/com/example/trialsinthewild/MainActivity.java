package com.example.trialsinthewild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Activity Launched
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }
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
            case R.id.labs:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LabsFragment()).commit();
                break;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserFragment()).commit();
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
}
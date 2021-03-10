package com.example.trialsinthewild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExperimenterFragment.OnFragmentInteractionListener {

    private FragmentStateAdapter pagerAdapter;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Activity Launched
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        /*
            Initialize all of our stuff - find out who the user is, which experiments he is subscribed to, which experiments he owns.
            Whatever is needed to display the stuff in this activity.
         */

        /* https://www.tutlane.com/tutorial/android/android-tabs-with-fragments-and-viewpager - properly cite later */
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager =(ViewPager)findViewById(R.id.tabContainer);
        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_profile:
                Log.d("Action: ", "Profile");
                // Fill the lists?

                return true;

            case R.id.menu_search:
                Log.d("Action: ", "Search");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onLoad(TextView tv) {
        tv.setText("TESTEFDSFDSF");
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
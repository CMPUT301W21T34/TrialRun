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

public class RegionManager {
    private static RegionManager instance;
    private static FirebaseFirestore db;
    private static ArrayList<Region> regions;
    private static CollectionReference ref;

    private RegionManager() {
        db = FirebaseFirestore.getInstance();
        ref = db.collection("Regions");
        regions = new ArrayList<>();
        instance = this;

        loadRegions();
    }

    private void loadRegions() {
        ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Log.d("Loading in region: ", String.valueOf(doc.getId()));

                    int region_id = Integer.parseInt(doc.getId());
                    GeoPoint point = (GeoPoint) doc.getData().get("location");
                    long range = (Long) doc.getData().get("range");
                    String description = (String) doc.getData().get("description");
                    Region region = new Region(point, description, range, region_id);
                    Log.d("GeoPoint Loaded: ", description+ "(" + String.valueOf(point.getLatitude()) + ", " + String.valueOf(point.getLongitude()) + ")");
                }
            }
        });

        // Changes made to the database - update them elsewhere somehow?
        ref.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (QueryDocumentSnapshot doc : value) {
                    Log.d("RegionManager: ", "Loading in updated Region: " + String.valueOf(doc.getId()));

                }
            }
        });
    }

    public static RegionManager getInstance() {
        if(instance == null) {
            instance = new RegionManager();
        }
        return instance;
    }

    public static Region getRegion(int region_id) {
        for(Region r : regions) {
            if(r!=null && r.getRegionId()==region_id)
                return r;
        }
        return null;
    }
}

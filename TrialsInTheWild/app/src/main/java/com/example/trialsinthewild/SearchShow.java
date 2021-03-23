package com.example.trialsinthewild;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchShow extends Activity {
    TextView name;
    TextView contact;
    Button back;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_show);

        name = findViewById(R.id.name_view);
        contact = findViewById(R.id.contact_view);
        back = findViewById(R.id.back);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        User user = UserManager.getUserByName(userName);

        name.setText(user.getUsername());
        contact.setText(user.getContactInfo());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}

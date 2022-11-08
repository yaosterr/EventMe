package com.example.eventme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.HashMap;
import java.util.*;

public class PersonalVoice extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("clicked", "newregister");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_voice);
        Intent intent = getIntent();
        ArrayList<Event> tempEvent = (ArrayList<Event>)intent.getSerializableExtra("msg");


        if (tempEvent != null){
            for (int i = 0; i< tempEvent.size(); i++) {
                LinearLayout parent = new LinearLayout(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                params.height = 350;
                parent.setOrientation(LinearLayout.VERTICAL);
                parent.setLayoutParams(params);

                TextView eventNameView = new TextView(this);
                TextView locationView = new TextView(this);
                TextView dateAndTimeView = new TextView(this);
                TextView organizationView = new TextView(this);

                LinearLayout.LayoutParams eventNameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                eventNameParams.height = 100;
                LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                viewParams.height = 50;
                LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                buttonParams.height = 100;
                eventNameView.setLayoutParams(eventNameParams);
                locationView.setLayoutParams(viewParams);
                dateAndTimeView.setLayoutParams(viewParams);
                organizationView.setLayoutParams(viewParams);

                String eventName = tempEvent.get(i).getEvent();
                String location = tempEvent.get(i).getLocation();
                String dateTime = tempEvent.get(i).getDateAndTime();
                String organization = tempEvent.get(i).getOrganization();

                eventNameView.setText(eventName);
                eventNameView.setTextColor(Color.BLACK);
                eventNameView.setTextSize(26);
                eventNameView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                locationView.setText(location);
                dateAndTimeView.setText(dateTime);
                organizationView.setText(organization);
                parent.addView(eventNameView);
                parent.addView(locationView);
                parent.addView(dateAndTimeView);
                parent.addView(organizationView);

                LinearLayout layout = (LinearLayout) findViewById(R.id.personalLayout1);
                layout.addView(parent);
            }
        }
    }
}

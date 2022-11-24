package com.example.eventme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.HashMap;
import java.util.*;

public class EventBox extends AppCompatActivity {

    ArrayList<Event> total = new ArrayList<Event>();

    String eventList[] = {"Cava Giveaway", "Nut Party", "Best Friend Party", "Adele Birthday Party", "Harvard How-tos", "Nice Greetings", "Physics Class", "Elon Musk Talk Show",  "Dinner With Lebron James", "Life of Pi Reading", "Temper Control", "Christmas Celebration", "Justin Bieber Meet and Greet", "Self Defense Class", "Love Yourself Readings", "Casper Party", "Standup Comedy", "Kevin Hart Party", "Bill Nye Exhibition", "Fire Exhibition"};
    String locationList[] = {"CAVA, Parking lot, South Hoover Street Suite 1840, Los Angeles, CA", "2809 Ellendale Place, Los Angeles, CA", "Insomnia Cookies, West Jefferson Boulevard, Los Angeles, CA", "1324 West 37th Place, Los Angeles, CA", "1261 W 36th Pl, Los Angeles, CA", "1259 W 36th Pl, Los Angeles, CA", "1261 W 36th Pl, Los Angeles, CA","1260 W 36th Pl, Los Angeles, CA","1263 W 36th Pl, Los Angeles, CA","1265 W 36th Pl, Los Angeles, CA", "CAVA, Parking lot, South Hoover Street Suite 1840, Los Angeles, CA", "2809 Ellendale Place, Los Angeles, CA", "Insomnia Cookies, West Jefferson Boulevard, Los Angeles, CA", "1324 West 37th Place, Los Angeles, CA", "1261 W 36th Pl, Los Angeles, CA", "1259 W 36th Pl, Los Angeles, CA", "1261 W 36th Pl, Los Angeles, CA","1260 W 36th Pl, Los Angeles, CA","1263 W 36th Pl, Los Angeles, CA","1265 W 36th Pl, Los Angeles, CA", "CAVA, Parking lot, South Hoover Street Suite 1840, Los Angeles, CA", "2809 Ellendale Place, Los Angeles, CA", "Insomnia Cookies, West Jefferson Boulevard, Los Angeles, CA", "1324 West 37th Place, Los Angeles, CA", "1261 W 36th Pl, Los Angeles, CA", "1259 W 36th Pl, Los Angeles, CA", "1261 W 36th Pl, Los Angeles, CA","1260 W 36th Pl, Los Angeles, CA","1263 W 36th Pl, Los Angeles, CA","1265 W 36th Pl, Los Angeles, CA"};
    String dateTimeList[] = {"9:30PM, 11/01/2022", "9:10PM, 11/03/2022", "7:00PM, 10/06/2022", "11:00PM, 11/15/2022", "9:30PM, 11/20/2022","9:35PM, 12/20/2022","10:00AM, 11/20/2022","7:15AM, 12/02/2022","7:30PM, 11/20/2022","10:30PM, 11/20/2022", "9:30PM, 11/01/2022", "9:10PM, 11/03/2022", "7:00PM, 10/06/2022", "11:00PM, 11/15/2022", "9:30PM, 11/20/2022","9:35PM, 12/20/2022","10:00AM, 11/20/2022","7:15AM, 12/02/2022","7:30PM, 11/20/2022","10:30PM, 11/20/2022"};
    String organizationList[] = {"Cava", "Deez", "Sausage Inc.", "ESPN", "Stephen A. Smith", "Jojo Siwa", "Marco Paolieri", "Tesla", "NBA", "Penguin House", "USC", "USC", "USC Village", "Fun Times Inc.", "Life is Good Inc.", "OVO", "LuLu Lemon", "Riot Games", "LAPD", "Crypto.com"};
    double longitudeList[] = {-118.2845264, -118.2893416, -118.2853451, -118.2982277, -118.2964867, -118.296445, -118.2964867, -118.2967404, -118.2965932, -118.2966258, -118.2845264, -118.2893416, -118.2853451, -118.2982277, -118.2964867, -118.296445, -118.2964867, -118.2967404, -118.2965932, -118.2966258};
    double latitudeList[] = {34.0250757, 34.0292234, 34.0250132, 34.0197438, 34.0220969, 34.022148, 34.0220969, 34.0216126, 34.0221403, 34.0221588, 34.0250757, 34.0292234, 34.0250132, 34.0197438, 34.0220969, 34.022148, 34.0220969, 34.0216126, 34.0221403, 34.0221588};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_box);

        LinearLayout parent1 = new LinearLayout(this);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        params1.height = 150;
        parent1.setOrientation(LinearLayout.VERTICAL);
        parent1.setLayoutParams(params1);

        TextView titleView = new TextView(this);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        titleParams.height = 150;
        titleView.setLayoutParams(titleParams);

        String title = "Event Box";
        titleView.setText(title);
        titleView.setTextColor(Color.BLUE);
        titleView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        titleView.setTextSize(50);

        parent1.addView(titleView);

        LinearLayout layout1 = (LinearLayout) findViewById(R.id.eventboxlayout);
        layout1.addView(parent1);

        for (int i = 0; i< eventList.length; i++){
            LinearLayout parent = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            params.height = 350;
            parent.setOrientation(LinearLayout.VERTICAL);
            parent.setLayoutParams(params);

            TextView eventNameView = new TextView(this);
            TextView locationView = new TextView(this);
            TextView dateAndTimeView = new TextView(this);
            TextView organizationView = new TextView(this);
            Button register = new Button(this);
            int finalI = i;
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(EventBox.this, "You have signed up. See you then!",
                            Toast.LENGTH_LONG).show();
                    Event temp = new Event(eventList[finalI], locationList[finalI], dateTimeList[finalI],organizationList[finalI]);
                    total.add(temp);
                    Intent minor = new Intent(EventBox.this, PersonalVoice.class);
//                    Intent minor = new Intent(view.getContext(), PersonalVoice.class);
                    minor.putExtra("msg", total);
                    startActivity(minor);
                }
            });

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
            register.setLayoutParams(buttonParams);

            String eventName = eventList[i];
            String location = locationList[i];
            String dateTime = dateTimeList[i];
            String organization = organizationList[i];
            String buttonText = "Register";

            eventNameView.setText(eventName);
            eventNameView.setTextColor(Color.BLACK);
            eventNameView.setTextSize(26);
            eventNameView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            locationView.setText(location);
            dateAndTimeView.setText(dateTime);
            organizationView.setText(organization);
            register.setText(buttonText);

            parent.addView(eventNameView);
            parent.addView(locationView);
            parent.addView(dateAndTimeView);
            parent.addView(organizationView);
            parent.addView(register);

            LinearLayout layout = (LinearLayout) findViewById(R.id.eventboxlayout);
            layout.addView(parent);
        }
    }

    public String[] getEventList() {
        return eventList;
    }

    public String[] getLocationList() {
        return locationList;
    }

    public double[] getLatitudeList() {
        return latitudeList;
    }

    public double[] getLongitudeList() {
        return longitudeList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.common_menu_guest, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_refresh) {
            startActivity(getIntent());
            finish();
            overridePendingTransition(0, 0);
        } else if (id == R.id.menu_alphabetical) {
            Intent intent = new Intent(EventBox.this, EventAlphabetical.class);
            startActivity(intent);

        } else if (id == R.id.menu_date) {
            Intent intent = new Intent(EventBox.this, EventDate.class);
            startActivity(intent);

        }else if (id == R.id.menu_distance) {
            Intent intent = new Intent(EventBox.this, EventDistance.class);
            startActivity(intent);
        }  else if (id == R.id.menu_cost) {
            Intent intent = new Intent(EventBox.this, EventCost.class);
            startActivity(intent);
        } else {
            Toast.makeText(EventBox.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
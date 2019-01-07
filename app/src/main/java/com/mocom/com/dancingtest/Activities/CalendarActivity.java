package com.mocom.com.dancingtest.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mocom.com.dancingtest.Fragments.CalendarFragment;
import com.mocom.com.dancingtest.R;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_calendar, CalendarFragment.newInstance())
                    .commit();
        }
    }
}

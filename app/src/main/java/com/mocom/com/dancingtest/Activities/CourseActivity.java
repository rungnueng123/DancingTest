package com.mocom.com.dancingtest.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mocom.com.dancingtest.Fragments.CourseFragment;
import com.mocom.com.dancingtest.R;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_course, CourseFragment.newInstance())
                    .commit();
        }
    }
}

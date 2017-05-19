package com.beiing.week_calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.beiing.weekcalendar.WeekCalendar;

public class MainActivity extends AppCompatActivity {
    private WeekCalendar weekCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weekCalendar = (WeekCalendar) findViewById(R.id.week_calendar);
    }
}

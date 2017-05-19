package com.beiing.week_calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beiing.weekcalendar.WeekCalendar;
import com.beiing.weekcalendar.utils.GetViewHelper;

import org.joda.time.DateTime;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeekCalendar weekCalendar = (WeekCalendar) findViewById(R.id.week_calendar);

        weekCalendar.setGetViewHelper(new GetViewHelper() {
            @Override
            public View getDayView(int position, View convertView, ViewGroup parent, DateTime dateTime) {
                TextView tv = new TextView(MainActivity.this);
                tv.setGravity(Gravity.CENTER);
                tv.setText(dateTime.toString("MM-dd"));
                return tv;
            }

            @Override
            public View getWeekView(int position, View convertView, ViewGroup parent, String week) {
                TextView tv = new TextView(MainActivity.this);
                tv.setGravity(Gravity.CENTER);
                tv.setText(week);
                return tv;
            }
        });
    }
}

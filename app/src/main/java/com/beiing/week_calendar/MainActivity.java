package com.beiing.week_calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beiing.weekcalendar.WeekCalendar;
import com.beiing.weekcalendar.listener.DateSelectListener;
import com.beiing.weekcalendar.listener.GetViewHelper;
import com.beiing.weekcalendar.listener.WeekChangeListener;
import com.beiing.weekcalendar.utils.CalendarUtil;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvSelectDate;
    private TextView tvWeekChange;

    private List<DateTime> eventDates;
    private WeekCalendar weekCalendar;
    private TextView currentFirstDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSelectDate = (TextView) findViewById(R.id.tv_select_date);
        tvWeekChange = (TextView) findViewById(R.id.tv_week_change);
        currentFirstDay = (TextView) findViewById(R.id.tv_current_firstday);
        eventDates = new ArrayList<>();

        weekCalendar = (WeekCalendar) findViewById(R.id.week_calendar);
        weekCalendar.setGetViewHelper(new GetViewHelper() {
            @Override
            public View getDayView(int position, View convertView, ViewGroup parent, DateTime dateTime, boolean select) {
                if(convertView == null){
                    convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_day, parent, false);
                }
                TextView tvDay = (TextView) convertView.findViewById(R.id.tv_day);
                tvDay.setText(dateTime.toString("d"));
                if(CalendarUtil.isToday(dateTime) && select){
                    tvDay.setTextColor(Color.WHITE);
                    tvDay.setBackgroundResource(R.drawable.circular_blue);
                } else if(CalendarUtil.isToday(dateTime)){
                    tvDay.setTextColor(getResources().getColor(R.color.colorTodayText));
                    tvDay.setBackgroundColor(Color.TRANSPARENT);
                } else if(select){
                    tvDay.setTextColor(Color.WHITE);
                    tvDay.setBackgroundResource(R.drawable.circular_blue);
                } else {
                    tvDay.setTextColor(Color.BLACK);
                    tvDay.setBackgroundColor(Color.TRANSPARENT);
                }

                ImageView ivPoint = (ImageView) convertView.findViewById(R.id.iv_point);
                ivPoint.setVisibility(View.GONE);
                for (DateTime d : eventDates) {
                    if(CalendarUtil.isSameDay(d, dateTime)){
                        ivPoint.setVisibility(View.VISIBLE);
                        break;
                    }
                }
                return convertView;
            }

            @Override
            public View getWeekView(int position, View convertView, ViewGroup parent, String week) {
                if(convertView == null){
                    convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_week, parent, false);
                }
                TextView tvWeek = (TextView) convertView.findViewById(R.id.tv_week);
                tvWeek.setText(week);
                if(position == 0 || position == 6){
                    tvWeek.setTextColor(getResources().getColor(R.color.colorAccent));
                }
                return convertView;
            }
        });

        weekCalendar.setDateSelectListener(new DateSelectListener() {
            @Override
            public void onDateSelect(DateTime selectDate) {
                String text = "你选择的日期是：" + selectDate.toString("yyyy-MM-dd");
                tvSelectDate.setText(text);
            }
        });

        weekCalendar.setWeekChangedListener(new WeekChangeListener() {
            @Override
            public void onWeekChanged(DateTime firstDayOfWeek) {
                String text = "本周第一天:" + firstDayOfWeek.toString("yyyy年M月d日")
                        + ",本周最后一天:" + new DateTime(firstDayOfWeek).plusDays(6).toString("yyyy年M月d日");
                tvWeekChange.setText(text);
            }
        });

    }

    int plus = 0;
    public void addEvent(View view) {
        eventDates.add(new DateTime().plusDays(plus ++));
        weekCalendar.refresh();
    }

    public void gotoDate(View view) {
        weekCalendar.gotoDate(new DateTime().plusWeeks((int) (Math.random() * 10)));
    }

    public void gotoToday(View view) {
        weekCalendar.gotoDate(new DateTime());
    }

    public void setSelectDate(View view) {
        weekCalendar.setSelectDateTime(new DateTime().plusDays(1));
    }

    public void getCurrentFirstDay(View view) {
        String text = "当前页面第一天：" + weekCalendar.getCurrentFirstDay().toString("yyyy年M月d日");
        currentFirstDay.setText(text);
    }
}

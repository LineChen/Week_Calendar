package com.beiing.weekcalendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.beiing.weekcalendar.adapter.CalendarPagerAdapter;
import com.beiing.weekcalendar.adapter.WeekAdapter;
import com.beiing.weekcalendar.utils.GetViewHelper;

import org.joda.time.DateTime;

/**
 * Created by linechen on 2017/5/18.<br/>
 * 描述：周日历
 * </br>
 */

public class WeekCalendar extends LinearLayout {

    public static final int DAYS_OF_WEEK = 7;

    private static final String TAG = "WeekCalendar";
    /**
     * 日历星期
     */
    private GridView weekGrid;
    /**
     * 日历日期
     */
    private ViewPager viewPagerContent;

    private GetViewHelper getViewHelper;


    public WeekCalendar(Context context) {
        this(context, null);
    }

    public WeekCalendar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {

    }

    private void initView() {
        setOrientation(VERTICAL);
        addHeaderView();
        addWeekView();
    }

    private void addHeaderView() {
        View header =  LayoutInflater.from(getContext()).inflate(R.layout.layout_calender_header, this, false);
        weekGrid = (GridView) header.findViewById(R.id.grid_week);
        addView(header);
        weekGrid.setAdapter(new WeekAdapter(getViewHelper));
    }

    private void addWeekView() {
        View calendar = LayoutInflater.from(getContext()).inflate(R.layout.layout_calendar_content, this, false);
        viewPagerContent = (ViewPager) calendar.findViewById(R.id.viewpager_calendar);
        addView(calendar);
        DateTime startDay = new DateTime();
        startDay = startDay.minusDays(startDay.getDayOfWeek());
        CalendarPagerAdapter calendarPagerAdapter = new CalendarPagerAdapter(getContext(), 100, startDay, getViewHelper);
        viewPagerContent.setAdapter(calendarPagerAdapter);
        viewPagerContent.setCurrentItem(50);
    }

    public void setGetViewHelper(GetViewHelper getViewHelper) {
        this.getViewHelper = getViewHelper;
        initView();
    }
}

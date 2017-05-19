package com.beiing.weekcalendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.beiing.weekcalendar.adapter.CalendarPagerAdapter;

import org.joda.time.DateTime;

/**
 * Created by linechen on 2017/5/18.<br/>
 * 描述：
 * </br>
 */

public class WeekCalendar extends LinearLayout {
    private static final String TAG = "WeekCalendar";
    /**
     * 日历头部
     */
    private GridView weekGrid;
    private ViewPager viewPagerContent;

    public WeekCalendar(Context context) {
        this(context, null);
    }

    public WeekCalendar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initView();
    }

    private void initAttrs(Context context, AttributeSet attrs) {

    }

    private void initView() {
        setOrientation(VERTICAL);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View header = inflater.inflate(R.layout.layout_calender_header, this, false);
        weekGrid = (GridView) header.findViewById(R.id.grid_week);
        View calendar = inflater.inflate(R.layout.layout_calendar_content, this, false);
        viewPagerContent = (ViewPager) calendar.findViewById(R.id.viewpager_calendar);
        addView(header);
        addView(calendar);

        DateTime startDay = new DateTime();
        startDay = startDay.minusDays(startDay.getDayOfWeek());
        Log.e(TAG, "today:" + startDay);
        viewPagerContent.setAdapter(new CalendarPagerAdapter(getContext(), 100, startDay));
        viewPagerContent.setCurrentItem(50);
    }


}

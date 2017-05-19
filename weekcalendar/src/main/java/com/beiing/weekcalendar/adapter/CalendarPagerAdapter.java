package com.beiing.weekcalendar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.beiing.weekcalendar.R;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import jackwharton_salvage.RecyclingPagerAdapter;

/**
 * Created by linechen on 2017/5/19.<br/>
 * 描述：
 * </br>
 */

public class CalendarPagerAdapter extends RecyclingPagerAdapter {

    private static final int DAYS_OF_WEEK = 7;
    private Context context;
    private int maxCount;
    private int centerPosition;
    /**本周第一天**/
    private DateTime startDateTime;

    public CalendarPagerAdapter(Context context, int maxCount, DateTime startDateTime) {
        this.context = context;
        this.maxCount = maxCount;
        this.startDateTime = startDateTime;
        centerPosition = maxCount / 2;
        Log.e("====", "startDateTime:" + startDateTime);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        WeekViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_calendar, container, false);
            convertView.setTag(new WeekViewHolder(convertView));
            viewHolder = new WeekViewHolder(convertView);
        } else {
            viewHolder = (WeekViewHolder) convertView.getTag();
        }
        int intervalWeeks = position - centerPosition;
        DateTime start = startDateTime.plusWeeks(intervalWeeks);
        final List<DateTime> dts = new ArrayList<>(DAYS_OF_WEEK);
        for (int i = 0; i < DAYS_OF_WEEK; i++) {
            dts.add(new DateTime(start).plusDays(i));
        }

        viewHolder.weekGrid.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return DAYS_OF_WEEK;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = new TextView(context);
                tv.setGravity(Gravity.CENTER);
                tv.setText(dts.get(position).toString("yyyy-MM-dd"));
                return tv;
            }
        });
        return convertView;
    }

    @Override
    public int getCount() {
        return maxCount;
    }

    private static class WeekViewHolder{
        GridView weekGrid;

        WeekViewHolder(View root) {
            weekGrid = (GridView) root.findViewById(R.id.grid_date);
        }
    }



}

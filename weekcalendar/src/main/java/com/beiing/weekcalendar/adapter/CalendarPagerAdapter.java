package com.beiing.weekcalendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.beiing.weekcalendar.R;
import com.beiing.weekcalendar.utils.GetViewHelper;

import org.joda.time.DateTime;

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
    private GetViewHelper getViewHelper;

    public CalendarPagerAdapter(Context context, int maxCount, DateTime startDateTime, GetViewHelper getViewHelper) {
        this.context = context;
        this.maxCount = maxCount;
        this.startDateTime = startDateTime;
        this.getViewHelper = getViewHelper;
        centerPosition = maxCount / 2;
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
        viewHolder.weekGrid.setAdapter(new DayAdapter(start, getViewHelper));
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

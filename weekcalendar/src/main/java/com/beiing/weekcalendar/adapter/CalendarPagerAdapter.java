package com.beiing.weekcalendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.beiing.weekcalendar.R;
import com.beiing.weekcalendar.listener.DateSelectListener;
import com.beiing.weekcalendar.listener.GetViewHelper;

import org.joda.time.DateTime;

import jackwharton_salvage.RecyclingPagerAdapter;

/**
 * Created by linechen on 2017/5/19.<br/>
 * 描述：
 * </br>
 */

public class CalendarPagerAdapter extends RecyclingPagerAdapter {

    private Context context;
    private int maxCount;
    private int centerPosition;
    /**开始显示周的第一天：默认显示今天所在的那一周**/
    private DateTime startDateTime;
    /**日期选择:默认是今天**/
    private DateTime selectDateTime;
    private GetViewHelper getViewHelper;
    private DateSelectListener dateSelectListener;

    public CalendarPagerAdapter(Context context, int maxCount, DateTime startDateTime, GetViewHelper getViewHelper) {
        this.context = context;
        this.maxCount = maxCount;
        this.startDateTime = startDateTime;
        this.getViewHelper = getViewHelper;
        centerPosition = maxCount / 2;
        selectDateTime = new DateTime();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        WeekViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_calendar, container, false);
            viewHolder = new WeekViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (WeekViewHolder) convertView.getTag();
        }
        int intervalWeeks = position - centerPosition;
        DateTime start = startDateTime.plusWeeks(intervalWeeks);
        final DayAdapter dayAdapter = new DayAdapter(start, getViewHelper, selectDateTime);
        viewHolder.weekGrid.setAdapter(dayAdapter);
        viewHolder.weekGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectDateTime = dayAdapter.getItem(position);
                dayAdapter.setSelectDateTime(selectDateTime);
                notifyDataSetChanged();
                if(dateSelectListener != null){
                    dateSelectListener.onDateSelect(selectDateTime);
                }
            }
        });
        return convertView;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
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

    public DateTime getSelectDateTime() {
        return selectDateTime;
    }

    public void setSelectDateTime(DateTime selectDateTime) {
        this.selectDateTime = selectDateTime;
    }

    public void setDateSelectListener(DateSelectListener dateSelectListener) {
        this.dateSelectListener = dateSelectListener;
    }

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(DateTime startDateTime) {
        this.startDateTime = startDateTime;
        notifyDataSetChanged();
    }
}

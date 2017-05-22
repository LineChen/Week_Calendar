package com.beiing.weekcalendar.listener;

import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;

/**
 * Created by linechen on 2017/5/19.<br/>
 * 描述：
 * </br>
 */

public interface GetViewHelper {

    View getDayView(int position, View convertView, ViewGroup parent, DateTime dateTime, boolean select);

    View getWeekView(int position, View convertView, ViewGroup parent, String week);

}

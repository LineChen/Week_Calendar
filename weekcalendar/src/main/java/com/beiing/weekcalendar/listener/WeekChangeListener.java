package com.beiing.weekcalendar.listener;

import org.joda.time.DateTime;

/**
 * Created by linechen on 2017/5/22.<br/>
 * 描述：
 * </br>
 */

public interface WeekChangeListener {
    void onWeekChanged(DateTime firstDayOfWeek);
}

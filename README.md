# Week_Calendar

一个按周显示的日历，布局完全自定义。

##使用

布局：

```java
    <com.beiing.weekcalendar.WeekCalendar
        android:id="@+id/week_calendar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:wc_headerBgColor="#ccc"
        app:wc_headerHeight="60dp"
        app:wc_calendarHeight="55dp" />

```

代码中：

- 设置布局显示

> 必须调用`setGetViewHelper`方法加载布局，`getDayView`方法控制每一天显示，
> `getWeekView`方法控制星期显示，使用类似ListView中BaseAdapter中的getView方法。

```java
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


```


- 设置日期选择监听

```java

 weekCalendar.setDateSelectListener(new DateSelectListener() {
            @Override
            public void onDateSelect(DateTime selectDate) {
                String text = "你选择的日期是：" + selectDate.toString("yyyy-MM-dd");
                tvSelectDate.setText(text);
            }
        });

```


- 设置周变化监听

```java
weekCalendar.setWeekChangedListener(new WeekChangeListener() {
            @Override
            public void onWeekChanged(DateTime firstDayOfWeek) {
                String text = "本周第一天:" + firstDayOfWeek.toString("yyyy年M月d日")
                        + ",本周最后一天:" + new DateTime(firstDayOfWeek).plusDays(6).toString("yyyy年M月d日");
                tvWeekChange.setText(text);
            }
        });

```

**其他方法**

- `getSelectDateTime` 获取当前选中日期

- `gotoDate(DateTime dateTime)` 跳转到指定日期

- `refresh` 刷新界面





#License

```
   Copyright 2017 LineChen

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```









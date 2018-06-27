package ru.rubiconepro.study.Modules.Schedule.Layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.rubiconepro.study.Modules.Schedule.Model.DayModel;
import ru.rubiconepro.study.Modules.Schedule.Scheduler;
import ru.rubiconepro.study.R;

public class SchedulerMainLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler_main_layout);

        List<EventDay> events = new ArrayList<>();

        List<DayModel> models = Scheduler.Current().getModels();

        for (DayModel m : models)
            events.add(new EventDay(m.getEventDate(), R.drawable.sample_icon));

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setEvents(events);

        //calendarView.setOnDayClickListener(this);
    }
}

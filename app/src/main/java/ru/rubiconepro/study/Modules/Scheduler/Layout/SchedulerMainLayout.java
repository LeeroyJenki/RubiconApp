package ru.rubiconepro.study.Modules.Scheduler.Layout;

import android.os.Bundle;
import android.widget.Button;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.List;

import ru.rubiconepro.study.Modules.Base.Layout.BaseLayout;
import ru.rubiconepro.study.Modules.Scheduler.Additional.EventDayEx;
import ru.rubiconepro.study.Modules.Scheduler.Dialog.DayDialog;
import ru.rubiconepro.study.Modules.Scheduler.Model.DayModel;
import ru.rubiconepro.study.Modules.Scheduler.Scheduler;
import ru.rubiconepro.study.R;

public class SchedulerMainLayout extends BaseLayout implements OnDayClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler_main_layout);

        List<EventDay> events = new ArrayList<>();

        List<DayModel> models = Scheduler.Current().getModels();

        for (int i = 0; i < models.size(); i++) {
            DayModel m = models.get(i);
            EventDayEx d = new EventDayEx(m.getEventDate(), R.drawable.sample_icon);
            d.setModelIndex(i);
            events.add(d);
        }

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setEvents(events);

        calendarView.setOnDayClickListener(this);
    }

    DayDialog dialog = null;
    @Override
    public void onDayClick(EventDay eventDay) {
        if (eventDay == null) return;
        if (!eventDay.getClass().equals(EventDayEx.class)) return;

        EventDayEx ex = (EventDayEx)eventDay;
        if (ex.getModelIndex() == -1) return;

        if (dialog == null) dialog = new DayDialog(this);
        dialog.show(ex.getModelIndex());
    }
}

package ru.rubiconepro.study.Modules.Scheduler.Additional;

import com.applandeo.materialcalendarview.EventDay;

import java.util.Calendar;

public class EventDayEx extends EventDay {

    public EventDayEx(Calendar day) {
        super(day);
    }

    public EventDayEx(Calendar day, int imageResource) {
        super(day, imageResource);
    }

    private int modelIndex = -1;

    public int getModelIndex() {
        return modelIndex;
    }

    public void setModelIndex(int modelIndex) {
        this.modelIndex = modelIndex;
    }

}

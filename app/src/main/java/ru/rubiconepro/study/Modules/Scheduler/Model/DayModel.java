package ru.rubiconepro.study.Modules.Scheduler.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.rubiconepro.study.Modules.Base.Model.BaseModel;

public class DayModel extends BaseModel {

    //Для какого дня
    private Calendar eventDate;
    //Какие события
    private List<EventModel> events;

    public DayModel() {
        eventDate = Calendar.getInstance();
        events = new ArrayList<>();
    }

    @Override
    public String getIDName() {
        return null;
    }

    public Calendar getEventDate() {
        return eventDate;
    }
    public void setEventDate(Calendar eventDate) {
        this.eventDate = eventDate;
    }

    public List<EventModel> getEvents() {
        return events;
    }

    public void addEvent(EventModel model) {
        events.add(model);
    }
}

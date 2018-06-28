package ru.rubiconepro.study.Modules.Schedule.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ru.rubiconepro.study.Modules.Base.Model.BaseModel;

public class DayModel extends BaseModel {

    private Calendar eventDate;
    private List<EventModel> events;

    public void setEvents(List<EventModel> events) {
        this.events = events;
    }

    public List<EventModel> getEvents() {
        return events;
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
}

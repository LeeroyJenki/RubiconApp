package ru.rubiconepro.study.Modules.Scheduler.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ru.rubiconepro.study.Modules.Base.Model.BaseModel;

public class EventModel extends BaseModel {

    private String name;
    private String type;
    private Calendar time;

    private List<SubModel> subs;

    private void initFields() {
        name = "";
        type = "";
        time = Calendar.getInstance();
    }

    public EventModel () {
        initFields();
    }

    public EventModel(String name) {
        initFields();
        this.name = name;
    }


    @Override
    public String getIDName() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }
}

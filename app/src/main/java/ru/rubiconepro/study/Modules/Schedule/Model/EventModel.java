package ru.rubiconepro.study.Modules.Schedule.Model;

import java.util.Date;
import java.util.List;

import ru.rubiconepro.study.Modules.Base.Model.BaseModel;

public class EventModel extends BaseModel {

    private String name;
    private String type;
    private Date time;

    private List<SubModel> subs;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Date getTime() {
        return time;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<SubModel> getSubs() {
        return subs;
    }

    public void setSubs(List<SubModel> subs) {
        this.subs = subs;
    }

    @Override
    public String getIDName() {
        return null;
    }
}

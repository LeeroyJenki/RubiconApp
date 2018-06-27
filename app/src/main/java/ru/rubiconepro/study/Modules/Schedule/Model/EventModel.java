package ru.rubiconepro.study.Modules.Schedule.Model;

import java.util.Date;
import java.util.List;

import ru.rubiconepro.study.Modules.Base.Model.BaseModel;

public class EventModel extends BaseModel {

    private String name;
    private String type;
    private Date time;

    private List<SubModel> subs;


    @Override
    public String getIDName() {
        return null;
    }
}

package ru.rubiconepro.study.Modules.Schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ru.rubiconepro.study.Modules.Base.Base;
import ru.rubiconepro.study.Modules.Schedule.Model.DayModel;

public class Scheduler extends Base {
    /**
     * Методы синглтона
     */
    private static final Scheduler _instance = new Scheduler();
    public static Scheduler Current() {
        return _instance;
    }

    private final List<DayModel> models = new ArrayList<>();
    public List<DayModel> getModels() {
        return models;
    }

    private Scheduler() {
        for (int i = 0; i < 10; i++) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);

            c.add(Calendar.DAY_OF_MONTH, i);

            DayModel m = new DayModel();
            m.setEventDate(c);

            models.add(m);

            //TODO Добавить внутряшку первой вложенности (EventModel)
            //TODO Сделать отображение
        }
    }



}

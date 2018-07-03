package ru.rubiconepro.study.Modules.Scheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.rubiconepro.study.Modules.Base.Base;
import ru.rubiconepro.study.Modules.Scheduler.Model.DayModel;
import ru.rubiconepro.study.Modules.Scheduler.Model.EventModel;

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
            //Тут мы создаем первый уровень
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);

            c.add(Calendar.DAY_OF_MONTH, i);

            DayModel m = new DayModel();
            m.setEventDate(c);

            models.add(m);

            //Второй уровень данных

            for (int j = 0; j < 10; j++) {
                m.addEvent(new EventModel("Элемент - " + j));
            }

            //TODO Добавить внутряшку первой вложенности (EventModel)
            //TODO Сделать отображение
        }
    }

    protected void storeResponce(byte[] data) throws Exception {

    }



}

package ru.rubiconepro.study.Modules.StaticText;

import ru.rubiconepro.study.Modules.Base.Base;
import ru.rubiconepro.study.Modules.StaticText.Model.StaticTextModel;

import java.util.ArrayList;
import java.util.List;

public class StaticText extends Base {

    private static final StaticText _instance = new StaticText();
    public static StaticText Current() {
        return  _instance;
    }

    private List<StaticTextModel> models;

    private StaticText() {
        models = new ArrayList<>();
    }

    public List<StaticTextModel> getList() {
        return models;
    }

    public void addElement(StaticTextModel model) {
        if (!models.contains(model))
            models.add(model);
    }

    public void clearList() {
        models.clear();
    }


    protected void storeResponce(byte[] data) throws Exception {

    }
}

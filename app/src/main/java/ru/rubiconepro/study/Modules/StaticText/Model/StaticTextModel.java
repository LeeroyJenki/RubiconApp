package ru.rubiconepro.study.Modules.StaticText.Model;

import ru.rubiconepro.study.Modules.Base.Model.BaseModel;

public class StaticTextModel extends BaseModel {
    public String name;
    public String shorttext;
    public String text;

    @Override
    public String getIDName() {
        return "rstaticid";
    }
}

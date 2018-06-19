package ru.rubiconepro.study.Modules.StaticText.Model;

import org.json.JSONObject;

import ru.rubiconepro.study.Modules.Base.Model.BaseModel;

public class StaticTextModel extends BaseModel {
    public String name;
    public String shorttext;
    public String text;

    @Override
    public String getIDName() {
        return "rstaticid";
    }

    public StaticTextModel() {
        super();
        name = "";
        shorttext = "";
        text = "";
    }

    public StaticTextModel(JSONObject obj) {
        super(obj);

        name = stringFromJson("name", "");
        shorttext = stringFromJson("shorttext", "");
        text = stringFromJson("text", "");
    }
}

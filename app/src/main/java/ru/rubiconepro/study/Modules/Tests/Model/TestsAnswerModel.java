package ru.rubiconepro.study.Modules.Tests.Model;

import ru.rubiconepro.study.Modules.Base.Model.BaseModel;

/**
 * Класс модель для хранения ответов на тест
 */
public class TestsAnswerModel extends BaseModel {

    private String text;
    private Boolean isRight;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }

    public TestsAnswerModel() {
        text = "";
        isRight = false;
    }

    @Override
    public String getIDName() {
        return "ID";
    }
}

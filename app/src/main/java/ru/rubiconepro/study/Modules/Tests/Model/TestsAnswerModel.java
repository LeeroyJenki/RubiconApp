package ru.rubiconepro.study.Modules.Tests.Model;

import ru.rubiconepro.study.Modules.Base.Model.BaseModel;

/**
 * Класс модель для хранения ответов на тест
 */
public class TestsAnswerModel extends BaseModel {

    private String text;
    private Boolean isRight;

    public TestsAnswerModel() {

    }

    @Override
    public String getIDName() {
        return "ID";
    }
}

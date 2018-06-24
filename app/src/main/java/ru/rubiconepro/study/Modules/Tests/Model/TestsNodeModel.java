package ru.rubiconepro.study.Modules.Tests.Model;

import ru.rubiconepro.study.Modules.Base.Model.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель данных для элемента тестов
 * Будет содержать название, и описание теста
 * В том числе и верные и неверные ответы
 */
public class TestsNodeModel extends BaseModel {

    private String name;
    private String text;

    private final List<TestsAnswerModel> answers = new ArrayList<>();


    public TestsNodeModel() {

    }

    public List<TestsAnswerModel> getAnswers () {
        return answers;
    }

    @Override
    public String getIDName() {
        return "ID";
    }


}

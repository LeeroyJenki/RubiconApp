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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addAnswer(TestsAnswerModel model) {
        answers.add(model);
    }

    public TestsNodeModel() {

    }

    public List<TestsAnswerModel> getAnswers () {
        return answers;
    }

    @Override
    public String getIDName() {
        return "ID";
    }

    public boolean isRightQuestion(){
        boolean right = true;

        for (TestsAnswerModel m : answers) {
            if (!m.isRightAnswer())
                right = false;
        }

        return right;
    }


}

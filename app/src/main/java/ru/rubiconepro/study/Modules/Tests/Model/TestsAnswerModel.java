package ru.rubiconepro.study.Modules.Tests.Model;

import ru.rubiconepro.study.Modules.Base.Model.BaseModel;

/**
 * Класс модель для хранения ответов на тест
 */
public class TestsAnswerModel extends BaseModel {

    //Переменные из АПИ
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
        userAnswer = false;
    }

    @Override
    public String getIDName() {
        return "ID";
    }



    //Переменные для внутренних нужд
    private Boolean userAnswer;
    public Boolean getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Boolean userAnswer) {
        this.userAnswer = userAnswer;
    }

    public boolean isRightAnswer() {
        return isRight == userAnswer;
    }


}

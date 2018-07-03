package ru.rubiconepro.study.Modules.Tests;

import ru.rubiconepro.study.Modules.Base.Base;
import ru.rubiconepro.study.Modules.Tests.Model.TestsAnswerModel;
import ru.rubiconepro.study.Modules.Tests.Model.TestsNodeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранилище данных для тестов
 */
public class Tests extends Base {

    private Tests () {

        for (int i = 0; i < 10; i++) {
            TestsNodeModel model = new TestsNodeModel();

            model.setName("Question name - " + i);
            model.setText("Question text - " + i);

            for (int j = 0; j < 4; j++) {
                TestsAnswerModel answer = new TestsAnswerModel();
                answer.setText("Answer#" + j + " for question - " + i);
                model.addAnswer(answer);
            }
            items.add(model);
        }


    }

    /**
     * Методы синглтона
     */
    private static final Tests _instance = new Tests();
    public static Tests Current() {
        return _instance;
    }


    private final List<TestsNodeModel> items = new ArrayList<>();
    public List<TestsNodeModel> getList() {
        return items;
    }


    protected void storeResponce(byte[] data) throws Exception {

    }
}

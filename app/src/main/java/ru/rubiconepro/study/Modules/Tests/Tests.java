package ru.rubiconepro.study.Modules.Tests;

import ru.rubiconepro.study.Modules.Base.Base;
import ru.rubiconepro.study.Modules.Tests.Model.TestsNodeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Хранилище данных для тестов
 */
public class Tests extends Base {

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
}

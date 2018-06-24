package ru.rubiconepro.study.Modules.Tests.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import ru.rubiconepro.study.Modules.Tests.Model.TestsAnswerModel;
import ru.rubiconepro.study.Modules.Tests.Model.TestsNodeModel;
import ru.rubiconepro.study.Modules.Tests.Tests;
import ru.rubiconepro.study.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для управления и отображения отоветами на тест
 */
public class TestsListAdapter extends BaseAdapter {

    /**
     * Обязательные элементы
     * Контекст, и инфлетер лайоутов
     */
    Context context;
    LayoutInflater inflater;


    /**
     * Основной массив из которого мы
     * отображаем элементы
     */
    List<TestsAnswerModel> answers = new ArrayList<>();

    public TestsListAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public void setCurrent(int index) {
        TestsNodeModel m = Tests.Current().getList().get(index);
        answers.clear();

        for (TestsAnswerModel item : m.getAnswers()) {
            answers.add(item);
        }

        notifyDataSetInvalidated();
    }


    /**
     * Функции интерфейса Адаптера
     */

    @Override
    public int getCount() {
        return answers.size();
    }

    @Override
    public Object getItem(int i) {
        return answers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return answers.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.tests_list_item, viewGroup, false);
        }

        //TODO Заполнить данные

        return view;
    }
}

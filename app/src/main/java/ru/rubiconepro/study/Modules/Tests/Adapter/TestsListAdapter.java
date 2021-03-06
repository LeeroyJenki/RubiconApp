package ru.rubiconepro.study.Modules.Tests.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import ru.rubiconepro.study.Modules.Tests.Model.TestsAnswerModel;
import ru.rubiconepro.study.Modules.Tests.Model.TestsNodeModel;
import ru.rubiconepro.study.Modules.Tests.Tests;
import ru.rubiconepro.study.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для управления и отображения отоветами на тест
 */
public class TestsListAdapter extends BaseAdapter implements View.OnClickListener {

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

        TestsAnswerModel model = answers.get(i);
        Switch sw = view.findViewById(R.id.swAnswer);
        sw.setText(model.getText());
        sw.setChecked(model.getUserAnswer());

        sw.setTag(i);
        sw.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Switch s = (Switch)view;

        int index = (int)s.getTag();
        answers.get(index).setUserAnswer(s.isChecked());
    }
}

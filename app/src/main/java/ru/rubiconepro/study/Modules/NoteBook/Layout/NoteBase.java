package ru.rubiconepro.study.Modules.NoteBook.Layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import ru.rubiconepro.study.Modules.Base.Dialog.PromptDialog;
import ru.rubiconepro.study.Modules.Base.Interface.IPromptDialog;
import ru.rubiconepro.study.Modules.NoteBook.Adapter.IAdapter;
import ru.rubiconepro.study.R;

/**
 * Базовый класс для отображения категорий и записей
 * Содержит общие методы и вьюшки для обеих лайаутов
 */
public  abstract class NoteBase extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    /**
     * Адаптер который передается в лист вью для отображения данных
     */
    IAdapter adapter;

    /**
     * Делегат получения адаптера отображения данных
     * В случае если возвращаемый параметр равен нулю
     * закрывается текущий активити
     * @return экземпляр адаптера данных (Может быть NULL)
     */
    protected abstract IAdapter createAdapter();

    /**
     * Получение заголовка лайаута
     * @return
     */
    protected abstract String getHeaderTitle();

    /**
     * Получение надписи для кнопки
     * @return
     */
    protected abstract String getButtonTitle();

    /**
     * Функция вызывается при создании элемента
     * Так как у нас каждый лайаут имеет свои собственные данные
     * то и создавать элемент он должен сам. Базовый класс только
     * создает алерт, и затем просит класс наследник создать элемент
     * с нужным заголовком
     * @param text
     */
    protected abstract void createElement(String text);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part);

        final Switch switchEdit = findViewById(R.id.switchEdit);
        switchEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            adapter.setEditable(switchEdit.isChecked());
            }
        });

        Button b = findViewById(R.id.btn_AddPart);
        b.setOnClickListener(this);
        b.setText(getButtonTitle());

        ListView lv = findViewById(R.id.listWiewPart);
        lv.setOnItemClickListener(this);

        adapter = createAdapter();
        if (adapter == null)
            finish();

        lv.setAdapter(adapter);

        ((TextView)findViewById(R.id.textView)).setText(getHeaderTitle());
    }

    @Override
    public void onClick(View v) {
        new PromptDialog(this, "Добавление элемента", new IPromptDialog() {
            @Override
            public void dialogDone(boolean result, String text) {
                if (!result)
                    return;

                createElement(text);
                adapter.reloadData();
            }
        }).show();
    }



}

package ru.rubiconepro.study.Modules.NoteBook.Layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import ru.rubiconepro.study.Modules.NoteBook.Adapter.PartAdapter;
import ru.rubiconepro.study.Modules.NoteBook.Const.IntentConst;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartModel;
import ru.rubiconepro.study.Modules.NoteBook.NoteBook;

public class Note extends NoteBase {
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Защита от програмиста
        Intent currentIntent = getIntent();
        if (!currentIntent.hasExtra(IntentConst.position)) {
            Toast.makeText(this, "Не переданы параметры", Toast.LENGTH_SHORT).show();
            return;
        }

        position = currentIntent.getIntExtra(IntentConst.position, 0);
    }

    protected void createAdapter() {
        PartModel model = NoteBook.instance.getPartByPosition(position);

        if (model == null) {
            Toast.makeText(this, "Попытка выбора несуществуюший категории", Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO ПЕРЕДЕЛАТЬ
        adapter = new PartAdapter(this);
    }

    protected String getHeaderTitle() {
        return "Записи";
    }

    protected String getButtonTitle() {
        return "Создать запись";
    }

    protected void createElement(String text) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

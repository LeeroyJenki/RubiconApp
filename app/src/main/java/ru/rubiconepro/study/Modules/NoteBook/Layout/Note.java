package ru.rubiconepro.study.Modules.NoteBook.Layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import ru.rubiconepro.study.Modules.NoteBook.Adapter.NoteAdapter;
import ru.rubiconepro.study.Modules.NoteBook.Adapter.PartAdapter;
import ru.rubiconepro.study.Modules.NoteBook.Const.IntentConst;
import ru.rubiconepro.study.Modules.NoteBook.Model.NotesModel;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartModel;
import ru.rubiconepro.study.Modules.NoteBook.NoteBook;

public class Note extends NoteBase {
    int positionPart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Защита от програмиста
        Intent currentIntent = getIntent();
        if (!currentIntent.hasExtra(IntentConst.positionPart)) {
            Toast.makeText(this, "Не переданы параметры", Toast.LENGTH_SHORT).show();
            return;
        }

        positionPart = currentIntent.getIntExtra(IntentConst.positionPart, 0);

    }

    protected void createAdapter() {
        Intent intt = getIntent();
        positionPart = intt.getIntExtra("positionPart", 0);
        PartModel model = NoteBook.instance.getPartByPosition(positionPart);

        if (model == null) {
            Toast.makeText(this, "Попытка выбора несуществуюший категории", Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO ПЕРЕДЕЛАТЬ
        adapter = new NoteAdapter(this, positionPart);
    }

    protected String getHeaderTitle() {
        return "Записи";
    }

    protected String getButtonTitle() {
        return "Создать запись";
    }

    protected void createElement(String text) {
        NotesModel nm = new NotesModel();
        nm.title = text;
        nm.text = "";
        nm.notesList.add(nm);
        NoteBook.instance.addNote(nm, positionPart);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

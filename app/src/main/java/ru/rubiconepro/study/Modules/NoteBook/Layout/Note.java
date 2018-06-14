package ru.rubiconepro.study.Modules.NoteBook.Layout;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import ru.rubiconepro.study.Modules.NoteBook.Adapter.IAdapter;
import ru.rubiconepro.study.Modules.NoteBook.Adapter.NoteAdapter;
import ru.rubiconepro.study.Modules.NoteBook.Adapter.PartAdapter;
import ru.rubiconepro.study.Modules.NoteBook.Const.IntentConst;
import ru.rubiconepro.study.Modules.NoteBook.Model.NotesModel;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartModel;
import ru.rubiconepro.study.Modules.NoteBook.NoteBook;

/**
 * Класс - Лайаут для отображения записей для конкретной категории
 */
public class Note extends NoteBase {
    int positionPart;

    protected IAdapter createAdapter() {
        Intent intent = getIntent();
        positionPart = intent.getIntExtra("positionPart", 0);
        PartModel model = NoteBook.instance.getPartByPosition(positionPart);

        if (model == null) {
            Toast.makeText(this, "Попытка выбора несуществуюший категории", Toast.LENGTH_SHORT).show();
            return null;
        }

        NoteAdapter a = new NoteAdapter(this, positionPart);
        a.setBtnAddVisible(true);
        return a;
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
        NoteBook.instance.addNote(nm, positionPart, -1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, NotesAdd.class);
        i.putExtra(IntentConst.position, position);
        i.putExtra("positionPart", positionPart);
        i.putExtra("position", position);
        startActivity(i);
    }
}

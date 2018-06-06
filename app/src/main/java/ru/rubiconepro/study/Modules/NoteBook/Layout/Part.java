package ru.rubiconepro.study.Modules.NoteBook.Layout;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import ru.rubiconepro.study.Modules.NoteBook.Adapter.PartAdapter;
import ru.rubiconepro.study.Modules.NoteBook.Const.IntentConst;
import ru.rubiconepro.study.Modules.NoteBook.NoteBook;

public class Part extends NoteBase {

    protected void createAdapter() {
        adapter = new PartAdapter(this);
    }

    protected void createElement(String text) {
        NoteBook.instance.addPart(text);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, Note.class);
        i.putExtra(IntentConst.position, position);
        startActivity(i);
    }

    protected String getHeaderTitle() {
        return "Категории";
    }
    protected String getButtonTitle() {
        return "Создать категорию";
    }
}

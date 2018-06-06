package ru.rubiconepro.study.Modules.NoteBook.Layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;

import ru.rubiconepro.study.Modules.Base.Dialog.PromptDialog;
import ru.rubiconepro.study.Modules.Base.Interface.IPromptDialog;
import ru.rubiconepro.study.Modules.NoteBook.Adapter.PartAdapter;
import ru.rubiconepro.study.Modules.NoteBook.NoteBook;
import ru.rubiconepro.study.R;

public class Part extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    PartAdapter adapter;

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

        findViewById(R.id.btn_AddPart).setOnClickListener(this);

        ListView lv = findViewById(R.id.listWiewPart);
        lv.setOnItemClickListener(this);

        adapter = new PartAdapter(this);
        lv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, Note.class);
        i.putExtra("position", position);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        new PromptDialog(this, "Добавление раздела", new IPromptDialog() {
            @Override
            public void dialogDone(boolean result, String text) {
                if (!result)
                    return;

                NoteBook.instance.addPart(text);
                adapter.notifyDataSetChanged();
            }
        }).show();
    }
}

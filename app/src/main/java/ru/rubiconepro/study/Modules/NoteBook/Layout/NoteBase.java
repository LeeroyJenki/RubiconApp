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

public  abstract class NoteBase extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    IAdapter adapter;

    protected abstract void createAdapter();
    protected abstract String getHeaderTitle();
    protected abstract String getButtonTitle();
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

        createAdapter();
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
                adapter.notifyDataSetChanged();
            }
        }).show();
    }



}

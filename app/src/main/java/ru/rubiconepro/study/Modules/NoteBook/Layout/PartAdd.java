package ru.rubiconepro.study.Modules.NoteBook.Layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ru.rubiconepro.study.Modules.NoteBook.Model.NotesModel;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartModel;
import ru.rubiconepro.study.R;

public class PartAdd extends AppCompatActivity implements View.OnClickListener {
    EditText titleName;
    EditText notestitleName;
    EditText notesTextName;



    PartModel model;

    int position = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_add);

        Button btnOk = findViewById(R.id.btn_OKAddPart);
        btnOk.setOnClickListener(this);

        titleName = findViewById(R.id.edt_partTitleAdd);
        notestitleName = findViewById(R.id.edt_notesTitleAdd);
        notesTextName = findViewById(R.id.edt_notesTextAdd);

        Intent i = getIntent();
        if (i.hasExtra("model")) {
            model = (PartModel) i.getSerializableExtra("model");
            position = i.getIntExtra("position", -1);
        } else {
            model = new PartModel();
            model.listNotes = new ArrayList<>();
        }


        titleName.setText(model.title);

        NotesModel m = null;
        if (model.listNotes.size() != 0)
            m = model.listNotes.get(0);
        else
            m = new NotesModel();

        notesTextName.setText(m.text);
        notestitleName.setText(m.title);

    }

    @Override
    public void onClick(View v) {
        model.title = titleName.getText().toString();



        NotesModel  nt = new NotesModel();
        nt.title = notestitleName.getText().toString();
        nt.text = notesTextName.getText().toString();

        if (model.listNotes.size() != 0)
            model.listNotes.remove(0);

        model.listNotes.add(nt);


        Intent i = new Intent();
        i.putExtra("position", position);
        i.putExtra("item", model);

        setResult(0, i);
        finish();
    }
}

package ru.rubiconepro.study.Modules.NoteBook.Layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ru.rubiconepro.study.Modules.NoteBook.Adapter.PartAdapter;
import ru.rubiconepro.study.Modules.NoteBook.Model.NoteWrapper;
import ru.rubiconepro.study.Modules.NoteBook.Model.NotesModel;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartModel;
import ru.rubiconepro.study.Modules.NoteBook.NoteBook;
import ru.rubiconepro.study.R;

public class NotesAdd extends AppCompatActivity implements View.OnClickListener {
    EditText titleNameN;
    EditText notesTextN;
    int position;
    int positionPart;
    NoteWrapper model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_add);

        Button btnAdd = findViewById(R.id.btn_AddNotesN);
        btnAdd.setOnClickListener(this);

        titleNameN = findViewById(R.id.edt_notesTitleAddN);
        notesTextN = findViewById(R.id.edt_notesTextAddN);

        Intent intt = getIntent();
        positionPart = intt.getIntExtra("positionPart", 0);
        position =  intt.getIntExtra("position", 0);

        model = NoteBook.instance.getList(positionPart).get(position);

        titleNameN.setText(model.model.title.toString());
        notesTextN.setText(model.model.text.toString());

    }

    @Override
    public void onClick(View v) {

        NotesModel  nt = new NotesModel();
        nt.title = titleNameN.getText().toString();
        nt.text = notesTextN.getText().toString();
        nt.notesList.add(nt);

        model.model.title = titleNameN.getText().toString();
        model.model.text = notesTextN.getText().toString();
    //  model.listNotes.get(position).notesList.set(position, nt);



        Intent i = new Intent();

        i.putExtra("notes", nt);

        setResult(0, i);
        finish();
    }
}

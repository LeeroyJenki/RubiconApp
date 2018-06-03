package ru.rubiconepro.study.Modules.NoteBook.Layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ru.rubiconepro.study.Modules.NoteBook.Model.NotesModel;
import ru.rubiconepro.study.R;

public class NotesAdd extends AppCompatActivity implements View.OnClickListener {
    EditText titleNameN;
    EditText notesTextN;
    ArrayList<NotesModel> items;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_add);

        Button btnAdd = findViewById(R.id.btn_AddNotesN);
        btnAdd.setOnClickListener(this);

        titleNameN = findViewById(R.id.edt_notesTitleAddN);

        notesTextN = findViewById(R.id.edt_notesTextAddN);

        items = new ArrayList<>();

    }

    @Override
    public void onClick(View v) {

        NotesModel  nt = new NotesModel();
        nt.title = titleNameN.getText().toString();
        nt.text = notesTextN.getText().toString();
        nt.notesList.add(nt);


        Intent i = new Intent();

        i.putExtra("notes", nt);

        setResult(0, i);
        finish();
    }
}

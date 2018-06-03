package ru.rubiconepro.study.Modules.NoteBook.Layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ru.rubiconepro.study.Modules.NoteBook.Model.PartListModel;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartModel;
import ru.rubiconepro.study.R;

public class Note extends AppCompatActivity implements AdapterView.OnItemClickListener {
    List<PartModel> items;

    ListView lv;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        lv = findViewById(R.id.listWiewNotes);

        Intent intnt = getIntent();
        PartListModel ll = (PartListModel)intnt.getSerializableExtra("test");
        items = ll.items;

        //      items = new ArrayList<>();
        fillList();

        lv.setOnItemClickListener(this);
    }

    private void fillList() {
        List<String> data = new ArrayList<>();
        for (PartModel item : items) {
            for (int j = 0; j < item.listNotes.size(); j++ ){
                data.add(item.listNotes.get(j).title);
            }

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

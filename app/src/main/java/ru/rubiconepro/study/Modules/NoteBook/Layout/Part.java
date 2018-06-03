package ru.rubiconepro.study.Modules.NoteBook.Layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import ru.rubiconepro.study.Modules.NoteBook.Adapter.PartAdapter;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartListModel;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartModel;
import ru.rubiconepro.study.R;

public class Part extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    List<PartModel> items;
    ListView lv;

    Switch switchEdit;
    boolean isEditable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part);

        switchEdit = findViewById(R.id.switchEdit);
        switchEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEditable = switchEdit.isChecked();
                fillList();
            }
        });

        Button btnAddP = findViewById(R.id.btn_AddPart);
        btnAddP.setOnClickListener(this);

        lv = findViewById(R.id.listWiewPart);

        items = new ArrayList<>();
        fillList();

        lv.setOnItemClickListener(this);
    }

    private void fillList() {
//        List<String> data = new ArrayList<>();
//        for (PartModel item : items) {
//            data.add(item.title);
//        }

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
//        lv.setAdapter(adapter);

        PartListModel model = new PartListModel();
        model.items = items;

        PartAdapter adapter = new PartAdapter(model, this);
        adapter.setEditable(isEditable);

        lv.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, Note.class);
        i.putExtra("position", position);
        i.putExtra("item", items.get(position));

        PartListModel l = new PartListModel();
        l.items = items;
        i.putExtra("test", l);
        startActivityForResult(i, 0);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_AddPart) {

            Intent i = new Intent(this, PartAdd.class);
            startActivityForResult(i, 0);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            PartModel item = (PartModel) data.getSerializableExtra("item");
            int position = data.getIntExtra("position", -1);

            if (item != null) {

                if (position == -1)
                    items.add(item);
                else
                    items.set(position, item);

                fillList();
            }
        }
    }
}

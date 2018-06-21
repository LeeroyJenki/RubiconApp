package ru.rubiconepro.study.Modules.StaticText.Layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import okhttp3.Request;
import org.json.JSONArray;
import ru.rubiconepro.study.Lib.NetHTTP.Interface.IRequester;
import ru.rubiconepro.study.Lib.NetHTTP.Model.ResponceModel;
import ru.rubiconepro.study.Lib.NetHTTP.Requester;
import ru.rubiconepro.study.Modules.StaticText.Adapter.StaticTextListAdapter;
import ru.rubiconepro.study.Modules.StaticText.Model.StaticTextModel;
import ru.rubiconepro.study.Modules.StaticText.StaticText;
import ru.rubiconepro.study.R;

import java.util.ArrayList;
import java.util.List;

public class StaticTextList extends AppCompatActivity implements IRequester, AdapterView.OnItemClickListener {

    ListView lstView;
    StaticTextListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_text_list);

        lstView = findViewById(R.id.lstView);
        adapter = new StaticTextListAdapter(this);

        lstView.setAdapter(adapter);

        makeRequest();

        lstView.setOnItemClickListener(this);

    }

    private void makeRequest() {
        new Requester(this).execute(
                new Request.Builder()
                        .url("http://rubiconepro.fvds.ru/web/api/RStatic.php")
                        .build()
        );
    }

    @Override
    public void RequestDone(ResponceModel model) {
        StaticText.Current().clearList();
        JSONArray arr;
        try {
            arr = new JSONArray(model.responce.body().string());
            for (int i = 0; i < arr.length(); i++) {
                StaticText.Current().addElement(new StaticTextModel(arr.getJSONObject(i)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        adapter.notifyDataSetInvalidated();
    }

    @Override
    public void AllDone() {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, StaticTextDetail.class);
        intent.putExtra("index", i);

        startActivity(intent);
    }
}

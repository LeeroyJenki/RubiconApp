package ru.rubiconepro.study.Modules.StaticText.Layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import ru.rubiconepro.study.Modules.StaticText.Model.StaticTextModel;
import ru.rubiconepro.study.R;

public class StaticTextList extends AppCompatActivity {

    List<StaticTextModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_text_list);
    }
}

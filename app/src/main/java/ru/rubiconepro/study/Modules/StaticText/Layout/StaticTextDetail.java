package ru.rubiconepro.study.Modules.StaticText.Layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.webkit.WebView;
import ru.rubiconepro.study.Modules.StaticText.Model.StaticTextModel;
import ru.rubiconepro.study.Modules.StaticText.StaticText;
import ru.rubiconepro.study.R;

public class StaticTextDetail extends AppCompatActivity {

    int index;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_text_detail);

        webView = findViewById(R.id.webView);

        Intent intent = getIntent();
        if (!intent.hasExtra("index")) {
            finish();
            return;
        }

        index = intent.getIntExtra("index", 0);

        StaticTextModel model = StaticText.Current().getList().get(index);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL("", model.text, "text/html", "UTF-8", "");
    }
}

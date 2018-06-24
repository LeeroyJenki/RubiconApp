package ru.rubiconepro.study.Modules.Tests.Layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import ru.rubiconepro.study.Modules.Tests.Adapter.TestsListAdapter;
import ru.rubiconepro.study.R;

/**
 * Класс для прохождения тестов.
 *
 * Будет отображать название теста
 * его описание, и варианты ответов.
 *
 * Так же бедет возможность перехода, на
 * предыдущий - следующий тест.
 *
 * Для отображения результата, будет использован
 * новый класс
 *
 * TODO добавить документацию по классу отображения результата
 */
public class TestsPass extends AppCompatActivity {

    /**
     * Описываем компонентя формы
     */
    TextView tvName;
    WebView  wvDescription;
    ListView lstAnswers;
    Button   btnPrev;
    Button   btnNext;

    TestsListAdapter listAdapter;

    /**
     * Функция поиска компонентов
     * вызывается в конструкторе
     */
    private void initComponents() {
        this.tvName = findViewById(R.id.tvName);
        wvDescription = findViewById(R.id.wvDescription);

        wvDescription.getSettings().setJavaScriptEnabled(true);

        lstAnswers = findViewById(R.id.lstAnswers);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);

        btnPrev.setVisibility(View.INVISIBLE);
        btnNext.setVisibility(View.INVISIBLE);

        listAdapter = new TestsListAdapter(this);
        lstAnswers.setAdapter(listAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests_pass);

        initComponents();
    }


}

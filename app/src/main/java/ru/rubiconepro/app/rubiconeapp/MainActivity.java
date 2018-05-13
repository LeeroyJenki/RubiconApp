package ru.rubiconepro.app.rubiconeapp;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
        1 - Модуль тестов
            a - Создание/редактирование категорий
            б - Создание/редактирование тестов (варианты ответов)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
//        //Вызов активити из другого приложения
//        Intent intent = new Intent();
//        intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings"));
//        startActivity(intent);
    }
}

package ru.rubiconepro.study.Modules.Schedule.Layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import ru.rubiconepro.study.R;

public class SchedulerEventLayout extends AppCompatActivity {

    TextView tvNameEv;
    TextView tvTypeEv;
    TextView tvDateEv;
    ListView lstSubs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler_event_layout);

        tvNameEv = findViewById(R.id.tvNameEv);
        tvTypeEv = findViewById(R.id.tvTypeEv);
        tvDateEv = findViewById(R.id.tvDateEvString);
        


    }
}

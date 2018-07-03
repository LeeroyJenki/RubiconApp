package ru.rubiconepro.study.Modules.TestLayout.Layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.Request;
import ru.rubiconepro.study.Modules.Base.Helper.RequestHelper;
import ru.rubiconepro.study.Modules.Base.Interface.IDone;
import ru.rubiconepro.study.Modules.PDFView.Layout.PDFViewLayout;
import ru.rubiconepro.study.Modules.PDFView.PDFView;
import ru.rubiconepro.study.R;

public class TestLayout extends AppCompatActivity implements View.OnClickListener, IDone {

    Button btn;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_layout);

        btn = findViewById(R.id.btn);
        edt = findViewById(R.id.edt);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        String str = edt.getText().toString();
//        new RequestHelper(this,
//                str,
//                new Intent(this, PDFViewLayout.class));

        Request r = null;
        try {
            r = new Request.Builder().url(str).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            r = null;
        }
        if (r == null) {
            Toast.makeText(this, "Запрос неверен!", Toast.LENGTH_SHORT).show();
            return;
        }
        PDFView.Current().loadData(r,
                this);
    }

    @Override
    public void JobDone(boolean success) {
        if (!success) {
            Toast.makeText(this, "Что то пошло не так!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent i = new Intent(this, PDFViewLayout.class);
        startActivity(i);
    }
}

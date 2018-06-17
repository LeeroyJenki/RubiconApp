package ru.rubiconepro.study.Modules.Test.Layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import okhttp3.Request;
import ru.rubiconepro.study.Lib.NetHTTP.Interface.IRequester;
import ru.rubiconepro.study.Lib.NetHTTP.Model.ResponceModel;
import ru.rubiconepro.study.Lib.NetHTTP.Requester;
import ru.rubiconepro.study.R;

public class TestRequest extends AppCompatActivity implements View.OnClickListener, IRequester {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_request);

        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        new Requester(this).execute(
                new Request.Builder()
                    .url("http://rubiconepro.fvds.ru/web/api/RStatic.php")
                    .build()
        );
    }

    @Override
    public void RequestDone(ResponceModel model) {
        String s = "";
        try {
            s = model.responce.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}

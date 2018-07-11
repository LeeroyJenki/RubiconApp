package ru.rubiconepro.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import ru.rubiconepro.study.Modules.Base.Helper.RequestHelper;
import ru.rubiconepro.study.Modules.Base.Layout.BaseLayout;
import ru.rubiconepro.study.Modules.NoteBook.Layout.Part;
import ru.rubiconepro.study.Modules.NoteBook.NoteBook;
import ru.rubiconepro.study.Modules.User.Layout.UserLogin;

public class MainActivity extends BaseLayout implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        Button btn = findViewById(R.id.btnNote);
        btn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Application.Current().getUserToken().equals("")) {
            startActivity(
                    new Intent(this, UserLogin.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            );
        }
    }

    @Override
    public void onClick(View v) {
        Map<String, String> body = new HashMap<>();
        if (v.getId() == R.id.btnNote) {
            body.put("token", Application.Current().getUserToken());

            new RequestHelper(this,
                    "http://rubiconepro.fvds.ru/web/api/RNote.php",
                    body,
                    NoteBook.instance,
                    new Intent(this, Part.class)
                    ).exec();
        }
    }
}

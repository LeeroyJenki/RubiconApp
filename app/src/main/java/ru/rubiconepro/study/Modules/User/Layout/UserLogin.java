package ru.rubiconepro.study.Modules.User.Layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;
import ru.rubiconepro.study.Lib.NetHTTP.Interface.IRequester;
import ru.rubiconepro.study.Lib.NetHTTP.Model.ResponceModel;
import ru.rubiconepro.study.Lib.NetHTTP.Requester;
import ru.rubiconepro.study.Modules.Base.Helper.RequestHelper;
import ru.rubiconepro.study.Modules.Base.Layout.BaseLayout;
import ru.rubiconepro.study.Modules.NoteBook.Layout.Part;
import ru.rubiconepro.study.Modules.NoteBook.NoteBook;
import ru.rubiconepro.study.Modules.User.Helper;
import ru.rubiconepro.study.R;

/**
 * TODO Сделать авторизацию пользователя
 *
 * У пользователя в качестве логина должен быть E-Mail
 * В качестве пароля цифробуквенный пароль минимум из 6 символов
 * Качество пароля НЕПРОВЕРЯЕМ
 *
 * Модель логина
 *
 * 1. E-Mail
 * 2. Пароль
 *
 * Кнопка логина
 *
 * Если пользователь указал верный логин но неверный пароль
 * то мы выбрасываем сообщение и даем пользователю еще 5 попыток
 * после которых блокируем ввод пароля на 1 минуту,
 * сообщаем об этом пользователю и перекидываем на начальный экран
 * с новостями
 *
 * Если пользователь указал неверный логин то показываем
 * кнопку регистрации нового пользователя
 */

public class UserLogin extends BaseLayout implements View.OnClickListener, IRequester {


    Map<String, String> mp;
    String token = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        mp = new HashMap<>();
        mp.put("admin@mail.com", "12345");

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button2){
            Intent i = new Intent(this, UserRegister.class);
            startActivity(i);
            return;
        }

        EditText LoginString = findViewById(R.id.editText2);
        EditText PasswordString = findViewById(R.id.editText);

        String email = LoginString.getText().toString();
        String password = PasswordString.getText().toString();

        new Requester(this).execute(
                new Request.Builder()
                .url("http://rubiconepro.fvds.ru/web/api/RUser.php")
                .post(
                    new FormBody.Builder()
                        .add("method", "auth")
                        .add("login", email)
                        .add("pass", password)
                        .build()
                ).build()
        );

//        Boolean eml;
//
//        String email1 = "admin@mail.com";
//        String password1 = "12345";
//
//
//
//        eml = Helper.isEmailValid(email);
//
//       // String f =  mp.get("admin@mail.com");
//        if((eml && email.equals(email1)) && (password.equals(password1))){
//            Toast.makeText(this, "YOU ARE IN :D", Toast.LENGTH_SHORT).show();
//        } else{
//            Toast.makeText(this, "INCORRECT PASSWORD OR EMAIL", Toast.LENGTH_SHORT).show();
//        }
    }

        public  void  startNote() {
            String str = "http://rubiconepro.fvds.ru/web/api/RNote.php";
            new RequestHelper(this, str, NoteBook.instance,
                    new Intent(this, Part.class))
                    .exec();
        }
    @Override
    public void RequestDone(ResponceModel model) {
        JSONObject obj = null;
        try {
            obj = model.asJSONObject();
        } catch (Exception ex) {
            ex.printStackTrace();

            Toast.makeText(this, "Ошибка данных", Toast.LENGTH_SHORT).show();
            return;
        }

        int errorID = 0;
        String errorDesc = "";
        String data = "";

        try {
            errorID = obj.getInt("ErrorID");
            errorDesc = obj.getString("ErrorDescription");
            data = obj.getString("Data");
            token = data;
        } catch (Exception ex) {
            ex.printStackTrace();

            Toast.makeText(this, "Ошибка данных", Toast.LENGTH_SHORT).show();
            return;
        }

        if (errorID != 0) {
            Toast.makeText(this, errorDesc, Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();


        startNote();
    }

    @Override
    public void AllDone() {

    }
}

package ru.rubiconepro.study.Modules.User.Layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import ru.rubiconepro.study.Modules.Base.Layout.BaseLayout;
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

public class UserLogin extends BaseLayout implements View.OnClickListener {


    Map<String, String> mp;


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
        }

        Boolean eml;

        String email1 = "admin@mail.com";
        String password1 = "12345";

        EditText LoginString = findViewById(R.id.editText2);
        EditText PasswordString = findViewById(R.id.editText);

        String email = LoginString.getText().toString();
        String password = PasswordString.getText().toString();

        eml = Helper.isEmailValid(email);

       // String f =  mp.get("admin@mail.com");
        if((eml && email.equals(email1)) && (password.equals(password1))){
            Toast.makeText(this, "YOU ARE IN :D", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "INCORRECT PASSWORD OR EMAIL", Toast.LENGTH_SHORT).show();
        }
    }
}

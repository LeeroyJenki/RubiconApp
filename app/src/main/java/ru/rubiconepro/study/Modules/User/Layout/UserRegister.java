package ru.rubiconepro.study.Modules.User.Layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ru.rubiconepro.study.Modules.Base.Layout.BaseLayout;
import ru.rubiconepro.study.Modules.User.Helper;
import ru.rubiconepro.study.R;

/**
 * TODO Сделать регистрацию нового пользователя
 *
 * Модель регистрации
 *
 * 1 - Имя пользователя (НЕОБЯЗАТЕЛЬНО)
 * 2 - Фамилия пользователя (НЕОБЯЗАТЕЛЬНО)
 * 3 - Отчество пользователя (НЕОБЯЗАТЕЛЬНО)
 * 4 - Имя пользователя (Никнейм)
 *
 *      Заполняется автоматически сл.Образом
 *      Первая буква имени точка фамилия
 *      Заполняется сразу как только пользователь
 *      вводит предыдущие поля
 *
 *      Пользователь может изменить никнейм
 *
 *      Если пользователь не менял никнейм и при этом изменил
 *      Имя или фамилию никнейм меняется автоматически
 *
 *      Если поменял, то не меняется
 *
 * 5 - E-Mail (Уникальный)
 * 6 - Номер телефона (НЕОБЯЗАТЕЛЬНО)
 * 7 - Пароль
 * 8 - Проверка пароля
 *
 * 8 - Кнопка зарегистрироваться
 *
 * Проверка на заполненность полей
 * Проверка на валидность полей
 * Проверка E-Mail на уникальность
 */

public class UserRegister extends BaseLayout implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        findViewById(R.id.buttonReg).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Boolean eml;

        EditText NickName_String = findViewById(R.id.editTextNickName);
        EditText NameString = findViewById(R.id.editTextName);
        EditText SecondNameString = findViewById(R.id.editTextSecondName);
        EditText ThirdNameString = findViewById(R.id.editTextThirdName);
        EditText EmailString = findViewById(R.id.editTextEMail);
        EditText PhoneNumberString = findViewById(R.id.editTextPhone);
        EditText Password1String = findViewById(R.id.editTextPassword);
        EditText PasswordConfString = findViewById(R.id.editTextPasswordConf);

        String email = EmailString.getText().toString();
        eml = Helper.isEmailValid(email);

        String NickName = NickName_String.getText().toString();
        String Pass1 = Password1String.getText().toString();
        String PassConf = PasswordConfString.getText().toString();

        if(NickName.equals("") ||
                email.equals("")||
                Pass1.equals("")||
                PassConf.equals(""))
        {
            Toast.makeText(this, "Все поля помеченные * обязательны для заполнения", Toast.LENGTH_SHORT).show();
        }

        if(!eml){
            Toast.makeText(this, "Ваша электронная почта не соответсвует формату", Toast.LENGTH_SHORT).show();
        }

        if(!Pass1.equals(PassConf)){
            Toast.makeText(this, "Введённые Вами пароли не совпадают", Toast.LENGTH_SHORT).show();
        }

        if((eml) && (Pass1.equals(PassConf))){
            Toast.makeText(this, "Молодец", Toast.LENGTH_SHORT).show();
        }
    }
}

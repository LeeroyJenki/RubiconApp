package ru.rubiconepro.study.Modules.User.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.rubiconepro.study.Modules.Base.Exception.ModelDataException;
import ru.rubiconepro.study.Modules.Base.Model.BaseModel;
import ru.rubiconepro.study.Modules.User.Helper;

public class UserModel extends BaseModel {
    private String nickName;   //Никнейм
    private String firstName;  //Имя
    private String lastName;   //Фамилия
    private String middleName; //Отчество
    private String email;      //Email
    private String phone;      //Номер телефона
    private String password;   //Пароль пользователя

    /*******************************************************
     //Создание Сеторов
     *******************************************************/

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setEmail(String email) throws ModelDataException {
        if (!Helper.isEmailValid(email)) {
            throw new ModelDataException("Ошибка проверки EMAIL поля");
        }
        this.email = email;
    }

    public void setPhone(String phone) throws ModelDataException {
        if (!Helper.isPhoneValid(phone)) {
            throw new ModelDataException("Ошибка проверки PHONE поля");
        }
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*******************************************************
    //Создание Гетеров
    *******************************************************/

    public String getNickName() {
        return nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getIDName() {
        return null;
    }
}

package ru.rubiconepro.study.Modules.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    public static boolean isPhoneValid(String phone)
    {
        String regExpn =
                "^([+]{0,1}7|8)9[0-9]{9}$";
                /*
                * 1 - ([+]{0,1}7|8) - У нас может встречаться либо 7 либо +7 либо 8
                *   - | - это символ логичесское ИЛИ следовательно у нас может быть
                *       либо  [+]{0,1}7  либо  8
                *   - [+] это просто поиск символа +
                *   - [+]{0,1} - в фигурных скобках указано сколько раз может повторяться + (0 или 1 раз)
                *   - 7 просто цифра 7
                *   - 8 просто цифра 8
                *
                *   - 9 просто цифра 9
                *   - [0-9] тут мы ищем один из сл. символов 0 1 2 3 4 5 6 7 8 9
                *   - [0-9]{9} модификатор числа повторений
                *
                *   - ^ признак начала строки
                *   - $ признак конца строки
                * */

        CharSequence inputStr = phone;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

}

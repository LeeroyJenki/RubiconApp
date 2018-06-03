package ru.rubiconepro.study.Lib.NetConnector.Model;

import android.util.Xml;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Request {
    //Базовый URL по которому мы отправляем запрос
    String url;
    //Параметры строки запроса (Ключ - значение)
    Map<String, String> queryString;
    //Тип запроса
    RequestType rType;

    //Данные которые мы отправляем в теле запроса
    //Они могут быть одними из

    //Бинарные данные
    byte[] bBody;
    //Данные формы
    Map<String, String> fBody;
    //Данные JSON
    JSONObject jBody;
    //Данные XML
//    Xml xBody;

    //Заголовки запроса
    Map<String, String> headers;
    Map<String, String> cookies;

    //Конструктор по умолчанию
    public Request() {
        init();
    }

    //Конструктор для GET запроса без параметров
    public Request(String url) {
        init();

        this.url = url;
    }

    //Функция инициализации
    private void init() {
        url = "";
        rType = RequestType.GET;
        queryString = new HashMap<>();
        //TODO инициализировать все поля
    }

    public URL getURL() throws MalformedURLException {
        //TODO Проверка на стартовую строку HTTP

        String data = this.url;
        //TODO Сделать проверку на параметры строки (?q=test)

        //TODO Сделать проверку на первый параметр
        /**
         * http://test.ru?a=b&q=7
        */
        for (String key: queryString.keySet()) {
            String value = queryString.get(key);
            data += "?" + key + "=" + value;
        }

        return new URL(data);
    }


    public Map<String, String> getHeaders() {
        Map<String, String> data = new HashMap<>();

        //TODO проверить все headers
        //TODO Добавить все headers в data

        //TODO проверить все cookies
        //TODO Добавить все cookies в data в формате
        //Cookies: key=value;key=value;

        return data;
    }

    public byte[] getBody() {

        //TODO Проверить что конвертировать
        //TODO Сконверитировать нужный параметр в массив байт


        //jBody.toString().getBytes(); //Прелбразование JSON

        /* fBody
        *
        * String str;
        *
        * Пробежаться по fBody
        *
        * создать сл. строку  key=value&key=value
        *
        * str.getBytes();
        * */

        return new byte[0];
    }
}


/**
    http://google.ru/path/to/file/file.php?q=test&region=russia&city=tula

    METHOD = GET
    Cache-Control: no-cache
    Content-Type: text/form-www-url-encoded
    Contetn-Size: 500
    Cookie: test=4;qqq=10

    test=4&qqq=9&qwerty=123qwerty

    ||

    Content-Type: text/json
    {test:4, qqq:9, qwerty:"123qwerty"}

     ||

     Content-Type: text/xml
     <!xml>
     <root>
        <test>4</test>
        <qqq>9</qqq>
        <qwerty>123qwerty</qwerty>
     </root>

 */
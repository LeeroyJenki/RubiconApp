package ru.rubiconepro.study.Lib.NetConnector;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import ru.rubiconepro.study.Lib.NetConnector.Model.Progress;
import ru.rubiconepro.study.Lib.NetConnector.Model.Request;

public class Connector extends AsyncTask<Request, Progress, byte[]> {

    @Override
    protected byte[] doInBackground(Request... urls) {

        for (Request r : urls) {

//            URL u = r.getURL();
//            URLConnection connection = u.openConnection();
//
//            Map<String, String> headers = r.getHeaders();
//            for (String key : headers) {
//                String value = headers.get(key);
//
//                connection.addRequestProperty(key, value);
//            }
//
//            connection.connect();
//
//            OutputStream os = connection.getOutputStream();
//            os.write(r.getBody());
//            os.flush();
//
//            InputStream is = connection.getInputStream();
//
//            int total = connection.getContentLength();
//
//            int totalReaded = 0;
//            byte[] buffer = new byte[1024];
//
//            while (true) {
//                int readed = is.read(buffer)
//                //TODO PROGRESS
//
//                totalReaded += readed;
//            }
//
//            //TODO Получить данные и вернуть
//
//            //TODO Мне не подходит простой URL, нужно принемать класс с параметрами
//            //Для строки запроса а так же тела запроса

        }

        return new byte[0];
    }

    @Override
    protected void onProgressUpdate(Progress... values) {
        super.onProgressUpdate(values);
    }
}

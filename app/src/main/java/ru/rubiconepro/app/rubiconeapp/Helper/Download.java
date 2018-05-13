package ru.rubiconepro.app.rubiconeapp.Helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Download {
    public byte[] startDownload(String str_url) {
        URL url;

        try {
            url = new URL(str_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        HttpURLConnection connection;

        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        InputStream stream;

        try {
            stream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        while (true) {
            int readed = 0;
            try {
                readed = stream.read(buff);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            if (readed == -1)
                break;

            output.write(buff,0 ,readed);
        }

        //Это комментарий

        return output.toByteArray();
    }
}

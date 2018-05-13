package ru.rubiconepro.app.rubiconeapp.Tasks;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DownloadTask extends AsyncTask<String, Void, byte[]> {

    ImageView imageview;

    public DownloadTask(ImageView imageview) {
        this.imageview = imageview;
    }

    @Override
    protected byte[] doInBackground(String... params) {

        URL url;

        try {
            url = new URL(params[0]);
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

            publishProgress();

            if (readed == -1)
                break;

            output.write(buff,0 ,readed);
        }


        return output.toByteArray();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        imageview.setImageBitmap(bmp);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

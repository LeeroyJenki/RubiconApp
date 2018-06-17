package ru.rubiconepro.study.Modules.Test.Helper;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Requester extends AsyncTask<String, String, String> {
    public final Context r;

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(r, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... strings) {
        String d = "";
        try {
            OkHttpClient client = new OkHttpClient();

            Request req = new Request.Builder()
                    .url("http://rubiconepro.fvds.ru/web/api/RStatic.php")
                    .post(new FormBody.Builder()
                            .add("method", "add")
                            .add("name", "name")
                            .add("text", "kjasdfbadsfkljbadskbfadkjsbfbdfs")
                            .add("shorttext", "asdfasdfadsfadsfasdfasdfasdf")
                            .build()
                    ).build();

            Response r = client.newCall(req).execute();
            d = r.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return d;
    }

    public Requester(Context r) {
        this.r = r;


//        new tt().addA(1).addB(5);
    }

//    public class  tt {
//        int a;
//        int b;
//        int c;
//
//        public tt() {
//
//        }
//
//        public tt addA(int a) {
//            this.a = a;
//            return this;
//        }
//
//        public tt addB(int b) {
//            this.b = b;
//            return this;
//        }
//    }
}


//public class Requester extends AsyncTask<String, String, String> {
//
//    Context context;
//
//    Requester(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    protected String doInBackground(String... voids) {
//        String data = "";
//
//        try {
//            URL u = new URL("http://rubiconepro.fvds.ru/web/api/RStatic.php?id=113");
//
//            URLConnection connection = u.openConnection();
//            connection.connect();
//
//            String d = "method=add&name=name&text=text&shorttext=shorttext";
//            OutputStream ostream = connection.getOutputStream();
//            ostream.write(d.getBytes());
//
//            int l = connection.getContentLength();
//            byte[] body = new byte[l];
//            int readed = 0;
//
//            InputStream s = connection.getInputStream();
//            while (true) {
//                int len = l - readed < 1024 ? l - readed : 1024;
//                int r = s.read(body, readed, len);
//                readed += r;
//
//                if (r == -1)
//                    break;
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return data;
//    }
//
//    @Override
//    protected void onPostExecute(String s) {
//
//        super.onPostExecute(s);
//    }
//}

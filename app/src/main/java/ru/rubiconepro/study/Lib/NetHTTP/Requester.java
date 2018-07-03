package ru.rubiconepro.study.Lib.NetHTTP;

import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.rubiconepro.study.Lib.NetHTTP.Interface.IRequester;
import ru.rubiconepro.study.Lib.NetHTTP.Model.ResponceModel;

public class Requester extends AsyncTask<Request, ResponceModel, Void> {

    final OkHttpClient client;
    final IRequester delegate;

    public Requester(IRequester delegate) {
        this.client = new OkHttpClient();
        this.delegate = delegate;
    }

    @Override
    protected void onProgressUpdate(ResponceModel... values) {
        for (ResponceModel m: values) {
            delegate.RequestDone(m);
        }
    }

    //Выполнение в другом потоке
    @Override
    protected Void doInBackground(Request... requests) {
        for (int i = 0; i < requests.length; i++) {
            Request r = requests[i];
            try {
                try (Response resp = client.newCall(r).execute()) {
                    byte[] body = null;
                    body = resp.body().bytes();
                    //НЕВЕРНО (будет выполнено во второй нитке)
                    //onProgressUpdate(new ResponceModel(i, r, resp, body));
                    //ВЕРНО
                    publishProgress(new ResponceModel(i, r, resp, body));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                publishProgress(new ResponceModel(i, r));
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        delegate.AllDone();
    }


}

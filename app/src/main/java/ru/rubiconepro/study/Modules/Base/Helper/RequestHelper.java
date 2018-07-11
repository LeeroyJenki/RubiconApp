package ru.rubiconepro.study.Modules.Base.Helper;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;
import ru.rubiconepro.study.Modules.Base.Base;
import ru.rubiconepro.study.Modules.Base.Dialog.PreloadDialog;
import ru.rubiconepro.study.Modules.Base.Interface.IDone;
import ru.rubiconepro.study.Modules.PDFView.Layout.PDFViewLayout;
import ru.rubiconepro.study.Modules.PDFView.PDFView;

//Помошник по загрузке
//1 - Составляет запрос
//1.1 - запускает прилоадер
//2 - Получает ответ
//2.1 - завершает прилоадер
//3 - в случае успеха запускает view
public class RequestHelper implements IDone {
    Context context;
    String url;
    Base element;
    Intent nextView;
    Map<String, String> params;

    PreloadDialog dialog;

    public RequestHelper(Context context, String url, Base element, Intent nextView) {
        this.context = context;
        this.url = url;
        this.element = element;
        this.nextView = nextView;
        this.params = null;

        dialog = new PreloadDialog(context);
    }

    public RequestHelper(Context context, String url, Map<String, String> params, Base element, Intent nextView) {
        this.context = context;
        this.url = url;
        this.element = element;
        this.nextView = nextView;
        this.params = params;


        dialog = new PreloadDialog(context);
    }

    public void exec () {
        Request r = null;
        try {
            Request.Builder b = new Request.Builder().url(url);

            if (params != null) {
                FormBody.Builder br = new FormBody.Builder();

                for (String k: params.keySet()) {
                    br.add(k, params.get(k));
                }

                b.post(br.build());
            }

            r = b.build();

        } catch (Exception ex) {
            ex.printStackTrace();
            r = null;
        }
        if (r == null) {
            Toast.makeText(context, "Запрос неверен!", Toast.LENGTH_SHORT).show();
            return;
        }

        element.loadData(r,this);
        dialog.show();
    }

    @Override
    public void JobDone(boolean success) {
        dialog.close();
        if (!success) {
            Toast.makeText(context, "Что то пошло не так!", Toast.LENGTH_SHORT).show();
            return;
        }

        context.startActivity(nextView);
    }
}

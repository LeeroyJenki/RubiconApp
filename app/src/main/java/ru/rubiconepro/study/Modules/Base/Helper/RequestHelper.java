package ru.rubiconepro.study.Modules.Base.Helper;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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

    PreloadDialog dialog;

    public RequestHelper(Context context, String url, Base element, Intent nextView) {
        this.context = context;
        this.url = url;
        this.element = element;
        this.nextView = nextView;

        dialog = new PreloadDialog(context);
    }

    public void exec () {
        Request r = null;
        try {
            r = new Request.Builder().url(url).build();
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

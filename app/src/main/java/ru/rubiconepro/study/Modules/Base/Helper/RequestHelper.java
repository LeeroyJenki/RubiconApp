package ru.rubiconepro.study.Modules.Base.Helper;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import okhttp3.Request;
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
    PreloadDialog pd;
    public RequestHelper(Context context, String url, Intent nextView) {
        this.context = context;

        Request r = null;
        try {
            r = new Request.Builder().url(url).build();
            pd = new PreloadDialog(context);
            pd.show();

        } catch (Exception ex) {
            ex.printStackTrace();
            r = null;
        }
        if (r == null) {
            Toast.makeText(context, "Запрос неверен!", Toast.LENGTH_SHORT).show();
            return;
        }
        PDFView.Current().loadData(r,
                this);
    }

    @Override
    public void JobDone(boolean success) {
 //       new PreloadDialog(context);
        pd.close();
            if (!success) {
            Toast.makeText(context, "Что то пошло не так!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent i = new Intent(context, PDFViewLayout.class);

        context.startActivity(i);

    }

//    @Override
//    public void JobDone(boolean success) {
//        if (!success) {
//            Toast.makeText(context, "Что то пошло не так!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        Intent i = new Intent(context, nextView);
//        startActivity(i);
//    }
}


package ru.rubiconepro.study.Modules.Test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.github.barteksc.pdfviewer.PDFView;

import org.json.JSONObject;

import java.io.Console;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import ru.rubiconepro.study.Application;
import ru.rubiconepro.study.Lib.NetHTTP.Interface.IRequester;
import ru.rubiconepro.study.Lib.NetHTTP.Model.ResponceModel;
import ru.rubiconepro.study.Lib.NetHTTP.Requester;
import ru.rubiconepro.study.Modules.Base.Layout.BaseLayout;
import ru.rubiconepro.study.R;

public class Test extends BaseLayout implements IRequester {

    @Override
    public void RequestDone(ResponceModel model) {
//        Context c = null;

        byte[] bytes = model.responceBody;

        //Отображение картинки
        ImageView iv = new ImageView(null);
        iv.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));

        //Отображение PDF
        PDFView pv = new PDFView(Application.Current(), null);
        pv.fromBytes(bytes).load();

        //Обычная строка
        String s = new String(bytes, StandardCharsets.UTF_8);//Преобразуем набор байт в строку

        TextView tv = new TextView(Application.Current());
        tv.setText(s);//Отображаем


        //JSON объект
        String str = new String(bytes, StandardCharsets.UTF_8);//Преобразуем набор байт в строку
        try {
            JSONObject obj = new JSONObject(str);
            if (obj.has("name"))
                Log.d("JSON", obj.getString("name"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void AllDone() {

    }

    public interface TestInterface {
        void a();
        void b();
    }

    public class FileWrite implements  TestInterface {
        @Override
        public void a() {}
        @Override
        public void b() {}
    }

    public class NetworkWrite implements TestInterface {
        @Override
        public void a() {}
        @Override
        public void b() {}
    }

    public class PipeWrite implements TestInterface {
        @Override
        public void a() {}
        @Override
        public void b() {}
    }

    void write (TestInterface writer) {
        writer.a();
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        write(new NetworkWrite());
        write(new FileWrite());

        Request r =
                new Request.Builder() //Создаем билдер реквеста
                .url("http://rubiconepro.fvds.ru/web/api/RStatic.php") //Говорим куда отправить данные
                .post( //Говорим что мы отправляем тело запроса (POST данных)
                        new FormBody.Builder() //Создаем построитеть тела запроса
                            .add("method", "add") //Добавляем несколько полей в форму
                            .add("name", "testtest")
                            .add("text", "texttrxttrxt")
                            .build() //Создаем форму и передаем её экземпляр в метод post()
                ).build(); //Наш запрос готов

        new Requester(this).execute(r);

    }
}

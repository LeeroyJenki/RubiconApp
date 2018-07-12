package ru.rubiconepro.study.Modules.NoteBook.Layout;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.Request;
import ru.rubiconepro.study.Application;
import ru.rubiconepro.study.Lib.NetHTTP.Interface.IRequester;
import ru.rubiconepro.study.Lib.NetHTTP.Model.ResponceModel;
import ru.rubiconepro.study.Lib.NetHTTP.Requester;
import ru.rubiconepro.study.Modules.Base.Helper.RequestHelper;
import ru.rubiconepro.study.Modules.NoteBook.Adapter.IAdapter;
import ru.rubiconepro.study.Modules.NoteBook.Adapter.PartAdapter;
import ru.rubiconepro.study.Modules.NoteBook.Const.IntentConst;
import ru.rubiconepro.study.Modules.NoteBook.NoteBook;

/**
 * Класс - Лайаут для отображения категорий записной книги
 */
public class Part extends NoteBase implements IRequester {

    protected IAdapter createAdapter() {
        return new PartAdapter(this);
    }


    String text = "";
    protected void createElement(String text) {
        this.text = text;

        new Requester(this).execute(
            new Request.Builder()
                .url("http://rubiconepro.fvds.ru/web/api/RNote.php")
                .post(
                        new FormBody.Builder()
                        .add("token", Application.Current().getUserToken())
                        .add("method", "AddCat")
                        .add("name", text)
                        .build()
                )
                .build()
        );
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, Note.class);
        i.putExtra(IntentConst.positionPart, position);
        i.putExtra("positionPart", position);
        startActivity(i);
    }

    protected String getHeaderTitle() {
        return "Категории";
    }
    protected String getButtonTitle() {
        return "Создать категорию";
    }

    @Override
    public void RequestDone(ResponceModel model) {
        //TODO ПЕРЕНЕСТИ В ХЕЛПЕР
        //TODO Так же из USER (3 места)
        JSONObject obj = null;
        try {
            obj = model.asJSONObject();
        } catch (Exception ex) {
            ex.printStackTrace();

            Toast.makeText(this, "Ошибка данных", Toast.LENGTH_SHORT).show();
            return;
        }

        int errorID = 0;
        String errorDesc = "";
        String data = "";

        try {
            errorID = obj.getInt("ErrorID");
            errorDesc = obj.getString("ErrorDescription");
            data = obj.getString("Data");
        } catch (Exception ex) {
            ex.printStackTrace();

            Toast.makeText(this, "Ошибка данных", Toast.LENGTH_SHORT).show();
            return;
        }

        if (errorID != 0) {
            Toast.makeText(this, errorDesc, Toast.LENGTH_SHORT).show();
            return;
        }


        NoteBook.instance.addPart(text, Integer.decode(data));
        adapter.reloadData();
    }

    @Override
    public void AllDone() {

    }
}

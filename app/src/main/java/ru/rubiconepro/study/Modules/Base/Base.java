package ru.rubiconepro.study.Modules.Base;

import java.io.IOException;

import okhttp3.Request;
import ru.rubiconepro.study.Lib.NetHTTP.Interface.IRequester;
import ru.rubiconepro.study.Lib.NetHTTP.Model.ResponceModel;
import ru.rubiconepro.study.Lib.NetHTTP.Requester;
import ru.rubiconepro.study.Modules.Base.Interface.IDone;

//Базовый класс для всех синглтонов модулей.
//Реализует общий базовыйфункционал
//1 - Загрузка моделей данных
public abstract class Base implements IRequester {




    /*****************************************************/
    /****Функционал загрузки данных****/

    protected abstract void storeResponce(byte[] data) throws Exception;

    //Делегат при помощи которого мы уведомляем удаленный класс о готовности данных
    private IDone doneDelegate;

    //Загрузка данных
    public void loadData(Request request, IDone delegate) {
        doneDelegate = delegate;
        Requester r = new Requester(this);
        r.execute(request);
    }

    //IREQUESTER DELEGATES
    @Override
    public void RequestDone(ResponceModel model) {
        //Проверяем на нормальный ответ
        //Если в ответе код не равен 200 то в запросе произошла ошибка
        //см. https://developer.mozilla.org/ru/docs/Web/HTTP/Status
        if (model.responce == null || model.responce.code() != 200) {
            doneDelegate.JobDone(false);
            return;
        }

        try {
            storeResponce(model.responceBody);
        } catch (Exception ex) {
            ex.printStackTrace();

            doneDelegate.JobDone(false);
            return;
        }

        doneDelegate.JobDone(true);
    }

    @Override
    public void AllDone() { }
}

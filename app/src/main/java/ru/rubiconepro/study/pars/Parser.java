package ru.rubiconepro.study.pars;

import android.nfc.Tag;
import android.util.Log;

import okhttp3.Request;
import ru.rubiconepro.study.Lib.NetHTTP.Interface.IRequester;
import ru.rubiconepro.study.Lib.NetHTTP.Model.ResponceModel;
import ru.rubiconepro.study.Lib.NetHTTP.Requester;

public class Parser implements IRequester {
  public void Request(){
        Requester requester = new Requester(this);
        Request.Builder builder = new Request.Builder();
       builder.url("http://rubiconepro.fvds.ru/web/api/RTests.php");
        requester.execute(builder.build());

    }

    @Override
    public void RequestDone(ResponceModel model) {
      model.asString();
    }

    @Override
    public void AllDone() {

    }
}

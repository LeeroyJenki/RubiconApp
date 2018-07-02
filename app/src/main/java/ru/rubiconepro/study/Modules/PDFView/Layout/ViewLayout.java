package ru.rubiconepro.study.Modules.PDFView.Layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.security.cert.Extension;

import okhttp3.Request;
import ru.rubiconepro.study.Lib.NetHTTP.Interface.IRequester;
import ru.rubiconepro.study.Lib.NetHTTP.Model.ResponceModel;
import ru.rubiconepro.study.Lib.NetHTTP.Requester;
import ru.rubiconepro.study.R;

public class ViewLayout extends AppCompatActivity implements IRequester {

    PDFView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_layout);

        view = findViewById(R.id.pdfView);


        Requester r = new Requester(this);
        r.execute(new Request.Builder()
                .url("http://www.pdf995.com/samples/pdf.pdf")
                .build()
        );

        //view.fromUri(Uri.parse("http://www.pdf995.com/samples/pdf.pdf")).load();
    }

    @Override
    public void RequestDone(ResponceModel model) {
        view.fromBytes(model.responceBody).load();
    }

    @Override
    public void AllDone() {

    }
}

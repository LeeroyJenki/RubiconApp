package ru.rubiconepro.study.Modules.Base.Dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;

public class PreloadDialog {

    AlertDialog ad;
    AlertDialog.Builder builder;
    ProgressBar progrBar;
    Context context;


    public  PreloadDialog(Context context){
        this.context = context;
//        ad = new AlertDialog();
//        builder = ad.Builder(context);
        builder = new AlertDialog.Builder(context);

        builder.setTitle("Идет загрузка");

        progrBar = new ProgressBar(context);
        builder.setView(new ProgressBar(context));

        ad = builder.create();

    }
    public void show() {

        ad.show();
    }

    public void close() {

        ad.dismiss();
    }
}

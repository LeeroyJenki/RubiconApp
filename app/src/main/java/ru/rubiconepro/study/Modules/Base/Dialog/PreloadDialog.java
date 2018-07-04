package ru.rubiconepro.study.Modules.Base.Dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;

public class PreloadDialog {

    AlertDialog.Builder builder;
    ProgressBar progrBar;


    public  PreloadDialog(Context context){
        builder = new AlertDialog.Builder(context);

        progrBar = new ProgressBar(context);


    }
    public void show() {
        builder.show();
    }

}

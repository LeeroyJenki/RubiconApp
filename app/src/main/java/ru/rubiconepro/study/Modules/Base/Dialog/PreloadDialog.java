package ru.rubiconepro.study.Modules.Base.Dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class PreloadDialog {

    AlertDialog dialog;
    Context context;

    public PreloadDialog (Context context) {
        this.context = context;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Идет выполнение операции");
        ProgressBar pb = new ProgressBar(context);
        builder.setView(pb);
        builder.setCancelable(false);

        dialog = builder.create();
    }

    public void show() {
        dialog.show();
    }

    public void close () {
        dialog.dismiss();
    }
}

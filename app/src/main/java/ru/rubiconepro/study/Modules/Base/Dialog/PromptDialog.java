package ru.rubiconepro.study.Modules.Base.Dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.widget.EditText;

import ru.rubiconepro.study.Modules.Base.Interface.IPromptDialog;

public class PromptDialog {
    AlertDialog.Builder builder;
    EditText input;

    public PromptDialog(Context context, String title, final IPromptDialog delegate) {
        builder = new AlertDialog.Builder(context);
        builder.setTitle(title);

        input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delegate.dialogDone(true, input.getText().toString());
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delegate.dialogDone(false, input.getText().toString());
                dialog.cancel();
            }
        });
    }

    public PromptDialog setText(String text) {
        input.setText(text);
        return this;
    }

    public void show() {
        builder.show();
    }
}

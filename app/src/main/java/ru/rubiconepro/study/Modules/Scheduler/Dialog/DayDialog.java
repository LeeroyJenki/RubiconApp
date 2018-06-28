package ru.rubiconepro.study.Modules.Scheduler.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.text.DateFormat;

import ru.rubiconepro.study.Modules.Scheduler.Adapter.DayDialogAdapter;
import ru.rubiconepro.study.Modules.Scheduler.Model.DayModel;
import ru.rubiconepro.study.Modules.Scheduler.Scheduler;
import ru.rubiconepro.study.R;

//Диалог для отрисовки
public class DayDialog {
    //Диалог который мы будем отрисовывать
    AlertDialog dialog;

    //Вьюшка для диалога
    View dialogView;

    //ListView для отображения наших данных
    ListView mainView;

    //Адаптер для отображения содержимого
    DayDialogAdapter adapter;

    //Контекст выполнения
    private Context context;


    public DayDialog (Context context) {
        this.context = context;

        LayoutInflater inflatter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflatter.inflate(R.layout.day_dialog_layout, null, false);

        dialog = new AlertDialog.Builder(context)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setView(dialogView)
                .create();

        //Создание адаптера, и определение его для вьюшки
        adapter = new DayDialogAdapter(context);
        mainView = dialogView.findViewById(R.id.lst_main);

        mainView.setAdapter(adapter);

    }

    public void show(int index) {
        DayModel m = Scheduler.Current().getModels().get(index);

        DateFormat formatter = DateFormat.getDateInstance(
                java.text.DateFormat.LONG);
        formatter.setTimeZone(m.getEventDate().getTimeZone());

        dialog.setTitle("События " + formatter.format(m.getEventDate().getTime()));
        adapter.setModel(index);
        dialog.show();
    }
}

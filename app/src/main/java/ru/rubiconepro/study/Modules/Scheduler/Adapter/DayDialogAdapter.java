package ru.rubiconepro.study.Modules.Scheduler.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.rubiconepro.study.Modules.Scheduler.Model.DayModel;
import ru.rubiconepro.study.Modules.Scheduler.Model.EventModel;
import ru.rubiconepro.study.Modules.Scheduler.Scheduler;
import ru.rubiconepro.study.R;

public class DayDialogAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflatter;

    List<EventModel> items;

    public DayDialogAdapter(Context context) {
        this.context = context;
        inflatter = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        items = new ArrayList<>();
    }


    public void setModel(int index) {
        items.clear();

        DayModel day = Scheduler.Current().getModels().get(index);
        for (EventModel event:
                day.getEvents()) {
            items.add(event);
        }

        notifyDataSetInvalidated();
    }

    /***************************************************/
    //Делегаты Адаптера
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflatter.inflate(R.layout.day_dialog_item, parent, false);
        }

        EventModel model = items.get(position);

        TextView tv = convertView.findViewById(R.id.tv_title);
        tv.setText(model.getName());

        return convertView;
    }
}

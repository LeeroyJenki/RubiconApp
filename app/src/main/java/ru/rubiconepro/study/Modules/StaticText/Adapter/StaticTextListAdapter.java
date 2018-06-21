package ru.rubiconepro.study.Modules.StaticText.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ru.rubiconepro.study.Modules.StaticText.Model.StaticTextModel;
import ru.rubiconepro.study.Modules.StaticText.StaticText;
import ru.rubiconepro.study.R;

import java.util.List;

public class StaticTextListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<StaticTextModel> models;

    public StaticTextListAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        models = StaticText.Current().getList();
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int i) {
        return models.get(i);
    }

    @Override
    public long getItemId(int i) {
        return models.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.statictext_list_item, viewGroup, false);
        }

        TextView v = view.findViewById(R.id.textView);
        v.setText(models.get(i).name);

        return view;
    }
}

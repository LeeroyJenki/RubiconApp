package ru.rubiconepro.study.Modules.StaticText.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;



import java.util.List;
import ru.rubiconepro.study.R;


import ru.rubiconepro.study.Modules.Base.Interface.IPromptDialog;

import ru.rubiconepro.study.Modules.StaticText.Model.StaticTextModel;

public class StaticTextListAdapter extends BaseAdapter {


    LayoutInflater inflater;
    Context context;
    List<StaticTextModel> items;

    public StaticTextListAdapter(Context context, List<StaticTextModel> items) {
        this.items = items;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = inflater.inflate(R.layout.activity_static_text_detail, viewGroup, false);
            }

        StaticTextModel a = items.get(i);

        TextView tv = view.findViewById(R.id.textView3);
        tv.setText(a.name);

        return view;
    }
}

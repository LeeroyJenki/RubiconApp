package ru.rubiconepro.study.Modules.NoteBook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import ru.rubiconepro.study.Modules.Base.Base;
import ru.rubiconepro.study.Modules.Base.Dialog.PromptDialog;
import ru.rubiconepro.study.Modules.Base.Interface.IPromptDialog;
import ru.rubiconepro.study.Modules.NoteBook.Layout.Part;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartListModel;
import ru.rubiconepro.study.Modules.NoteBook.NoteBook;
import ru.rubiconepro.study.R;

public class PartAdapter extends IAdapter {

    PartListModel data;

    public PartAdapter(Context context) {
        super(context);
        this.data = NoteBook.instance.getModel();
    }

    @Override
    public int getCount() {
        return data.items.size();
    }

    @Override
    public Object getItem(int position) {
        return data.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        TextView label = v.findViewById(R.id.label);
        label.setText(data.items.get(position).toString());

        return v;
    }

    @Override
    public void onClick(View v) {
        int position = (int)v.getTag();
        if (v.getId() == R.id.btnDelete)
            this.deleteElement(position);
        if (v.getId() == R.id.btnEdit)
            this.editElement(position);

    }

    private void deleteElement(int position) {
        data.items.remove(position);
        notifyDataSetChanged();
    }

    private void editElement(final int position) {
        new PromptDialog(context, "Изменение элемента", new IPromptDialog() {
            @Override
            public void dialogDone(boolean result, String text) {
                if (!result)
                    return;

                data.items.get(position).title = text;
                PartAdapter.this.notifyDataSetChanged();
            }
        }).setText(data.items.get(position).title).show();
    }





}

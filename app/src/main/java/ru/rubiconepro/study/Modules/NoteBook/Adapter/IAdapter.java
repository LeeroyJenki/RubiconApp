package ru.rubiconepro.study.Modules.NoteBook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.security.PublicKey;

import ru.rubiconepro.study.R;

public abstract class IAdapter extends BaseAdapter implements View.OnClickListener {
    boolean isEditable = false;
    LayoutInflater inflater;
    Context context;

    protected IAdapter(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.notebook_part_call, parent, false);
        }

        Button btnDelete = convertView.findViewById(R.id.btnDelete);
        Button btnEdit   = convertView.findViewById(R.id.btnEdit);

        if (!isEditable) {
            btnDelete.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);
        } else {
            btnDelete.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.VISIBLE);
        }

        //Добавляем тагом позицию
        btnDelete.setTag(position);
        btnEdit.setTag(position);

        //Вешаем обработчики
        btnDelete.setOnClickListener(this);
        btnEdit.setOnClickListener(this);

        return convertView;
    }

    public void reloadData() {
        this.notifyDataSetChanged();
    }

}

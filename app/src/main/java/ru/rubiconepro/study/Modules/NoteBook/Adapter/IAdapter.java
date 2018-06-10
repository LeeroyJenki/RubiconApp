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
    boolean btnAddVisible;

    abstract void deleteElement(int position);
    abstract void editElement(final int position);
    protected void addElement (int position) { }

    protected IAdapter(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        btnAddVisible = false;
    }

    public void setBtnAddVisible(boolean btnAddVisible) {
        this.btnAddVisible = btnAddVisible;
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
        Button btnAdd    = convertView.findViewById(R.id.btnAdd);

        if (!isEditable) {
            btnDelete.setVisibility(View.GONE);
            btnEdit.setVisibility(View.GONE);
            btnAdd.setVisibility(View.GONE);
        } else {
            btnDelete.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.VISIBLE);
            if (btnAddVisible)
                btnAdd.setVisibility(View.VISIBLE);
        }

        //Добавляем тагом позицию
        btnDelete.setTag(position);
        btnEdit.setTag(position);
        btnAdd.setTag(position);

        //Вешаем обработчики
        btnDelete.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = (int)v.getTag();
        if (v.getId() == R.id.btnDelete)
            this.deleteElement(position);
        if (v.getId() == R.id.btnEdit)
            this.editElement(position);
        if (v.getId() == R.id.btnAdd)
            this.addElement(position);
    }

    public void reloadData() {
        this.notifyDataSetChanged();
    }
}

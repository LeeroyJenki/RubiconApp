package ru.rubiconepro.study.Modules.NoteBook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ru.rubiconepro.study.Modules.Base.Dialog.PromptDialog;
import ru.rubiconepro.study.Modules.Base.Interface.IPromptDialog;
import ru.rubiconepro.study.Modules.NoteBook.Const.IntentConst;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartListModel;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartModel;
import ru.rubiconepro.study.Modules.NoteBook.NoteBook;
import ru.rubiconepro.study.R;

public class NoteAdapter extends IAdapter  {
    PartModel data;
    int positionPart;

    public NoteAdapter(Context context, int position) {
        super(context);

        this.positionPart = position;
        this.data = NoteBook.instance.getPartByPosition(position);
    }

    @Override
    public void onClick(View v) {
        int position = (int)v.getTag();
        if (v.getId() == R.id.btnDelete)
            this.deleteElement(position);
        if (v.getId() == R.id.btnEdit)
            this.editElement(position);
    }

    @Override
    public int getCount() {
        return data.listNotes.size();
    }

    @Override
    public Object getItem(int position) {
        return data.listNotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        TextView label = v.findViewById(R.id.label);
        label.setText(data.listNotes.get(position).title.toString());

        return v;
    }

    private void deleteElement(int position) {
        data.listNotes.remove(position);
        notifyDataSetChanged();
    }

    private void editElement(final int position) {
        new PromptDialog(context, "Изменение элемента", new IPromptDialog() {
            @Override
            public void dialogDone(boolean result, String text) {
                if (!result)
                    return;

                data.listNotes.get(position).title = text;
                NoteAdapter.this.notifyDataSetChanged();
            }
        }).setText(data.listNotes.get(position).title).show();
    }


}

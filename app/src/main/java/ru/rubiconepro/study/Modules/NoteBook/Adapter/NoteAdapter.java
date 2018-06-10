package ru.rubiconepro.study.Modules.NoteBook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.rubiconepro.study.Modules.Base.Dialog.PromptDialog;
import ru.rubiconepro.study.Modules.Base.Interface.IPromptDialog;
import ru.rubiconepro.study.Modules.NoteBook.Const.IntentConst;
import ru.rubiconepro.study.Modules.NoteBook.Model.NoteWrapper;
import ru.rubiconepro.study.Modules.NoteBook.Model.NotesModel;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartListModel;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartModel;
import ru.rubiconepro.study.Modules.NoteBook.NoteBook;
import ru.rubiconepro.study.R;

public class NoteAdapter extends IAdapter  {
    List<NoteWrapper> data;
    int positionPart;

    public NoteAdapter(Context context, int position) {
        super(context);

        this.positionPart = position;
        this.data = NoteBook.instance.getList(position);
    }

    public void reloadData() {
        this.data = NoteBook.instance.getList(positionPart);
        super.reloadData();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        NoteWrapper w = data.get(position);

        StringBuilder b = new StringBuilder();
        for (int i = 0; i < w.level; i++) {
            b.append("  ");
        }
        b.append(w.model.title);

        TextView label = v.findViewById(R.id.label);
        label.setText(b.toString());
        return v;
    }

    void deleteElement(int position) {
        //data.listNotes.remove(position);
        NoteBook.instance.deleteElement(positionPart, data.get(position));
        this.reloadData();
    }

    void editElement(final int position) {
        new PromptDialog(context, "Изменение элемента", new IPromptDialog() {
            @Override
            public void dialogDone(boolean result, String text) {
                if (!result)
                    return;

                data.get(position).model.title = text;
                NoteAdapter.this.notifyDataSetChanged();
            }
        }).setText(data.get(position).model.title).show();
    }

    @Override
    protected void addElement(final int position) {
        new PromptDialog(context, "Добавление элемента", new IPromptDialog() {
            @Override
            public void dialogDone(boolean result, String text) {
                if (!result)
                    return;

                NotesModel nm = new NotesModel();
                nm.title = text;
                nm.text = "";

                NoteWrapper w = data.get(position);
                w.model.notesList.add(nm);

                reloadData();
            }
        }).setText("").show();
    }
}

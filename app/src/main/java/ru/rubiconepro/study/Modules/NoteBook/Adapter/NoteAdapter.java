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
import ru.rubiconepro.study.Modules.NoteBook.Layout.Note;
import ru.rubiconepro.study.Modules.NoteBook.Layout.NoteBase;
import ru.rubiconepro.study.Modules.NoteBook.Model.NoteWrapper;
import ru.rubiconepro.study.Modules.NoteBook.Model.NotesModel;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartListModel;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartModel;
import ru.rubiconepro.study.Modules.NoteBook.NoteBook;
import ru.rubiconepro.study.R;

public class NoteAdapter extends IAdapter  {
    List<NoteWrapper> data;
    int positionPart;
    int positionParrent;

    public NoteAdapter(Context context, int position, int positionNote) {
        super(context);

        this.positionPart = position;
        this.data = NoteBook.instance.getList(position);
        this.positionParrent = positionNote;
    }

    public void reloadData() {
        this.data = NoteBook.instance.getList(positionPart);
        super.reloadData();
    }

    @Override
    public void onClick(View v) {
        final int positionTag = (int)v.getTag();
        if (v.getId() == R.id.btnDelete)
            this.deleteElement(positionTag);
        if (v.getId() == R.id.btnEdit)
            this.editElement(positionTag);
        if (v.getId() == R.id.btnAdd) {
            this.addElement(positionTag);
//            new PromptDialog(this, "Добавление элемента", new IPromptDialog() {
//                @Override
//                public void dialogDone(boolean result, String text) {
//                    if (!result)
//                        return;
//
//                    createElement(text, positionTag);
//                    adapter.reloadData();
//                }
//            }).show();
        }
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

        //TODO Сделать отступы в зависимости от level
        //TODO Сделать добавление
        TextView label = v.findViewById(R.id.label);
        label.setText(data.get(position).model.title.toString());

//        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) label.getLayoutParams();
//        int m = getResources().getDimensionPixelSize(R.dimen.ololo_margin);
//        lp.setMargins(m, 0, 0, 0);

        return v;
    }

    private void deleteElement(int position) {
        //data.listNotes.remove(position);
        NoteBook.instance.deleteElement(positionPart, data.get(position));
        this.reloadData();
    }

    private void editElement(final int position) {
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

    private void addElement(int position) {

        Intent i = new Intent(context, Note.class);
        i.putExtra(IntentConst.positionPart, positionPart);
        i.putExtra("positionPart", positionPart);
        i.putExtra("positionNote", position);
       ((NoteBase)context).startActivity(i);


    }


}

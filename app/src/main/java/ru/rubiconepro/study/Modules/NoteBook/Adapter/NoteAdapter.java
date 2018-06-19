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
    int positionNote = -1;


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
            b.append("      ");
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

    protected void rightElement(final int position) {

        NoteWrapper w = data.get(position);
        NotesModel nm = new NotesModel();
        nm.title = w.model.title;
        nm.text = w.model.text;
        nm.notesList = w.model.notesList;
        nm.positionCurrThis = w.model.positionCurrThis;
        nm.isShowN = w.model.isShowN;

        if(w.parent != null) {
            for (int i = 0; i < w.parent.notesList.size(); i++) {

                if (w.parent.notesList.get(i).equals(w.model)) {
                    if (i != 0) {
                        w.parent.notesList.get((i - 1)).notesList.add(nm);
                        NoteBook.instance.deleteElement(positionPart, data.get(position));
                    } else {
                        NotesModel nmN = new NotesModel();
                        nmN.title = w.model.title;
                        nmN.text = w.model.text;
                        nmN.isShowN = w.model.isShowN;
                        nmN.positionCurrThis = w.model.positionCurrThis;
                        w.parent.notesList.get((i)).notesList.add(nmN);
                    }
                }
            }
        } else {
            NotesModel nmN2 = new NotesModel();
            nmN2.title = w.model.title;
            nmN2.text = w.model.text;
            nmN2.isShowN = w.model.isShowN;
            nmN2.positionCurrThis = w.model.positionCurrThis;
            w.model.notesList.add(nmN2);
        }


        this.reloadData();
    }

    protected void leftElement(final int position) {

        NoteWrapper w = data.get(position);
        NotesModel nm = new NotesModel();
        nm.title = w.model.title;
        nm.text = w.model.text;
        nm.notesList = w.model.notesList;
        nm.isShowN = w.model.isShowN;


        if (w.parent != null){
        int indexThis = w.parent.positionCurrThis;
        NoteWrapper wPar2 = new NoteWrapper();
        for (int i = 0; i < data.size(); i++){
            if((data.get(i)).model.equals(w.parent)){

                wPar2 = data.get(i);

                if (wPar2.parent != null) {
                    int index = 0;
                    for (int j = 0; j < wPar2.parent.notesList.size(); j++) {
                        if (wPar2.parent.notesList.get(j).equals(wPar2.model)) {
                            index = j;
                   //         wPar2.parent.notesList.add(j, nm);

                        }
                    }
                    wPar2.parent.notesList.add(index, nm);

                } else {

                    NoteWrapper wPar = new NoteWrapper();
                    NotesModel nmNew = new NotesModel();
                    nmNew.text = nm.text;
                    nmNew.title = nm.title;
                    nmNew.notesList = nm.notesList;
                    nmNew.isShowN = nm.isShowN;
                    wPar.model = nmNew;
                    wPar.parent = null;
                    wPar.level = 0;
                    if (w.level == 1){
                        NoteBook.instance.addNote(nmNew, positionPart, indexThis);
                    } else {
                        positionNote = position;
                        NoteBook.instance.addNote(nmNew, positionPart, positionNote);
                    }
                }
            }
        }
    }

        NoteBook.instance.deleteElement(positionPart, data.get(position));
        this.reloadData();

    }

    protected void rollElement(final int position) {
        for (NotesModel mod : data.get(position).model.notesList) {
            if (mod.isShowN == true) {
                mod.isShowN = false;
            } else {
                mod.isShowN = true;
            }
        }
        this.reloadData();
    }
}

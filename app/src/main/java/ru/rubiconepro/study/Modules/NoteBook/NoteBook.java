package ru.rubiconepro.study.Modules.NoteBook;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ru.rubiconepro.study.Modules.Base.Base;
import ru.rubiconepro.study.Modules.NoteBook.Model.NoteWrapper;
import ru.rubiconepro.study.Modules.NoteBook.Model.NotesModel;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartListModel;
import ru.rubiconepro.study.Modules.NoteBook.Model.PartModel;

public class NoteBook extends Base {
    //Создание синглтона
    public static final NoteBook instance = new NoteBook();
    private NoteBook() { }
    private PartListModel model = null;

    public PartListModel getModel() {
        //TODO Сделать Сбор данных из хранилища
        if (model == null) {
            model = new PartListModel();
        }
        return model;
    }

    public void addPart(String name) {
        model.items.add(new PartModel(name));
    }

    public void addNote(NotesModel nm, int position) {
        model.items.get(position).listNotes.add(nm);
    }

    public PartModel getPartByPosition(int position) {
        if (position < 0 || position >= model.items.size())
            return null;

        return model.items.get(position);
    }

    /**
     * Рекурсивная функция обхода дерева
     * @param data указатель на массив в который мы добавляем все записи
     * @param model Текущая модель данных
     * @param parent Модель парента (Может быть NULL)
     * @param level Текущий уровень вложенности
     */
    private void appendList(List<NoteWrapper> data, NotesModel model, NotesModel parent, int level) {
            NoteWrapper w = new NoteWrapper();
            w.model = model;
            w.parent = parent;
            w.level = level;

            data.add(w);

            if (model.notesList != null)
                for (NotesModel sub: model.notesList)
                    appendList(data, sub, model, level + 1);
    }

    /**
     * Полкчение плоского списка записей из дерева
     * @param position Индекс категории
     * @return плоский список записей
     */
    public List<NoteWrapper> getList (int position) {
        List<NoteWrapper> data = new ArrayList<>();

        PartModel m = this.getPartByPosition(position);

        for (NotesModel sub : m.listNotes)
            appendList(data, sub, null, 0);

        return data;
    }

    public void deleteElement (int position, NoteWrapper element) {
        if (element.parent != null) {
            element.parent.notesList.remove(element.model);
        } else {
            getPartByPosition(position).listNotes.remove(element.model);
        }
    }


    protected void storeResponce(byte[] data) throws Exception {
        JSONObject object = new JSONObject(new String(data));
        JSONArray arr = object.getJSONArray("Data");

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);

            addPart(obj.getString("name"));
        }
    }

}

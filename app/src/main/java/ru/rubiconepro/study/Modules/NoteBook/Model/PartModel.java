package ru.rubiconepro.study.Modules.NoteBook.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.rubiconepro.study.Modules.Base.Model.BaseModel;

//Категории записей
public class PartModel extends BaseModel implements Serializable {
    public String title; //Название категории
    public List<NotesModel> listNotes; //Записи которые входят в эту категорию

    public PartModel() {
        title = "";
        listNotes = new ArrayList<>();
    }

    public PartModel(String name) {
        title = name;
        listNotes = new ArrayList<>();
    }

    @Override
    public String toString() {
        return title;
    }
}

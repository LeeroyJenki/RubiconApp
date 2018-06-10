package ru.rubiconepro.study.Modules.NoteBook.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Запись блокнота
public class NotesModel implements Serializable {
    public String title;
    public String text;
    public List<NotesModel> notesList; //Вложенные записи

    public NotesModel() {
        title = "";
        text = "";
        notesList = new ArrayList<>();
    }
}

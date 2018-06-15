package ru.rubiconepro.study.Modules.NoteBook.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Запись блокнота
public class NotesModel implements Serializable {
    public String title;
    public String text;
    public  boolean isShowN;
    public List<NotesModel> notesList; //Вложенные записи
    public int positionCurrThis;

    public NotesModel() {
        title = "";
        text = "";
        isShowN = true;
        notesList = new ArrayList<>();
        positionCurrThis = -1;
    }
}

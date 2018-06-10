package ru.rubiconepro.study.Modules.NoteBook.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//МОДЕЛЬ ЯВЛЯЕТСЯ СЛУЖЕБНОЙ

//Просто список категорий
//Для удобства работы
public class PartListModel implements Serializable {
    public List<PartModel> items; //Список категорий

    public PartListModel() {
        items = new ArrayList<>();
    }
}

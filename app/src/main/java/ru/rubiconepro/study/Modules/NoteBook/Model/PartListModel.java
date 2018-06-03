package ru.rubiconepro.study.Modules.NoteBook.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PartListModel implements Serializable {
    public List<PartModel> items;

    public PartListModel() {
        items = new ArrayList<>();
    }
}

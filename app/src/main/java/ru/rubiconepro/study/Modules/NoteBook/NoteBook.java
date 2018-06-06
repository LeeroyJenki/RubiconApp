package ru.rubiconepro.study.Modules.NoteBook;

import ru.rubiconepro.study.Modules.Base.Base;
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

    public PartModel getPartByPosition(int position) {
        if (position < 0 || position >= model.items.size())
            return null;

        return model.items.get(position);
    }

}

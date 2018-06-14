package ru.rubiconepro.study.Modules.NoteBook;

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

    public void addNote(NotesModel nm, int position, int positionNote) {
        if (positionNote != -1){
            model.items.get(position).listNotes.add(positionNote, nm);
        } else {
            model.items.get(position).listNotes.add(nm);
        }
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
        //Создаем элемент плоского списка
        NoteWrapper w = new NoteWrapper();
        //Добавляем туда текущий элемент
        w.model = model;
        //Добавляем указатель на предыдущий элемент
        w.parent = parent;
        //Устанавливаем уровень
        w.level = level;

        //Добавляем все в плоский список
        //Так как нам передается только указатель на него
        //то мы можем просто добавить элемент
        //и он отобразится в предыдущей функции
        data.add(w);


        //Если есть связанные записи
            if (model.notesList != null)

                //Мы по ним пробегаемся
                for (NotesModel sub : model.notesList) {
                    //И если текущий элемент нужно развернуть
                    if (sub.isShowN == true)
                        //Добавляем в список и его
                        appendList(data, sub, model, level + 1);
                }
    }

    /**
     * Полкчение плоского списка записей из дерева
     * @param position Индекс категории
     * @return плоский список записей
     */
    public List<NoteWrapper> getList (int position) {
        //Создание плоского списка
        List<NoteWrapper> data = new ArrayList<>();

        //Получение категории для отображения
        PartModel m = this.getPartByPosition(position);

        //Пробегаемся по списку записей (NoteModel)
        for (NotesModel sub : m.listNotes) {
            //Если нужно отрисовывать поддерево
            if (sub.isShowN == true)
                //То рисуем его
                appendList(data, sub, null, 0);
        }
        return data;
    }

    public void deleteElement (int positionP, NoteWrapper element) {
        if (element.parent != null) {
            element.parent.notesList.remove(element.model);
        } else {
            getPartByPosition(positionP).listNotes.remove(element.model);
        }
    }

}

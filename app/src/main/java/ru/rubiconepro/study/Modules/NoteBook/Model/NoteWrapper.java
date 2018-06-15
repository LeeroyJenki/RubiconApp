package ru.rubiconepro.study.Modules.NoteBook.Model;

/**
 * Модель является служебной
 * Нужна для схлопывания дерева в линейный список (для удобства отображения)
 *
 *
 *
*/
public class NoteWrapper {
    /**
     * Запись записной книги
     */
    public NotesModel model;
    /**
     * Запись которая является родительской
     * Может быть равна NULL
     */
    public NotesModel parent;
    /**
     * Уровень вложенности
     */
    public int level;



}

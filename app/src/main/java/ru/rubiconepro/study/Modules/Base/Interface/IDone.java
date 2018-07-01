package ru.rubiconepro.study.Modules.Base.Interface;

//Интерфейс используемый как делегат
//Извещает о том что задача выполнена
//С возвратом булевого значения (Успешно задание или нет)
public interface IDone {
    void JobDone(boolean success);
}

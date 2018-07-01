package ru.rubiconepro.study.Modules.Base.Interface;

//Интерфейс используемый как делегат
//Информирует о том что диалог для запроса строки от пользователя
//закрылся, с передачей того что ввел пользователь
public interface IPromptDialog {
    /**
     *
     * @param result пользователь нажал OK или Отмена
     * @param text то что пользователь ввел в диалог
     */
    void dialogDone(boolean result, String text);
}

package ru.rubiconepro.study.Modules.PDFView;

import ru.rubiconepro.study.Modules.Base.Base;

public class PDFView extends Base {

    private byte[] file;

    public byte[] getFile() {
        return file;
    }

    //Методы синглтона
    private static final PDFView _instance = new PDFView();
    public static PDFView Current() {
        return _instance;
    }
    private PDFView() {
        file = new byte[0];
    }


    //Методы скачивания данных Base
    @Override
    protected void storeResponce(byte[] data) throws Exception {
        file = data;
    }
}

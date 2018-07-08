package ru.rubiconepro.study;

public class Application extends android.app.Application {

    public static Application app;

    public Application() {
        Application.app = this;
    }
}

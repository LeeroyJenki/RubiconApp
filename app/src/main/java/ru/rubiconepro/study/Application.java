package ru.rubiconepro.study;

public class Application extends android.app.Application {

    private static Application app;
    public static Application Current() {
        return Application.app;
    }


    //UserToken
    private String userToken = "";
    public String getUserToken() {
        return userToken;
    }
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }




    public Application() {
        Application.app = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

package ru.rubiconepro.study.Modules.User;

import ru.rubiconepro.study.Modules.Base.Base;
import ru.rubiconepro.study.Modules.User.Model.UserModel;

public class User extends Base {
    /**
     * Получение текущего пользователя
     */
    static UserModel _currentUser = null;
    public static UserModel getCurrentUser() {
        return _currentUser;
    }
    
    /*-----------------------------------*/

    protected void storeResponce(String data) throws Exception {

    }
}

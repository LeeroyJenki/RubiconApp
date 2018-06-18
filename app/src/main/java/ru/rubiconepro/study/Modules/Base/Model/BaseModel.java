package ru.rubiconepro.study.Modules.Base.Model;

import java.util.Date;

public abstract class BaseModel {
    private Integer Id;               //ID записи
    private String number;            //Уникальный идентификатор записи
    private Date createdtime;         //Время создания записи
    private Date modifiedtime;        //Время изменения записи
    private Integer created_user_id;  //Кем создано
    private Integer assigned_user_id; //Кому назначено


    /**
     * @breaf
     *
     * Функции интерфейса которые нужно будет переопрерделить
     */

    /**
     * Функция определяет название ID поля для модели
     * @return строка
     */
    public abstract String getIDName();


    public Integer getId() {
        if (Id == null)
            return 0;
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }




}

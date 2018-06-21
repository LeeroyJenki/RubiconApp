package ru.rubiconepro.study.Modules.Base.Model;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class BaseModel {
    @ModelAttribute(fieldName = "Id", defValue = "-1")
    private Integer Id;               //ID записи
    @ModelAttribute(fieldName = "number", defValue = "N1")
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


    /**
     * Конструкторы
     */

    public BaseModel() {
        Id = -1;
        number = "";
        createdtime = new Date();
        modifiedtime = new Date();
        created_user_id = -1;
        assigned_user_id = -1;
    }

    protected JSONObject JSONData;
    public BaseModel(JSONObject obj) {
        JSONData = obj;
        Id = intFromJson(getIDName(), -1);
        number = stringFromJson("number", "");
        createdtime = dateFromJson("createdtime", new Date());
        modifiedtime = dateFromJson("modifiedtime", new Date());
        created_user_id = intFromJson("smcreatorid", -1);
        assigned_user_id = intFromJson("smownerid", -1);
    }

    public Integer getId() {
        if (Id == null)
            return 0;
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }


    protected Integer intFromJson(String fieldName, Integer defaultValue) {
        Integer target = defaultValue;
        try {
            if (JSONData.has(fieldName) && !JSONData.isNull(fieldName))
                target = JSONData.getInt(fieldName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return target;
    }

    protected String stringFromJson(String fieldName, String defaultValue) {
        String target = defaultValue;
        try {
            if (JSONData.has(fieldName) && !JSONData.isNull(fieldName))
                target = JSONData.getString(fieldName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return target;
    }

    protected Date dateFromJson(String fieldName, Date defaultValue) {
        Date target = defaultValue;
        try {
            if (JSONData.has(fieldName) && !JSONData.isNull(fieldName)) {
                String d = JSONData.getString(fieldName);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                target = format.parse(d);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return target;
    }


    //TODO НЕ ИСПОЛЬЗОВАТЬ НЕ ГОТОВО
    public void FromJsonObject(JSONObject obj) {
        JSONData = obj;
        Field[] fields = this.getClass().getFields();
        for (Field f: fields) {
            //Пытаемся получить атрибут
            ModelAttribute attr = f.getAnnotation(ModelAttribute.class);
            //Если его нет, то поле нельзя заполнять
            if (attr == null)
                continue;


            if (f.getType().equals(Integer.class)) {
                Integer value = intFromJson(attr.fieldName(), Integer.decode(attr.defValue()));
                try {
                    f.set(this, value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (f.getType().equals(String.class)) {
                String value = stringFromJson(attr.fieldName(), attr.defValue());
                try {
                    f.set(this, value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (f.getType().equals(Date.class)) {
                Date value = dateFromJson(attr.fieldName(), new Date());
                try {
                    f.set(this, value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}

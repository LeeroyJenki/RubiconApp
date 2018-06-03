package ru.rubiconepro.study.Modules.Base.Model;

public class BaseModel {
    private Integer Id;


    public Integer getId() {
        if (Id == null)
            return 0;
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }


}

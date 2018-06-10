package ru.rubiconepro.study.dao.storage;

import ru.rubiconepro.study.dao.DataObject;
import ru.rubiconepro.study.dao.DataStorage;

import java.util.List;

public interface Storage {
    boolean isMaster();

    <T extends DataObject> T getObject(DataStorage<T> storage, int id);

    <T extends DataObject> List<T> getObjects(DataStorage<T> storage);

    <T extends DataObject> void syncObjects(DataStorage<T> storage, List<T> object);
}

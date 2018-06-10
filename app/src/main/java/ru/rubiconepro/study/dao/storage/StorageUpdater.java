package ru.rubiconepro.study.dao.storage;

import ru.rubiconepro.study.dao.DataObject;
import ru.rubiconepro.study.dao.DataStorage;

public interface StorageUpdater {
    <T extends DataObject> void update(DataStorage<T> storage, T t);

    <T extends DataObject> void add(DataStorage<T> storage, T t);

    <T extends DataObject> void remove(DataStorage<T> storage, T t);
}

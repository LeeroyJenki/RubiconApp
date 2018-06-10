package ru.rubiconepro.study.dao;

import lombok.experimental.UtilityClass;
import ru.rubiconepro.study.dao.storage.Storage;
import ru.rubiconepro.study.dao.storage.StorageUpdater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public final class DAO {
    private static final List<Storage> storages = new ArrayList<>();
    private static final List<StorageUpdater> storageUpdaters = new ArrayList<>();
    private static final Map<String, DataStorage<?>> ds = new HashMap<>();

    public <T extends DataObject> DataStorage<T> getDataStorage(String name, Class<T> clazz) {
        if(ds.containsKey(name))
            return (DataStorage<T>) ds.get(name);
        DataStorage<T> storage = new DataStorage<>(name, clazz);
        ds.put(name, storage);
        return storage;
    }

    public void addStorage(Storage storage) {
        storages.add(storage);
    }

    public void addStorageUpdater(StorageUpdater updater) {
        storageUpdaters.add(updater);
    }

    public List<DataStorage<?>> getDataStorages() {
        return (List<DataStorage<?>>) ds.values();
    }

    <T extends DataObject> List<T> getData(DataStorage<T> dataStorage, boolean masterRequired) {
        if(masterRequired) {
            List<T> list = null;
            for(Storage storage : storages) {
                if(!storage.isMaster())
                    continue;

                list = storage.getObjects(dataStorage);
                if(list != null)
                    break;
            }
            if(list == null)
                return Collections.emptyList();
            for(Storage storage : storages)
                if(!storage.isMaster())
                    storage.syncObjects(dataStorage, list);
            return list;
        } else {
            List<T> list = null;
            boolean master = false;

            for(Storage storage : storages) {
                list = storage.getObjects(dataStorage);
                if(list != null) {
                    master = storage.isMaster();
                    break;
                }
            }
            if(master) {
                for(Storage storage : storages)
                    if(!storage.isMaster())
                        storage.syncObjects(dataStorage, list);
            }
            if(list == null)
                return Collections.emptyList();
            return list;
        }
    }

    <T extends DataObject> T getData(DataStorage<T> dataStorage, int id) {
        for(Storage storage : storages) {
            T o = storage.getObject(dataStorage, id);
            if(o != null)
                return o;
        }
        return null;
    }

    <T extends DataObject> void add(DataStorage<T> storage, T t) {
        for(StorageUpdater storageUpdater : storageUpdaters) storageUpdater.add(storage, t);
    }

    <T extends DataObject> void remove(DataStorage<T> storage, T t) {
        for(StorageUpdater storageUpdater : storageUpdaters) storageUpdater.remove(storage, t);
    }

    <T extends DataObject> void update(DataStorage<T> storage, T t) {
        for(StorageUpdater storageUpdater : storageUpdaters) storageUpdater.update(storage, t);
    }

    <T extends DataObject> void push(DataStorage<T> storage, List<T> t) {
        for(StorageUpdater storageUpdater : storageUpdaters) storageUpdater.push(storage, t);
    }
}

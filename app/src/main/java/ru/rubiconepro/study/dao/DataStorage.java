package ru.rubiconepro.study.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.carrotsearch.hppc.IntObjectMap;
import com.carrotsearch.hppc.IntObjectScatterMap;
import com.carrotsearch.hppc.cursors.IntObjectCursor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class DataStorage<T extends DataObject> {
    private final IntObjectMap<T> list = new IntObjectScatterMap<>();
    @Getter
    private final String name;
    @Getter
    private final Class<?> clazz;

    @Nullable
    public T get(int id) {
        T t = list.get(id);
        if(t == null) {
            t = DAO.getData(this, id);
            list.put(id, t);
        }
        return t;
    }

    @NonNull
    public List<T> getAllCached() {
        List<T> list = new ArrayList<>();
        for(IntObjectCursor<T> tIntObjectCursor : this.list) list.add(tIntObjectCursor.value);
        return list;
    }

    public List<T> getAllRemote(boolean masterRequired) {
        return DAO.getData(this, masterRequired);
    }

    public void add(T t) {
        if(list.put(t.id, t) == null)
            DAO.add(this, t);
    }

    public void remove(T t) {
        if(list.remove(t.id) != null)
            DAO.remove(this, t);
    }

    public void update(T t) {
        if(list.containsKey(t.id)) {
            list.put(t.id, t);
            DAO.update(this, t);
        }
    }
}

package ru.rubiconepro.study.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.carrotsearch.hppc.IntObjectMap;
import com.carrotsearch.hppc.IntObjectScatterMap;
import com.carrotsearch.hppc.cursors.IntObjectCursor;
import com.carrotsearch.hppc.cursors.ObjectCursor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class DataStorage<T extends DataObject> extends AbstractCollection<T> {
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

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    @Override
    public int size() {
        return list.size();
    }

    public boolean add(T t) {
        if(list.put(t.id, t) == null) {
            DAO.add(this, t);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        list.clear();
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

    public void syncWithStorage(boolean masterRequired) {
        List<T> data = DAO.getData(this, masterRequired);
        list.clear();
        for(T datum : data) list.put(datum.id, datum);
    }

    public void pushToStorage() {
        DAO.push(this, getAllCached());
    }

    private final class IteratorImpl implements Iterator<T> {
        private final Iterator<ObjectCursor<T>> cursors;

        IteratorImpl() {
            this.cursors = list.values().iterator();
        }

        @Override
        public boolean hasNext() {
            return cursors.hasNext();
        }

        @Override
        public T next() {
            return cursors.next().value;
        }

        @Override
        public void remove() {
            cursors.remove();
        }
    }
}

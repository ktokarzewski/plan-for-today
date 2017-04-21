package pl.com.tokarzewski.api;

import java.util.Collection;

public interface CRUDService<T> {
    Collection<T> findAll();
    T getById(long id);
    T create(T d);
    T update(T t);
    void delete(long id);
}

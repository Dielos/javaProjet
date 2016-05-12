package DAO;

import java.util.Collection;

/**
 *
 * @author Robin
 * @param <T>
 * Main Dao Class
 */
public interface Dao<T> {
    public boolean      create(T obj);
    public T            find (Integer id);
    public Collection<T> findAll();
    public boolean      update (T obj);
    public boolean      delete (T obj);
    public boolean      deleteAll();
    public void         close();
}

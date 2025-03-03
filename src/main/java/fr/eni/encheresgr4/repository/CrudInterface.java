package fr.eni.encheresgr4.repository;

import java.util.List;

public interface CrudInterface<T> {

    public T findOneById(int id);

    public List<T> findAll();

    public int save(T t);

    public void delete(T t);

}

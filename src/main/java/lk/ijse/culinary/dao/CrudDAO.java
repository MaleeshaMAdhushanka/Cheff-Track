package lk.ijse.culinary.dao;


import java.util.List;

public interface CrudDAO <T> extends SuperDAO{

    List<T> getAll() ;
    void save(T entity)  ;
    void update(T entity)  ;
    void delete(T entity)  ;
    T search(String id) ;
    String generateNextId() ;
    List<String> getIds();

}

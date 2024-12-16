package ec.edu.uce.payments.jpa.Services;

import java.util.List;

public interface CrudService<T> {
    List<T> list();
    T findById(Long id);
    void save(T entity);
    void delete(Long id);
    void update(T entity);
}
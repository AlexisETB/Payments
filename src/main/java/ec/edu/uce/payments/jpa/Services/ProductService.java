package ec.edu.uce.payments.jpa.Services;

import ec.edu.uce.payments.jpa.Entities.Product;
import ec.edu.uce.payments.jpa.Manager.EntityManagerProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductService implements CrudService<Product> {
    public ProductService (){}


    @Inject
    private EntityManagerProvider emp;

    @Transactional
    public void createProduct(Product product) {
        EntityManager em = emp.getEntityManager();
        em.persist(product);
    }

    @Override
    @Transactional
    public List<Product> list() {
        try {
            EntityManager em = emp.getEntityManager();
            return em.createQuery("FROM Product", Product.class).getResultList();
        }catch (Exception e) { throw new ServiceJdbcException(e.getMessage(), e.getCause());}
    }

    @Override
    @Transactional
    public Product findById(Long id) {
        try {
            EntityManager em = emp.getEntityManager();
            return em.find(Product.class, id);
        }catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public void save(Product product) {
        try {
            EntityManager em = emp.getEntityManager();
            if (product.getId() != null && product.getId() > 0) {
                em.merge(product);
            } else {
                em.persist(product);
            }
        }catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            EntityManager em = emp.getEntityManager();
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
            }
        }catch  (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public void update(Product product) {
        try {
            EntityManager em = emp.getEntityManager();
            if (product.getId() == null || product.getId() <= 0) {
                throw new IllegalArgumentException("El ID debe ser válido para actualizar");
            }
            em.merge(product);
        }catch (Exception e) {
            throw new ServiceJdbcException("Error updating user: " + product.getName(), e);
        }
    }
}

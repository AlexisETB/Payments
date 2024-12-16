package ec.edu.uce.payments.jpa.Services;

import ec.edu.uce.payments.jpa.Entities.UserP;
import ec.edu.uce.payments.jpa.Manager.EntityManagerProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UserPService implements CrudService<UserP> {

    public UserPService() {}

    @Inject
    private EntityManagerProvider emp;

    @Transactional
    public void createUserP(UserP userP) {
        try {
            EntityManager em = emp.getEntityManager();
            em.persist(userP);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el usuario", e);
        }
    }

    @Override
    @Transactional
    public List<UserP> list() {
        try {
            EntityManager em = emp.getEntityManager();
            return em.createQuery("FROM UserP", UserP.class).getResultList();
        }catch (Exception e) { throw new ServiceJdbcException(e.getMessage(), e.getCause());}
    }

    @Override
    @Transactional
    public UserP findById(Long id) {
        try {
            EntityManager em = emp.getEntityManager();
            return em.find(UserP.class, id);
        }catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public void save(UserP user) {
        try {
            EntityManager em = emp.getEntityManager();
            if (user.getId() != null && user.getId() > 0) {
                em.merge(user);
            } else {
                em.persist(user);
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
            UserP user = em.find(UserP.class, id);
            if (user != null) {
                em.remove(user);
            }
        }catch  (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public void update(UserP user) {
        try {
            EntityManager em = emp.getEntityManager();
            if (user.getId() == null || user.getId() <= 0) {
                throw new IllegalArgumentException("El ID debe ser válido para actualizar");
            }
            em.merge(user);
        }catch (Exception e) {
            throw new ServiceJdbcException("Error updating user: " + user.getName(), e);
        }
    }
}


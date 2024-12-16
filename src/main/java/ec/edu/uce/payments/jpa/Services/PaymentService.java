package ec.edu.uce.payments.jpa.Services;

import ec.edu.uce.payments.jpa.Entities.PaymentDetail;
import ec.edu.uce.payments.jpa.Manager.EntityManagerProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PaymentService implements CrudService<PaymentDetail> {
    public PaymentService() {}

    @Inject
    private EntityManagerProvider emp;

    @Transactional
    public void createPayment(PaymentDetail payment) {
        EntityManager em = emp.getEntityManager();
        em.persist(payment);
    }

    @Override
    @Transactional
    public List<PaymentDetail> list() {
        EntityManager em = emp.getEntityManager();
        try {
            return em.createQuery("FROM PaymentDetail", PaymentDetail.class).getResultList();
        }catch (Exception e) { throw new ServiceJdbcException(e.getMessage(), e.getCause());}
    }

    @Override
    @Transactional
    public PaymentDetail findById(Long id) {
        EntityManager em = emp.getEntityManager();
        try {
            return em.find(PaymentDetail.class, id);
        }catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public void save(PaymentDetail payment) {
        EntityManager em = emp.getEntityManager();
        try {
            if (payment.getId() != null && payment.getId() > 0) {
                em.merge(payment);
            } else {
                em.persist(payment);
            }
        }catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        EntityManager em = emp.getEntityManager();
        try {
            PaymentDetail payment = em.find(PaymentDetail.class, id);
            if (payment != null) {
                em.remove(payment);
            }
        }catch  (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public void update(PaymentDetail payment) {
        EntityManager em = emp.getEntityManager();
        try {
            if (payment.getId() == null || payment.getId() <= 0) {
                throw new IllegalArgumentException("El ID debe ser válido para actualizar");
            }
            em.merge(payment);
        }catch (Exception e) {
            throw new ServiceJdbcException("Error updating user: " + payment.getPaymentMethod(), e);
        }
    }
}

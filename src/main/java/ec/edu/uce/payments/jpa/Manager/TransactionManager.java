package ec.edu.uce.payments.jpa.Manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.interceptor.InvocationContext;
import jakarta.transaction.Transactional;

import java.util.logging.Logger;

@Transactional
@Interceptor
public class TransactionManager {

    @Inject
    private EntityManagerProvider emp;

    private Logger log;

    @AroundInvoke
    public Object manageTransaction(InvocationContext context) throws Exception {
        EntityManager em = emp.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            log.info("----- Transaction started -----" + context.getMethod().getName()
                    + " of the class: " + context.getMethod().getDeclaringClass());
            transaction.begin();
            Object result = context.proceed();
            transaction.commit();
            log.info("----- Transaction finished -----" + context.getMethod().getName()
                    + " of the class: " + context.getMethod().getDeclaringClass());
            return result;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
                log.warning("Transaction rolled back.");
            }
            System.err.println("Transaction failed: " + e.getMessage());
            throw new RuntimeException("Transaction failed", e);
        } finally {
            if (em.isOpen()) {
                em.close();
                log.info("EntityManager closed.");
            }
        }
    }
}


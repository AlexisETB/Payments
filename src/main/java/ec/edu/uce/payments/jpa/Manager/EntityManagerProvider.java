package ec.edu.uce.payments.jpa.Manager;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.logging.Logger;


@ApplicationScoped
public class EntityManagerProvider {
    private static final String PERSISTENCE_UNIT_NAME = "paymentUnit";


    private EntityManagerFactory emf;

    private static final Logger log = Logger.getLogger(EntityManagerProvider.class.getName());

    @PostConstruct
    public void init() {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            log.info("EntityManagerFactory initialized successfully.");
        } catch (Exception e) {
            System.err.println("Error initializing EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize EntityManagerFactory", e);
        }
    }

    @Produces
    public  EntityManager getEntityManager() {
        if (emf == null || !emf.isOpen()) {
            throw new IllegalStateException("EntityManagerFactory is not available or has been closed.");
        }
        return emf.createEntityManager();
    }

    @PreDestroy
    public void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            log.info("EntityManager closed");
        }
    }
}


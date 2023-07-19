package az.inci.bmsreport.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractService
{

    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
}

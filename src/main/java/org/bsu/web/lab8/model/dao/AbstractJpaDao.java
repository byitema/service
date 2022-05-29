package org.bsu.web.lab8.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractJpaDao<T extends Serializable, PK extends Integer> implements GenericDao<T, PK> {

    protected static EntityManager entityManager;
    private final Class<T> clazz;

    protected AbstractJpaDao(Class<T> clazzToSet) {
        if (entityManager == null) {
            EntityManagerFactory emf = Persistence
                    .createEntityManagerFactory("restaurant");
            entityManager = emf.createEntityManager();
        }
        this.clazz = clazzToSet;
    }

    @Override
    public T persist(T object) throws DAOException {
        executeInsideTransaction(entityManager -> entityManager.persist(object));
        return object;
    }

    @Override
    public T getByPK(PK key) throws DAOException {
        T result = entityManager.find(clazz, key);
        if (result == null)
            throw new DAOException("Object with id = " + key + " not found!");
        return result;
    }

    @Override
    public void update(T object) throws DAOException {
        executeInsideTransaction(entityManager -> entityManager.merge(object));
    }

    @Override
    public void delete(T object) throws DAOException {
        executeInsideTransaction(entityManager -> entityManager.remove(object));
    }

    @Override
    public List<T> getAll() throws DAOException {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(clazz);

        Root<T> root = criteriaQuery.from(clazz);

        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    protected void executeInsideTransaction(Consumer<EntityManager> action)
            throws DAOException {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw new DAOException("Error while transaction!", e);
        }
    }
}

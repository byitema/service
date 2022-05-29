package org.bsu.web.lab8.model.dao.domain;

import org.bsu.web.lab8.model.dao.AbstractJpaDao;
import org.bsu.web.lab8.model.dao.DAOException;
import org.bsu.web.lab8.model.entity.Order;
import org.bsu.web.lab8.model.entity.OrderPosition;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class OrderDao extends AbstractJpaDao<Order, Integer> {

    public OrderDao() {
        super(Order.class);
    }

    public List<Order> getOrders(boolean confirmed) throws DAOException {
        return entityManager
                .createNamedQuery("Order.findByConfirmedStatus", Order.class)
                .setParameter("isConfirmed", confirmed)
                .getResultList();
    }

    public List<Order> getOrders(int id) throws DAOException {
        return entityManager
                .createNamedQuery("Order.findByClientId", Order.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<OrderPosition> getOrderPositions(Integer orderId) throws DAOException {
        return getByPK(orderId).getPositions();
    }

    public void confirmOrder(Integer orderId) throws DAOException {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            Query query = entityManager.createNamedQuery("Order.confirmOrder");
            query.setParameter("id", orderId);

            int count = query.executeUpdate();
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }

            throw new DAOException(e.getMessage(), e);
        }
    }

    public List<Order> getAll(Integer clientId) throws DAOException {
        return entityManager
                .createNamedQuery("Order.findByClient", Order.class)
                .setParameter("id", clientId)
                .getResultList();
        //return new UserDao().getByPK(clientId).getClientOrders();
    }

    public BigDecimal getOrderCost(Integer orderId) throws DAOException {
        return entityManager
                .createNamedQuery("Order.getCost", BigDecimal.class)
                .setParameter("id", orderId)
                .getSingleResult();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}

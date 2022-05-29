package org.bsu.web.lab8.model.dao;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

@Remote
public interface GenericDao<T, PK extends Serializable> {
    T persist(T object) throws DAOException;

    T getByPK(PK key) throws DAOException;

    void update(T object) throws DAOException;

    void delete(T object) throws DAOException;

    List<T> getAll() throws DAOException;
}

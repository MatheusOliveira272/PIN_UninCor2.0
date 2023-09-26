package br.com.unincor.webSite.model.dao;

import br.com.unincor.webSite.core.HibernateManager;
import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDao<T extends Serializable, ID> implements Serializable {

   private Class<T> aClass;
    private EntityManager em;

    public GenericDao() {
        reflection();
    }

    public EntityManager getEntityManager() {
        if (em == null) {
            em = HibernateManager.geEntityManager();
        }
        return em;
    }

    public long couunt() {
        var query = getEntityManager().createQuery("select coun(c) from " + aClass.getSimpleName() + " c");
        long count = (Long) query.getSingleResult();
        return count;
    }

    public T findById(ID id) {
        return getEntityManager().find(aClass, id);
    }

    public T save(T value) {
        getEntityManager().getTransaction().begin();
        T valueSaved = getEntityManager().merge(value);
        getEntityManager().getTransaction().commit();
        return valueSaved;
    }

    public void delete(ID id) {
        getEntityManager().getTransaction().begin();
        var value = getEntityManager().getReference(aClass, id);
        getEntityManager().remove(value);
        getEntityManager().getTransaction().commit();
    }

    public List<T> findAll() {
        return getEntityManager().createQuery("from " + aClass.getSimpleName()).getResultList();
    }

    private void reflection() {
        aClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}

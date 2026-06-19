package de.iu.ipwa02.dao;

import de.iu.ipwa02.model.GhostNet;
import de.iu.ipwa02.model.GhostNetStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class GhostNetDAO {

    public void save(GhostNet ghostNet) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(ghostNet);
            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }

    public List<GhostNet> findAll() {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            TypedQuery<GhostNet> query = entityManager.createQuery(
                    "SELECT g FROM GhostNet g ORDER BY g.id DESC",
                    GhostNet.class
            );
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<GhostNet> findOpenGhostNets() {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            TypedQuery<GhostNet> query = entityManager.createQuery(
                    "SELECT g FROM GhostNet g WHERE g.status = :status ORDER BY g.id DESC",
                    GhostNet.class
            );
            query.setParameter("status", GhostNetStatus.GEMELDET);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<GhostNet> findGhostNetsInRecovery() {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            TypedQuery<GhostNet> query = entityManager.createQuery(
                    "SELECT g FROM GhostNet g WHERE g.status = :status ORDER BY g.id DESC",
                    GhostNet.class
            );
            query.setParameter("status", GhostNetStatus.BERGUNG_BEVORSTEHEND);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public List<GhostNet> findGhostNetsNotRecovered() {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            TypedQuery<GhostNet> query = entityManager.createQuery(
                    "SELECT g FROM GhostNet g " +
                            "WHERE g.status = :reportedStatus OR g.status = :recoveryStatus " +
                            "ORDER BY g.id DESC",
                    GhostNet.class
            );
            query.setParameter("reportedStatus", GhostNetStatus.GEMELDET);
            query.setParameter("recoveryStatus", GhostNetStatus.BERGUNG_BEVORSTEHEND);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public GhostNet findById(Long id) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            return entityManager.find(GhostNet.class, id);
        } finally {
            entityManager.close();
        }
    }

    public void update(GhostNet ghostNet) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(ghostNet);
            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
        }
    }
}
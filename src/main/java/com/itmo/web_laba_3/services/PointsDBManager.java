package com.itmo.web_laba_3.services;

import com.itmo.web_laba_3.model.Point;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class PointsDBManager {
    private final DbManager dbManager;

    public PointsDBManager(){
        this.dbManager = new DbManager();
    }
    public void save(Point point) {
        EntityManager entityManager = dbManager.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(point);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }
    }
    public List<Point> getShots(){
        try (EntityManager entityManager = dbManager.getEntityManager()) {
            return entityManager.createQuery("SELECT p FROM Point p", Point.class).getResultList();
        }
    }

//    public void clearAllShots(){
//        EntityManager entityManager = dbManager.getEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//            entityManager.createQuery("DELETE FROM GraphShot").executeUpdate();
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//        } finally {
//            entityManager.close();
//        }
//    }
}

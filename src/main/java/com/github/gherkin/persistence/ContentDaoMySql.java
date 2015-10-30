package com.github.gherkin.persistence;

import com.github.gherkin.Content;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class ContentDaoMySql implements ContentDao {

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public ContentDaoMySql() {
        entityManagerFactory = Persistence.createEntityManagerFactory("sql");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void save(Content content) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            ContentEntity entity = dataToEntity(content);

            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();

        } catch(IllegalArgumentException exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
    }

    @Override
    public void insert(Content content) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            ContentEntity entity = dataToEntity(content);

            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Content retrieve(int id) {
        ContentEntity entity = entityManager.find(ContentEntity.class, id);
        return entityToData(entity);
    }

    @Override
    public void remove(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            Content content = retrieve(id);
            ContentEntity entity = dataToEntity(content);

            transaction.begin();
            entityManager.remove(entity);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ContentEntity dataToEntity(Content content) {
        ContentEntity entity = new ContentEntity();

        entity.setId(Integer.parseInt(content.get("id")));
        entity.setContent(content);

        return entity;
    }

    private Content entityToData(ContentEntity entity) {
        Content content = new Content();
        content.putAll(entity.getContent());

        return content;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

package com.example.demo;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Data Access Object - provide some specific data operations without exposing details of the database
 * Access data for the Greeting entity.
 * Repository annotation allows Spring to find and configure the DAO.
 * Transactional annonation will cause Spring to call begin() and commit()
 * at the start/end of the method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class GreetingDao {

    //PersistenceContext annotation used to specify there is a database source.
    //EntityManager is used to create and remove persistent entity instances,
    //to find entities by their primary key, and to query over entities.
    @PersistenceContext
    private EntityManager entityManager;

    //Insert greeting into the database.
    public void create(Greeting greeting) {
        entityManager.persist(greeting);
    }

    public String update(Greeting g) {
        String to_return = "Greeting successfully updated";
        try {
            entityManager.merge(g);
        } catch (IllegalArgumentException e) {
            to_return = e.toString();
        }
        return to_return;
    }

    //Return the greeting with the passed-in id.
    public Greeting getById(int id) {
        return entityManager.find(Greeting.class, id);
    }


    public Greeting delete(int id) {
        Greeting g = getById(id);
        if (g.getId() > 0) {
            entityManager.remove(g);
        } else
            entityManager.remove(entityManager.merge(g));
        return g;
    }

    @SuppressWarnings("unchecked")
    public List<Greeting> getAll() {
        return entityManager.createQuery("from User").getResultList();
    }


} // class UserDao

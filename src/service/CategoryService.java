package service;

import model.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Wojtek on 18/05/15.
 */

@Stateless
public class CategoryService {
    @PersistenceContext
    EntityManager em;

    public void save(Category category){
        this.em.persist(category);
    }


}

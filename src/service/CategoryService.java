package service;

import model.CategoryEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Wojtek on 18/05/15.
 */

@Stateless
public class CategoryService {
    @PersistenceContext
    EntityManager em;

    public void saveCategory(CategoryEntity category){
        this.em.persist(category);
    }

    public void updateCategory(CategoryEntity category) { this.em.merge(category); }

    public List<CategoryEntity> getAllCategories() { return this.em.createNamedQuery("Category.findAll").getResultList(); }

    public List<CategoryEntity> getCategoryName() { return this.em.createNamedQuery("Category.findNames").getResultList(); }
}

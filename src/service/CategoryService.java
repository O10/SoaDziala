package service;

import model.CategoryEntity;
import model.CategoryGroupEntity;

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

    public List<CategoryGroupEntity> getAllCategoryGroups() { return this.em.createNamedQuery("CategoryGroup.findAll").getResultList(); }

    public List<CategoryEntity> getAllCategories() { return this.em.createNamedQuery("Category.findAll").getResultList(); }
}
